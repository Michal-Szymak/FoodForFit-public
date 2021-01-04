import org.junit.jupiter.api.Test;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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