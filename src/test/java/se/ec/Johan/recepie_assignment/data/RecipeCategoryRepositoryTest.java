package se.ec.Johan.recepie_assignment.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;
import se.ec.Johan.recepie_assignment.entity.RecipeCategory;

import javax.persistence.Access;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeCategoryRepositoryTest {

    @Autowired
    private RecipeCategoryRepository testObject;

    RecipeCategory category1 = new RecipeCategory("Spicy");
    RecipeCategory category2 = new RecipeCategory("Mexican");
    RecipeCategory category3 = new RecipeCategory("Italian");

    @BeforeEach
    void setUp(){

        testObject.save(category1);
        testObject.save(category2);
        testObject.save(category3);

    }

    @Test
    public void given_name_finds_category(){
        String name = "spicy";
        Optional<RecipeCategory> result = testObject.findByCategoryNameIgnoreCase(name);

        assertTrue(result.isPresent());
        assertEquals(category1,result.get());

    }

}
