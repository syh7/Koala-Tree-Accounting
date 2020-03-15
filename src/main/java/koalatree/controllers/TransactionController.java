package koalatree.controllers;

import koalatree.domain.Transaction;
import koalatree.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/")
    public Iterable getAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable long id) {
        return transactionService.findTransactionById(id);
    }

//    @GetMapping("/{date}")
//    @ResponseBody
//    public List<Transaction> getTransactionsByDate(@PathVariable LocalDate date) {
//        return transactionService.findTransactionsByDate(date);
//    }

    @PostMapping("/{id}")
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable long id) {
        transactionService.deleteTransaction(id);
    }
}
