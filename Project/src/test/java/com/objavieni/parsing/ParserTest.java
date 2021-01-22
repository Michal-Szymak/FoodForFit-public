package com.objavieni.parsing;

import com.objavieni.meals.Recipe;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParserTest {

    @Test
    void recipeParser_returnsRecipe_givenEmptyString() {
        //given
        String text = "";
        Recipe recipe;
        //when
        recipe = new Parser().recipeParser(text);
        //then
        assertThat(recipe).isNotNull();
    }
    @Test
    void  recipeParser_returnsNull_givenNullString(){
        //given
        String text = null;
        Recipe recipe;
        //when
        recipe = new Parser().recipeParser(text);
        //then
        assertThat(recipe).isNull();
    }

}