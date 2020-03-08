package se.ec.Johan.recepie_assignment.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.ec.Johan.recepie_assignment.entity.*;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository testObject;

    @Autowired
    private TestEntityManager em;

    //Ingredients
    private Ingredient kott;
    private Ingredient banana;
    private Ingredient apple;

    //Instructions
    private RecipeInstructions instructions;

    //Categories
    private RecipeCategory mexican;
    private RecipeCategory fresh;
    private RecipeCategory vegan;

    //Recipes
    private Recipe testRecipe1KöttSallad;
    private Recipe testRecipe2FruktSallad;
    private Recipe testRecipe3Oxe;

    @BeforeEach
    void setUp(){
        kott = em.persist(new Ingredient("Oxfilé"));
        apple = em.persist(new Ingredient("apple"));
        banana = em.persist(new Ingredient("Banana"));

        mexican = em.persist(new RecipeCategory("Mexican"));
        fresh = em.persist(new RecipeCategory("Fresh"));
        vegan = em.persist(new RecipeCategory("Vegan"));

        instructions = em.persist(new RecipeInstructions("Gör detta med all din mat!"));

        //Köttsallad - Mexican - Oxfilé - Banana - Gör detta med all din mat!
        testRecipe1KöttSallad = new Recipe("Köttsallad",instructions);
        testRecipe1KöttSallad.addCategory(mexican);
        testRecipe1KöttSallad.addIngredient(new RecipeIngredient(kott,1,Measurement.KG));
        testRecipe1KöttSallad.addIngredient(new RecipeIngredient(banana,2,Measurement.G));

        //Fruktsallad - Fresh - Vegan - Apple - Banana - Gör detta med all din mat!
        testRecipe2FruktSallad = new Recipe("Fruktsallad", instructions);
        testRecipe2FruktSallad.addCategory(fresh);
        testRecipe2FruktSallad.addCategory(vegan);
        testRecipe2FruktSallad.addIngredient(new RecipeIngredient(banana,2,Measurement.HG));
        testRecipe2FruktSallad.addIngredient(new RecipeIngredient(apple,10,Measurement.KG));

        //Oxfilé - Fresh - Oxfilé - Gör detta med all din mat!
        testRecipe3Oxe = new Recipe("Oxfilé",instructions);
        testRecipe3Oxe.addCategory(fresh);
        testRecipe3Oxe.addIngredient(new RecipeIngredient(kott,2,Measurement.HG));

        testObject.save(testRecipe1KöttSallad);
        testObject.save(testRecipe2FruktSallad);
        testObject.save(testRecipe3Oxe);
    }

    //Hitta flera recept vars receptnamn innehåller en viss String
    @Test
    public void given_string_find_recipe_containing_that_string(){
        String name = "sallad";
        List<Recipe> result = testObject.findByRecipeNameContainsIgnoreCase(name);

        assertEquals(2,result.size());
    }

    //Hitta alla recept som innehåller ett visst ingrediensnamn
    @Test
    public void given_ingredient_finds_recipes_containing_ingredientQuery(){
        String ingredient = "Oxfilé";
        List<Recipe> result = testObject.findRecipeByIngredientName(ingredient);

        assertEquals(2,result.size());
    }

    //Hitta alla recept som innehåller ett visst ingrediensnamn
    @Test
    public void given_ingredient_finds_recipes_containing_ingredient(){
        String ingredient = "Oxfilé";
        List<Recipe> result = testObject.findByRecipeIngredientsIngredientIngredientNameIgnoreCase(ingredient);

        assertEquals(2,result.size());
    }

    //Hitta alla recept som tillhör en viss receptkategori
    @Test
    public void given_category_finds_recepies_with_category(){
        String category = "fresh";
        List<Recipe> result = testObject.findByRecipeCategoriesCategoryNameIgnoreCase(category);

        assertEquals(2,result.size());
    }

    //Hitta alla recept som har en eller flera träffar från en samling kategorier. Ex:{”spicy”,”mexican”,”weekend”}
    @Test
    public void given_list_of_categories_find_those_recipes(){
        List<String> categories = Arrays.asList("Fresh","Vegan");
        List<Recipe> result = testObject.findRecipeFromCategories(categories);

        assertEquals(2,result.size());
    }

    //Hitta alla recept som har en eller flera träffar från en samling kategorier. Ex:{”spicy”,”mexican”,”weekend”}
    @Test
    public void given_list_of_categories_find_those_recipestest(){
        List<String> categories = Arrays.asList("Fresh","mexican");
        List<Recipe> result = testObject.findDistinctByRecipeCategoriesCategoryNameIgnoreCaseIn(categories);

        assertEquals(3,result.size());
    }
}
