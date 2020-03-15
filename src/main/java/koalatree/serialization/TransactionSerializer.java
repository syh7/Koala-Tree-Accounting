package koalatree.serialization;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import koalatree.domain.Transaction;

public class TransactionSerializer extends StdSerializer<Transaction> {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public TransactionSerializer() {
        this(null);
    }

    public TransactionSerializer(Class<Transaction> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(Transaction transaction, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", transaction.getId());
        generator.writeStringField("amountTotal", transaction.getAmountTotal().toString());
        generator.writeStringField("amountSjoerd", transaction.getAmountSjoerd().toString());
        generator.writeStringField("amountLoes", transaction.getAmountLoes().toString());
        generator.writeStringField("amountAll", transaction.getAmountAll().toString());
        generator.writeStringField("category", transaction.getCategory().getDutchName());
        generator.writeStringField("message", transaction.getMessage());
        generator.writeStringField("date", transaction.getDate().format(formatter));
        generator.writeStringField("createdDateTime", transaction.getCreatedDateTime().format(formatter));
        generator.writeEndObject();
    }
}
