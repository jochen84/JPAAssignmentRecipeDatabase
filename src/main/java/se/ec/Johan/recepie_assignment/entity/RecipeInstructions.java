package se.ec.Johan.recepie_assignment.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class RecipeInstructions {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String instructionsId;
    private String instructions;

    public RecipeInstructions(String instructionsId, String instructions) {
        this.instructionsId = instructionsId;
        this.instructions = instructions;
    }

    public RecipeInstructions(String instructions) {
        this(null, instructions);
    }

    RecipeInstructions(){}

    public String getInstructionsId() {
        return instructionsId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeInstructions)) return false;
        RecipeInstructions that = (RecipeInstructions) o;
        return Objects.equals(instructionsId, that.instructionsId) &&
                Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructionsId, instructions);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecipeInstructionsRepository{");
        sb.append("instructionsId='").append(instructionsId).append('\'');
        sb.append(", instructions='").append(instructions).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
