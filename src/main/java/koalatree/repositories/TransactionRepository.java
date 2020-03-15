package koalatree.repositories;

import java.time.LocalDate;
import java.util.List;

import koalatree.domain.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, Long> {

    List<Transaction> findTransactionsByDate(LocalDate date);

    List<Transaction> findTransactionsWithDateBetween(LocalDate dateStart, LocalDate dateEnd);

}
