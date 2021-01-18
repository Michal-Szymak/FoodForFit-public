import com.objavieni.parsing.Parser;
import com.objavieni.meals.Recipe;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParserTest {

    @Test
    void recipeParser_returnsRecipe_givenEmptyString() {
        //given
        String text = "";
        Recipe recipe;
        //when
        recipe = Parser.recipeParser(text);
        //then
        assertThat(recipe).isNotNull();
    }
    @Test
    void  recipeParser_returnsNull_givenNullString(){
        //given
        String text = null;
        Recipe recipe;
        //when
        recipe = Parser.recipeParser(text);
        //then
        assertThat(recipe).isNull();
    }

}