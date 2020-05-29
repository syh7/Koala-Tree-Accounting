package koalatree;

import koalatree.domain.Category;
import koalatree.domain.Entry;
import koalatree.domain.Transaction;
import koalatree.domain.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
                .category(Category.OTHER)
                .date(LocalDate.now())
                .entries(createEntrySet(5))
                .build();
    }

    public static Set<Entry> createEntrySet(int size) {
        HashSet<Entry> entries = new HashSet<>();
        for (int i = 0;
             i < size;
             i++) {
            entries.add(createEntry());
        }
        return entries;
    }

    public static Entry createEntry() {
        return Entry.builder()
                .amount(BigDecimal.ONE)
                .user(User.ALL)
                .build();
    }
}
