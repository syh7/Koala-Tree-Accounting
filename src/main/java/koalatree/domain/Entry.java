package koalatree.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import koalatree.serialization.EntryDeserializer;
import koalatree.serialization.EntrySerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ENTRIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = EntrySerializer.class)
@JsonDeserialize(using = EntryDeserializer.class)
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    private BigDecimal amount;

    @CreationTimestamp
    private ZonedDateTime createdDateTime;

}
