package koalatree.accounting.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import koalatree.accounting.domain.Transaction;
import koalatree.accounting.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController subject;

    @Test
    public void findAllTransactions_shouldCallService() {
        // given
        Transaction transaction = Transaction.builder().message("test transaction").build();
        when(transactionService.findAllTransactions()).thenReturn(Collections.singletonList(transaction));

        // when
        List<Transaction> result = subject.findAllTransactions();

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(transaction);
    }

    @Test
    public void saveTransaction_shouldCallService() {
        // given
        Transaction transaction = Transaction.builder().message("test transaction").build();
        when(transactionService.saveTransaction(transaction)).thenReturn(transaction);

        // when
        Transaction result = subject.saveTransaction(transaction);

        // then
        assertThat(result).isEqualTo(transaction);
    }

    @Test
    public void findTransactionById_shouldCallService() {
        // given
        long id = 1;
        Transaction transaction = Transaction.builder().message("test transaction").build();
        when(transactionService.findTransactionById(id)).thenReturn(transaction);

        // when
        Transaction result = subject.findTransactionById(id);

        // then
        assertThat(result).isEqualTo(transaction);
    }

    @Test
    public void findTransactionsByDate_shouldCallService() {
        // given
        String date = "date";
        Transaction transaction = Transaction.builder().message("test transaction").build();
        when(transactionService.findTransactionsByDate(date)).thenReturn(Collections.singletonList(transaction));

        // when
        List<Transaction> result = subject.findTransactionsByDate(date);

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(transaction);
    }

    @Test
    public void findTransactionsByMonth_shouldCallService() {
        // given
        String date = "date";
        Transaction transaction = Transaction.builder().message("test transaction").build();
        when(transactionService.findTransactionsByMonth(date)).thenReturn(Collections.singletonList(transaction));

        // when
        List<Transaction> result = subject.findTransactionsByMonth(date);

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(transaction);
    }

    @Test
    public void deleteTransaction_shouldCallService() {
        // given
        long id = 1;

        // when
        subject.deleteTransaction(id);

        // then
        verify(transactionService).deleteTransaction(id);
    }
}