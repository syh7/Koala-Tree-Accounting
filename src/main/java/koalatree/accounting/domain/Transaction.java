package koalatree.accounting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "transaction_id")
    private Set<Entry> entries;

    private String message;

    private LocalDate date;

    @CreationTimestamp
    private ZonedDateTime createdDateTime;
}
