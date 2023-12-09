package pizza.delivery.entity;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "check")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP") //FIXME: fix problem with DB
    private LocalDateTime date;
    @Column
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "id")
    private List<PizzaOrder> orders;
}
