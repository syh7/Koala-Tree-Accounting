package koalatree.services;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import koalatree.domain.Transaction;
import koalatree.exceptions.TransactionNotFoundException;
import koalatree.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    public Iterable findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findTransactionsByDate(LocalDate date) {
        return transactionRepository.findTransactionsByDate(date);
    }

    public List<Transaction> findTransactionsByMonth(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        LocalDate startDate = parsedDate.with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate endDate = parsedDate.withDayOfMonth(1).minusDays(1);
        return transactionRepository.findTransactionsByDateBetween(startDate, endDate);
    }
}
