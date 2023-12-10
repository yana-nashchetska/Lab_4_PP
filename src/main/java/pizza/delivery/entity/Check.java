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
@Table(name = "checks")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;
    @Column
    private BigDecimal totalSum;

    @OneToMany(mappedBy = "check")
    private List<PizzaOrder> orders;

    @ManyToOne
    @JoinColumn
    private Customer customer;
}
