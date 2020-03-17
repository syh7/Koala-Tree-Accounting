package koalatree.services;

import koalatree.domain.Transaction;
import koalatree.exceptions.TransactionNotFoundException;
import koalatree.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    public List<Transaction> findAllTransactions() {
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

    public List<Transaction> findTransactionsByDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        return transactionRepository.findTransactionsByDate(date);
    }

    public List<Transaction> findTransactionsByMonth(String date) {
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        LocalDate startDate = parsedDate.withDayOfMonth(1).minusDays(1);
        LocalDate endDate = parsedDate.with(TemporalAdjusters.firstDayOfNextMonth());
        return transactionRepository.findTransactionsByDateBetween(startDate, endDate);
    }
}
