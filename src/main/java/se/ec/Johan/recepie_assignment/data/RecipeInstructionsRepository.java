package se.ec.Johan.recepie_assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ec.Johan.recepie_assignment.entity.RecipeInstructions;

public interface RecipeInstructionsRepository extends JpaRepository<RecipeInstructions, String> {
}
