package koalatree.controllers;

import koalatree.domain.Transaction;
import koalatree.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/")
    public List<Transaction> findAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @PostMapping("/")
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/{id}")
    public Transaction findTransactionById(@PathVariable long id) {
        return transactionService.findTransactionById(id);
    }

    @GetMapping("/day/{date}")
    public List<Transaction> findTransactionsByDate(@PathVariable String date) {
        return transactionService.findTransactionsByDate(date);
    }

    @GetMapping("/month/{date}")
    public List<Transaction> findTransactionsByMonth(@PathVariable String date) {
        return transactionService.findTransactionsByMonth(date);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable long id) {
        transactionService.deleteTransaction(id);
    }
}
