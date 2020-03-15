package koalatree.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import koalatree.serialization.TransactionDeserializer;
import koalatree.serialization.TransactionSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = TransactionSerializer.class)
@JsonDeserialize(using = TransactionDeserializer.class)
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amountTotal;

    private BigDecimal amountSjoerd;

    private BigDecimal amountLoes;

    private BigDecimal amountAll;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String message;

    private LocalDate date;

    @CreatedDate
    private ZonedDateTime createdDateTime;
}
