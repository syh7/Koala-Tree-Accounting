package koalatree.repositories;

import koalatree.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByDate(LocalDate date);

    List<Transaction> findTransactionsByDateBetween(LocalDate dateStart, LocalDate dateEnd);

}
