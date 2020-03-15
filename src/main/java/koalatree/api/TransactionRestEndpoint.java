package koalatree.api;

import java.time.LocalDate;
import java.util.List;

import koalatree.domain.Transaction;
import koalatree.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionRestEndpoint {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions/")
    @ResponseBody
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    @ResponseBody
    public Transaction getTransactionById(@PathVariable long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/transactions/{date}")
    @ResponseBody
    public List<Transaction> getTransactionsByDate(@PathVariable LocalDate date) {
        return transactionService.getTransactionsByDate(date);
    }

    @PostMapping("/transactions/{id}")
    @ResponseBody
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }
}
