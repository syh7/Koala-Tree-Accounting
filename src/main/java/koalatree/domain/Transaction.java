package koalatree.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import lombok.Builder;

@Entity
@Table(name = "TRANSACTIONS")
@Builder
public class Transaction{

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal amountTotal;

    private BigDecimal amountSjoerd;

    private BigDecimal amountLoes;

    private BigDecimal amountAll;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String message;

    private LocalDate date;

    private ZonedDateTime createdDateTimeUTC;
}
