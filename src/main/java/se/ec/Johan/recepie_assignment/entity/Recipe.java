package se.ec.Johan.recepie_assignment.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private String recipeName;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            orphanRemoval = true,
            mappedBy = "recipe"

    )
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "instruction_id")
    private RecipeInstructions instructions;

    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "recipe_recipecategory",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "recipecategory_id")
    )
    private List<RecipeCategory> recipeCategories = new ArrayList<>();

    public Recipe(int recipeId, String recipeName, RecipeInstructions instructions) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    public Recipe(String recipeName, RecipeInstructions instructions) {
        this(0, recipeName,instructions);
    }

    Recipe(){}

    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public RecipeInstructions getInstructions() {
        return instructions;
    }

    public void setInstructions(RecipeInstructions instructions) {
        this.instructions = instructions;
    }

    public List<RecipeCategory> getRecipeCategories() {
        return recipeCategories;
    }

    public void setRecipeCategories(List<RecipeCategory> recipeCategories) {
        this.recipeCategories = recipeCategories;
    }

    //Add ingredient to the ingredient list
    public boolean addIngredient(RecipeIngredient ingredient){
        //Initiera listan här istället OM den == null? - Istället för i fältet.
        if(ingredient == null) return false;
        if(recipeIngredients.contains(ingredient)) return false;

        return recipeIngredients.add(ingredient);
    }

    //Remove ingredient from ingredient list
    public boolean removeIngredient(RecipeIngredient ingredient){
        //Initiera listan här istället OM den == null? - Istället för i fältet.
        if (ingredient == null) return false;
        if (!recipeIngredients.contains(ingredient)) return false;

        return recipeIngredients.remove(ingredient);
    }

    //Add Category to category list
    public boolean addCategory(RecipeCategory category){
        if (category == null) return false;
        if (recipeCategories.contains(category)) return false;

        return recipeCategories.add(category);
    }

    //Remove Category from category list
    public boolean removeCategory(RecipeCategory category){
        if (category == null) return false;
        if (!recipeCategories.contains(category)) return false;

        return recipeCategories.remove(category);
    }
}
