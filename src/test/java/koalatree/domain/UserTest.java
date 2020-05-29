package koalatree.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UserTest {

    private static final String BEIDE = "Beide";

    @Test
    public void getDutchName_returnsDutchName() {
        // given

        // when
        String result = User.ALL.getDutchName();

        // then
        assertThat(result).isEqualTo(BEIDE);
    }

    @Test
    public void valueOfDutchName_shouldConvertUser() {
        // given

        // when
        User result = User.valueOfDutchName(BEIDE);

        // then
        assertThat(result).isEqualTo(User.ALL);
    }

    @Test
    public void valueOfDutchName_shouldThrowError() {
        // given

        // when + then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                User.valueOfDutchName("blub"));
        assertThat(exception.getMessage()).isEqualTo("Tried parsing blub to User but failed.");
    }
}