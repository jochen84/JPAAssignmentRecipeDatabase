package se.ec.Johan.recepie_assignment.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeCategoryId;
    private String categoryName;

    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST/*,CascadeType.REFRESH*/},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "recipe_recipecategory",
            joinColumns = @JoinColumn(name = "recipecategory_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes; // = new ArrayList<>();

    public RecipeCategory(int recipeCategoryId, String categoryName, List<Recipe> recipes) {
        this.recipeCategoryId = recipeCategoryId;
        this.categoryName = categoryName;
        this.recipes = recipes;
    }

    public RecipeCategory(String categoryName) {
        this(0, categoryName, null);
    }

    RecipeCategory(){}

    public int getRecipeCategoryId() {
        return recipeCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    //Add recipes! Setter and getter is enough??
    //Remove recipes! Setter and getter is enough??

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeCategory)) return false;
        RecipeCategory that = (RecipeCategory) o;
        return recipeCategoryId == that.recipeCategoryId &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(recipes, that.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeCategoryId, categoryName, recipes);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecipeCategory{");
        sb.append("recipeCategoryId=").append(recipeCategoryId);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", recipes=").append(recipes);
        sb.append('}');
        return sb.toString();
    }
}
