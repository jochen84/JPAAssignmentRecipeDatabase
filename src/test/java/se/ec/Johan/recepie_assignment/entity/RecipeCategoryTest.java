package se.ec.Johan.recepie_assignment.entity;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeCategoryTest {

    @Autowired
    private RecipeCategory testObject;

    private Recipe recipe1;
    private Recipe recipe2;


    private RecipeIngredient oxfile = new RecipeIngredient(new Ingredient("Oxfilé"),10,Measurement.KG);

    @BeforeEach
    void setUp(){

        testObject = new RecipeCategory("Mexican");
        recipe1 = new Recipe("Köttgryta", new RecipeInstructions("Tärna kött"));
        recipe2 = new Recipe("Sallad", new RecipeInstructions("Tärna gurka"));

        testObject.addRecipe(recipe1);


    }

    @Test
    public void add_recipe_to_category_list(){
        testObject.addRecipe(recipe2);

        assertEquals(2,testObject.getRecipes().size());
    }

    @Test
    public void remove_recipe_from_category_list(){
        testObject.removeRecipe(recipe1);

        assertEquals(0,testObject.getRecipes().size());
    }

}
