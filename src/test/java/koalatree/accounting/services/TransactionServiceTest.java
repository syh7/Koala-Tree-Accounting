package koalatree.accounting.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import koalatree.accounting.TestUtils;
import koalatree.accounting.domain.Transaction;
import koalatree.accounting.exceptions.TransactionNotFoundException;
import koalatree.accounting.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService subject;

    @Test
    public void findAllTransactions_shouldCallRepository() {
        // given
        Transaction transaction = TestUtils.createTransaction();
        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(transaction));

        // when
        List<Transaction> result = subject.findAllTransactions();

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(transaction);
    }

    @Test
    public void findTransactionById_shouldCallRepository() {
        // given
        long id = 1L;
        Transaction transaction = TestUtils.createTransaction();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        // when
        Transaction result = subject.findTransactionById(id);

        // then
        assertThat(result).isEqualTo(transaction);
    }

    @Test
    public void findTransactionById_shouldThrowNotFound() {
        // given
        long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        // when + then
        assertThrows(TransactionNotFoundException.class, () -> subject.findTransactionById(id));
    }

    @Test
    public void saveTransaction_shouldCallRepository() {
        // given
        Transaction transaction = TestUtils.createTransaction();
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // when
        Transaction result = subject.saveTransaction(transaction);

        // then
        assertThat(result).isEqualTo(transaction);
    }

    @Test
    public void deleteTransaction_shouldCallRepository() {
        // given
        long id = 1L;

        // when
        subject.deleteTransaction(id);

        // then
        verify(transactionRepository).deleteById(id);
    }

    @Test
    public void findTransactionsByDate_shouldParseCorrectly() {
        // given
        LocalDate now = LocalDate.now();
        Transaction transaction = TestUtils.createTransaction();
        when(transactionRepository.findTransactionsByDate(now)).thenReturn(Collections.singletonList(transaction));

        // when
        List<Transaction> result = subject.findTransactionsByDate(now.format(DateTimeFormatter.ISO_DATE));

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(transaction);
    }

    @Test
    public void findTransactionsByMonth_shouldCallRepository() {
        // given
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1).minusDays(1);
        LocalDate endDate = now.with(TemporalAdjusters.firstDayOfNextMonth());
        Transaction transaction = TestUtils.createTransaction();
        when(transactionRepository.findTransactionsByDateBetween(startDate, endDate))
                .thenReturn(Collections.singletonList(transaction));

        // when
        List<Transaction> result = subject.findTransactionsByMonth(now.format(DateTimeFormatter.ISO_DATE));

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(transaction);
    }
}