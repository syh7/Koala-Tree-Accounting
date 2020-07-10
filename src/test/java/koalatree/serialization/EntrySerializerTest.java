package koalatree.serialization;

import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import koalatree.domain.Category;
import koalatree.domain.Entry;
import koalatree.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class EntrySerializerTest {


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    private EntrySerializer subject = new EntrySerializer();

    @Mock
    private JsonGenerator generator;

    @Test
    public void serialize_shouldSerialize() throws IOException {
        // given
        Entry entry = Entry.builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .user(User.ALL)
                .category(Category.OTHER)
                .createdDateTime(ZonedDateTime.now())
                .build();

        // when
        subject.serialize(entry, generator, null);

        // then
        verify(generator).writeStartObject();
        verify(generator).writeNumberField("id", entry.getId());
        verify(generator).writeStringField("user", entry.getUser().name());
        verify(generator).writeStringField("category", entry.getCategory().name());
        verify(generator).writeStringField("amount", entry.getAmount().toString());
        verify(generator).writeStringField("createdDateTime", entry.getCreatedDateTime().format(FORMATTER));
        verify(generator).writeEndObject();
    }
}