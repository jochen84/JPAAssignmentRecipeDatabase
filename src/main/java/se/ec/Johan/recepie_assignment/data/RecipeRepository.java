package se.ec.Johan.recepie_assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.ec.Johan.recepie_assignment.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    //Hitta flera recept vars receptnamn innehåller en viss String
    List<Recipe> findByRecipeNameContainsIgnoreCase(String containsString);

    //Hitta alla recept som innehåller ett visst ingrediensnamn
    @Query("SELECT a FROM Recipe a JOIN FETCH a.recipeIngredients b WHERE b.ingredient.ingredientName = :ingredientName")
    List<Recipe> findRecipeByIngredientName(@Param("ingredientName") String ingredientName);

    //Hitta alla recept som innehåller ett visst ingrediensnamn
    List<Recipe> findByRecipeIngredientsIngredientIngredientNameIgnoreCase(String ingredientName);

    //Hitta alla recept som tillhör en viss receptkategori
    List<Recipe> findByRecipeCategoriesCategoryNameIgnoreCase(String category);

    //Hitta alla recept som har en eller flera träffar från en samling kategorier. Ex:{”spicy”,”mexican”,”weekend”}
    @Query("SELECT DISTINCT a FROM Recipe a JOIN FETCH a.recipeCategories b WHERE b.categoryName IN :categoriesList")
    List<Recipe> findRecipeFromCategories(@Param("categoriesList") List<String> categoriesList);

    //Hitta alla recept som har en eller flera träffar från en samling kategorier. Ex:{”spicy”,”mexican”,”weekend”}
    List<Recipe> findDistinctByRecipeCategoriesCategoryNameIgnoreCaseIn(List<String> categoryList);

}


