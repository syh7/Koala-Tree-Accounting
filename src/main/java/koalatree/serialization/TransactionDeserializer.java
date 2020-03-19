package koalatree.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import koalatree.domain.Category;
import koalatree.domain.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionDeserializer extends StdDeserializer<Transaction> {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public TransactionDeserializer() {
        this(null);
    }

    public TransactionDeserializer(Class<Transaction> clazz) {
        super(clazz);
    }

    @Override
    public Transaction deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        Transaction.TransactionBuilder builder = Transaction.builder()
                .amountTotal(BigDecimal.valueOf((node.get("amountTotal").asDouble())))
                .amountSjoerd(BigDecimal.valueOf((node.get("amountSjoerd").asDouble())))
                .amountLoes(BigDecimal.valueOf((node.get("amountLoes").asDouble())))
                .amountAll(BigDecimal.valueOf(node.get("amountAll").asDouble()))
                .category(Category.valueOfDutchName(node.get("category").asText()))
                .date(LocalDate.parse(node.get("date").asText(), formatter));
        if (node.has("message")) {
            builder.message(node.get("message").asText());
        }
        return builder.build();
    }
}
