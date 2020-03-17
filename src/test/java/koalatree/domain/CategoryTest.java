package koalatree.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CategoryTest {

    private static final String OVERIG = "Overig";

    @Test
    public void getDutchName_returnsDutchName() {
        // given

        // when
        String result = Category.OTHER.getDutchName();

        // then
        assertThat(result).isEqualTo(OVERIG);
    }

    @Test
    public void valueOfDutchName_shouldConvertCategory() {
        // given

        // when
        Category result = Category.valueOfDutchName(OVERIG);

        // then
        assertThat(result).isEqualTo(Category.OTHER);
    }

    @Test
    public void valueOfDutchName_shouldThrowError() {
        // given

        // when + then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Category.valueOfDutchName("blub"));
        assertThat(exception.getMessage()).isEqualTo("Tried parsing blub to Category but failed.");
    }
}