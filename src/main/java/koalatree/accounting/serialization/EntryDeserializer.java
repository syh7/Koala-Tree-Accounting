package koalatree.accounting.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import koalatree.accounting.domain.Category;
import koalatree.accounting.domain.Entry;
import koalatree.accounting.domain.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class EntryDeserializer extends StdDeserializer<Entry> {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public EntryDeserializer() {
        this(null);
    }

    public EntryDeserializer(Class<Entry> clazz) {
        super(clazz);
    }

    @Override
    public Entry deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return Entry.builder()
                .user(User.valueOf(node.get("user").asText()))
                .category(Category.valueOf(node.get("category").asText()))
                .amount(BigDecimal.valueOf(node.get("amount").asDouble()))
                .build();
    }
}
