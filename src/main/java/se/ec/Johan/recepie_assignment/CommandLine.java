package se.ec.Johan.recepie_assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ec.Johan.recepie_assignment.data.*;
import se.ec.Johan.recepie_assignment.entity.Recipe;

@Component
public class CommandLine implements CommandLineRunner {

    private RecipeRepository recipeRepository;
    private RecipeIngredientRepository recipeIngredientRepository;
    private RecipeInstructionsRepository recipeInstructionsRepository;
    private RecipeCategoryRepository recipeCategoryRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public CommandLine(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeInstructionsRepository recipeInstructionsRepository, RecipeCategoryRepository recipeCategoryRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeInstructionsRepository = recipeInstructionsRepository;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {



    }
}
