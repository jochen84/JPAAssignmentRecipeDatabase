package se.ec.Johan.recepie_assignment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ec.Johan.recepie_assignment.entity.RecipeCategory;

import java.util.List;
import java.util.Optional;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Integer> {

    Optional<RecipeCategory> findByCategoryNameIgnoreCase(String category);
}
