package koalatree;

import koalatree.domain.Category;
import koalatree.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public final class TestUtils {

    private TestUtils() {
        // Utilities class
    }

    public static Transaction createTransactionWithMessage(String message) {
        Transaction transaction = createTransaction();
        transaction.setMessage(message);
        return transaction;
    }

    public static Transaction createTransaction() {
        return Transaction.builder()
                .id(1L)
                .amountTotal(BigDecimal.valueOf(20))
                .amountSjoerd(BigDecimal.valueOf(5))
                .amountLoes(BigDecimal.valueOf(5))
                .amountAll(BigDecimal.valueOf(5))
                .category(Category.GROCERIES)
                .date(LocalDate.now())
                .createdDateTime(ZonedDateTime.now())
                .build();
    }
}
