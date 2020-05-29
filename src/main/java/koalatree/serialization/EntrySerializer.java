package koalatree.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import koalatree.domain.Entry;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class EntrySerializer extends StdSerializer<Entry> {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public EntrySerializer() {
        this(null);
    }

    public EntrySerializer(Class<Entry> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(Entry entry, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", entry.getId());
        generator.writeStringField("user", entry.getUser().name());
        generator.writeStringField("amount", entry.getAmount().toString());
        generator.writeStringField("createdDateTime", entry.getCreatedDateTime().format(formatter));
        generator.writeEndObject();
    }
}
