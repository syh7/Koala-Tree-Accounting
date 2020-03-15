package koalatree.repositories;

import java.time.LocalDate;
import java.util.List;

import koalatree.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findTransactionsByDate(LocalDate date);

    List<Transaction> findTransactionsByDateBetween(LocalDate dateStart, LocalDate dateEnd);

}
