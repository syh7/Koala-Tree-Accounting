package koalatree.services;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
public class TransactionService{

    @Autowired
    private final TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(long id){
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByDate(LocalDate date){
        return transactionRepository.findTransactionsByDate(date);
    }

}
