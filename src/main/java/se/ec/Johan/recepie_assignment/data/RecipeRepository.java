package se.ec.Johan.recepie_assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.ec.Johan.recepie_assignment.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByRecipeNameContainsIgnoreCase(String containsString);

    @Query("SELECT r FROM Recipe r JOIN FETCH r.recipeIngredients recipeIngredients WHERE recipeIngredients.ingredient.ingredientName = :ingredientName")
    List<Recipe> findRecipeByIngredientName(@Param("ingredientName") String ingredientName);

    List<Recipe> findByRecipeIngredientsIngredientIngredientNameIgnoreCase(String ingredientName111);




}


