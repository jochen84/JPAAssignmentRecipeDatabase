package se.ec.Johan.recepie_assignment.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        if (recipeIngredients == null){
            recipeIngredients.forEach(r -> r.setRecipe(null));
        }else{
            recipeIngredients.forEach(this::addIngredient);
        }
        this.recipeIngredients = recipeIngredients;
    }

    //Add ingredient to the ingredient list
    public boolean addIngredient(RecipeIngredient ingredient){
        if(ingredient == null) return false;
        if(recipeIngredients.contains(ingredient)) return false;
        if (recipeIngredients.add(ingredient)){
            ingredient.setRecipe(this);
            return true;
        }
        return false;
    }

    //Remove ingredient from ingredient list
    public boolean removeIngredient(RecipeIngredient ingredient){

        if (ingredient == null) return false;
        if (!recipeIngredients.contains(ingredient)) return false;
        if (recipeIngredients.remove(ingredient)){
            ingredient.setRecipe(null);
            return true;
        }
        return false;
    }

    public boolean addCategory(RecipeCategory category){
        if (category == null) return false;
        if (recipeCategories.contains(category)) return false;
        if (recipeCategories.add(category)) {
            //category.getRecipes().add(this);
            return true;
        }
        return false;
    }

    //Remove Category from category list
    public boolean removeCategory(RecipeCategory category){
        if (category == null) return false;
        if (!recipeCategories.contains(category)) return false;
        if (recipeCategories.remove(category)){
            category.getRecipes().remove(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId &&
                Objects.equals(recipeName, recipe.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recipe{");
        sb.append("recipeId=").append(recipeId);
        sb.append(", recipeName='").append(recipeName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
