package koalatree.serialization;

import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import koalatree.TestUtils;
import koalatree.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
class TransactionSerializerTest {


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    private TransactionSerializer subject = new TransactionSerializer();

    @Mock
    private JsonGenerator generator;

    @Test
    void serialize_shouldSerialize() throws IOException {
        // given
        Transaction transaction = TestUtils.createTransaction();

        // when
        subject.serialize(transaction, generator, null);

        // then
        verify(generator).writeStartObject();
        verify(generator).writeNumberField("id", transaction.getId());
        verify(generator).writeStringField("amountTotal", transaction.getAmountTotal().toString());
        verify(generator).writeStringField("amountSjoerd", transaction.getAmountSjoerd().toString());
        verify(generator).writeStringField("amountLoes", transaction.getAmountLoes().toString());
        verify(generator).writeStringField("amountAll", transaction.getAmountAll().toString());
        verify(generator).writeStringField("category", transaction.getCategory().getDutchName());
        verify(generator).writeStringField("message", transaction.getMessage());
        verify(generator).writeStringField("date", transaction.getDate().format(FORMATTER));
        verify(generator).writeStringField("createdDateTime", transaction.getCreatedDateTime().format(FORMATTER));
        verify(generator).writeEndObject();
    }
}