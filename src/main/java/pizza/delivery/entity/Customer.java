package pizza.delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column
 private String firstName;
 @Column
 private String lastName;
 @Column
 private BigDecimal money; // will be deducted when purchasing

 // !!! Consider whether it will be BigDecimal.ZERO or if we will set an initial amount
 @Column
 private Boolean isAuthorized = Boolean.FALSE; // will be checked when purchasing
 @Column
 private Boolean isActive = Boolean.TRUE;

 @OneToMany(mappedBy = "customer")
 private List<Check> checkList;

 @OneToMany(mappedBy = "customer")
 private List<PizzaOrder> basket = new ArrayList<>();
}
