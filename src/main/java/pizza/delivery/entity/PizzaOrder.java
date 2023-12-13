package pizza.delivery.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pizzaOrders")
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String pizzaType = "Margherita";
    @Column
    private String sauceType = "Default";
    @Column
    private boolean withCheeseCrust = false;
    @Column
    private boolean isConfirmed = false;
    @Column
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Check check;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public void setSauceType(String sauceType) {
        this.sauceType = sauceType;
    }

    public void setWithCheeseCrust(boolean withCheeseCrust) {
        this.withCheeseCrust = withCheeseCrust;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        if (!"Margherita".equals(pizzaType)) {
            return "Your current pizza order: " + pizzaType;
        } else {
            return "No pizza chosen yet.";
        }
    }
}