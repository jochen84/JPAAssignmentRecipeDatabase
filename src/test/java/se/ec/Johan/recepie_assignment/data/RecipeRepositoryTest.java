package se.ec.Johan.recepie_assignment.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.ec.Johan.recepie_assignment.entity.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository testObject;

    private RecipeCategoryRepository recipeCategory;
    private RecipeIngredientRepository recipeIngredient;

    //Instructions
    private RecipeInstructions testInstructions = new RecipeInstructions("Gör detta med all din mat!");

    //Categories
    private RecipeCategory testCategoryMexican = new RecipeCategory("Mexican");
    private RecipeCategory testCategoryFresh = new RecipeCategory("Fresh");
    private RecipeCategory testCategoryVegan = new RecipeCategory("Vegan");

    //RecipeIngrediens
    private RecipeIngredient banana = new RecipeIngredient(new Ingredient("Banana"),20,Measurement.KG);
    private RecipeIngredient apple = new RecipeIngredient(new Ingredient("Apple"),10,Measurement.G);
    private RecipeIngredient kott = new RecipeIngredient(new Ingredient("Oxfilé"),1,Measurement.KG);

    //Recipes
    private Recipe testRecipe1KöttSallad;
    private Recipe testRecipe2FruktSallad;
    private Recipe testRecipe3Oxe;

    @BeforeEach
    void setUp(){

        //Köttsallad - Mexican - Oxfilé - Banana - Gör detta med all din mat!
        testRecipe1KöttSallad = new Recipe("Köttsallad",testInstructions);
        testRecipe1KöttSallad.addCategory(testCategoryMexican);
        testRecipe1KöttSallad.addIngredient(kott);
        testRecipe1KöttSallad.addIngredient(banana);

        //Fruktsallad - Fresh - Vegan - Apple - Banana - Gör detta med all din mat!
        testRecipe2FruktSallad = new Recipe("Fruktsallad", testInstructions);
        testRecipe2FruktSallad.addCategory(testCategoryFresh);
        testRecipe2FruktSallad.addCategory(testCategoryVegan);
        testRecipe2FruktSallad.addIngredient(banana);
        testRecipe2FruktSallad.addIngredient(apple);

        //Oxfilé - Fresh - Oxfilé - Gör detta med all din mat!
        testRecipe3Oxe = new Recipe("Oxfilé",testInstructions);
        testRecipe3Oxe.addCategory(testCategoryFresh);
        testRecipe3Oxe.addIngredient(kott);

        testObject.save(testRecipe1KöttSallad);
        testObject.save(testRecipe2FruktSallad);
        testObject.save(testRecipe3Oxe);
    }

    @Test
    public void given_string_find_recipe_containing_that_string(){
        String name = "sallad";
        List<Recipe> result = testObject.findByRecipeNameContainsIgnoreCase(name);

        assertEquals(2,result.size());
    }

    @Test
    public void given_ingredient_finds_recipes_containing_ingredientQuery(){
        String ingredient = "Banana";
        List<Recipe> result = testObject.findRecipeByIngredientName(ingredient);

        assertEquals(1,result.size());
    }

    @Test
    public void given_ingredient_finds_recipes_containing_ingredient(){
        String ingredient = "Banana";
        List<Recipe> result = testObject.findByRecipeIngredientsIngredientIngredientNameIgnoreCase(ingredient);

        assertEquals(1,result.size());
    }




}
