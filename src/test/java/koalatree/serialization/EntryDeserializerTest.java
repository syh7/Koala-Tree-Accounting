package koalatree.serialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import koalatree.domain.Entry;
import koalatree.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class EntryDeserializerTest {

    @Mock
    private JsonParser parser;

    @Mock
    private JsonNode node;

    @Mock
    private ObjectCodec codec;

    private EntryDeserializer subject = new EntryDeserializer();

    private Double AMOUNT = 1.5;
    private User USER = User.ALL;

    @Test
    public void deserialize_shouldDeserializeWithMessage() throws IOException {
        // given
        when(parser.getCodec()).thenReturn(codec);
        when(codec.readTree(parser)).thenReturn(node);

        JsonNode amountMock = mock(JsonNode.class);
        when(amountMock.asDouble()).thenReturn(AMOUNT);
        when(node.get("amount")).thenReturn(amountMock);

        JsonNode userMock = mock(JsonNode.class);
        when(userMock.asText()).thenReturn(USER.name());
        when(node.get("user")).thenReturn(userMock);

        // when
        Entry result = subject.deserialize(parser, null);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo(USER);
        assertThat(result.getAmount()).isEqualTo(BigDecimal.valueOf(AMOUNT));
    }
}