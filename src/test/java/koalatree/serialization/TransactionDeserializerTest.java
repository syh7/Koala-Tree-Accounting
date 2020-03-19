package koalatree.serialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import koalatree.domain.Category;
import koalatree.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@ExtendWith(MockitoExtension.class)
class TransactionDeserializerTest {

    @Mock
    private JsonParser parser;

    @Mock
    private JsonNode node;

    @Mock
    private ObjectCodec codec;

    private TransactionDeserializer subject = new TransactionDeserializer();

    private Double amountTotal = 1.5;
    private Double amountSjoerd = 2.5;
    private Double amountLoes = 3.4;
    private Double amountAll = 4.6;
    private Category category = Category.OTHER;
    private LocalDate date = LocalDate.now();

    @BeforeEach
    public void setup() throws IOException {
        when(parser.getCodec()).thenReturn(codec);
        when(codec.readTree(parser)).thenReturn(node);
        JsonNode amountTotalMock = mock(JsonNode.class);
        when(amountTotalMock.asDouble()).thenReturn(amountTotal);
        when(node.get("amountTotal")).thenReturn(amountTotalMock);

        JsonNode amountSjoerdMock = mock(JsonNode.class);
        when(amountSjoerdMock.asDouble()).thenReturn(amountSjoerd);
        when(node.get("amountSjoerd")).thenReturn(amountSjoerdMock);

        JsonNode amountLoesMock = mock(JsonNode.class);
        when(amountLoesMock.asDouble()).thenReturn(amountLoes);
        when(node.get("amountLoes")).thenReturn(amountLoesMock);

        JsonNode amountAllMock = mock(JsonNode.class);
        when(amountAllMock.asDouble()).thenReturn(amountAll);
        when(node.get("amountAll")).thenReturn(amountAllMock);

        JsonNode categoryMock = mock(JsonNode.class);
        when(categoryMock.asText()).thenReturn(category.getDutchName());
        when(node.get("category")).thenReturn(categoryMock);

        JsonNode dateMock = mock(JsonNode.class);
        when(dateMock.asText()).thenReturn(date.format(DateTimeFormatter.ISO_DATE));
        when(node.get("date")).thenReturn(dateMock);
    }

    @Test
    void deserialize_shouldDeserialize() throws IOException {
        // given

        // when
        Transaction result = subject.deserialize(parser, null);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getAmountTotal()).isEqualTo(BigDecimal.valueOf(amountTotal));
        assertThat(result.getAmountSjoerd()).isEqualTo(BigDecimal.valueOf(amountSjoerd));
        assertThat(result.getAmountLoes()).isEqualTo(BigDecimal.valueOf(amountLoes));
        assertThat(result.getAmountAll()).isEqualTo(BigDecimal.valueOf(amountAll));
        assertThat(result.getCategory()).isEqualTo(category);
        assertThat(result.getDate()).isEqualTo(date);
    }

    @Test
    void deserialize_shouldDeserializeWithMessage() throws IOException {
        // given
        String message = "test message";
        JsonNode messageMock = mock(JsonNode.class);
        when(messageMock.asText()).thenReturn(message);
        when(node.has("message")).thenReturn(true);
        when(node.get("message")).thenReturn(messageMock);

        // when
        Transaction result = subject.deserialize(parser, null);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getAmountTotal()).isEqualTo(BigDecimal.valueOf(amountTotal));
        assertThat(result.getAmountSjoerd()).isEqualTo(BigDecimal.valueOf(amountSjoerd));
        assertThat(result.getAmountLoes()).isEqualTo(BigDecimal.valueOf(amountLoes));
        assertThat(result.getAmountAll()).isEqualTo(BigDecimal.valueOf(amountAll));
        assertThat(result.getCategory()).isEqualTo(category);
        assertThat(result.getDate()).isEqualTo(date);
        assertThat(result.getMessage()).isEqualTo(message);
    }
}