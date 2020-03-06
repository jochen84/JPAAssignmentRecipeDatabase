package se.ec.Johan.recepie_assignment.entity;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeTest {

    @Autowired
    private Recipe testObject;

    private RecipeIngredient oxfile = new RecipeIngredient(new Ingredient("Oxfilé"),10,Measurement.KG);
    private RecipeIngredient bananas = new RecipeIngredient(new Ingredient("Banana"),10,Measurement.KG);

    private RecipeCategory mexican = new RecipeCategory("Mexican");

    @BeforeEach
    void setUp(){

        testObject = new Recipe("Köttsallad",new RecipeInstructions("Gör såhär!"));
        testObject.addCategory(mexican);
        testObject.addIngredient(oxfile);
        testObject.addIngredient(bananas);
    }

    @Test
    public void add_recipeIngredient_to_recipes_ingredientList_gives3(){
        RecipeIngredient ingredient = new RecipeIngredient(new Ingredient("Ostbågar"),10,Measurement.KG);
        testObject.addIngredient(ingredient);

        assertEquals(3,testObject.getRecipeIngredients().size());
    }

    @Test
    public void remove_recipeIngredient_to_recipes_ingredientList_gives1(){
        testObject.removeIngredient(oxfile);

        assertEquals(1,testObject.getRecipeIngredients().size());
    }

    @Test
    public void add_recipeCategory_to_a_recipe_gives2(){
        RecipeCategory category = new RecipeCategory("Testmat");
        testObject.addCategory(category);

        assertEquals(2,testObject.getRecipeCategories().size());
    }

    @Test
    public void remove_recipeCategory_from_a_recipe_give0(){
        testObject.removeCategory(mexican);

        assertEquals(0,testObject.getRecipeCategories().size());
    }

}
