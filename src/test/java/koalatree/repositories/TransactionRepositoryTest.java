package koalatree.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import koalatree.TestUtils;
import koalatree.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionRepository subject;

    @Test
    public void saveTransaction() {
        // given
        Transaction transaction = TestUtils.createTransaction();

        // when
        subject.save(transaction);

        // then
        // no error
        Transaction result = entityManager.find(Transaction.class, 1L);
        assertThat(result).isEqualTo(transaction);
    }

    @Test
    public void bla() {
        // given
        Transaction transaction = TestUtils.createTransaction();

        // when

        // then
    }
}