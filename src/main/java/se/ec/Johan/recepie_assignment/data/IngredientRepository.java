package se.ec.Johan.recepie_assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ec.Johan.recepie_assignment.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    //Hitta en ingrediens med specifikt ingrediensnamn.
    Optional<Ingredient> findByIngredientNameIgnoreCase(String ingredientName);

    //Hitta flera ingredienser vars ingrediensnamn innehåller en viss String.
    List<Ingredient> findByIngredientNameContainsIgnoreCase(String ingredientName);

}
