package se.ec.Johan.recepie_assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.ec.Johan.recepie_assignment.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByRecipeNameContainsIgnoreCase(String containsString);

    @Query("SELECT a FROM Recipe a JOIN FETCH a.recipeIngredients b WHERE b.ingredient.ingredientName = :ingredientName")
    List<Recipe> findRecipeByIngredientName(@Param("ingredientName") String ingredientName);

    List<Recipe> findByRecipeIngredientsIngredientIngredientNameIgnoreCase(String ingredientName111);

    List<Recipe> findByRecipeCategoriesCategoryNameIgnoreCase(String category);

    @Query("SELECT DISTINCT a FROM Recipe a JOIN FETCH a.recipeCategories b WHERE b.categoryName IN :categoriesList")
    List<Recipe> findRecipeFromCategories(@Param("categoriesList") List<String> categoriesList);

    List<Recipe> findDistinctByRecipeCategoriesCategoryNameIgnoreCaseIn(List<String> categoryList);

}


