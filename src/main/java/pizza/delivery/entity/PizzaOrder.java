package pizza.delivery.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

    private String pizzaType = "Margherita";

    private String sauceType = "Default";

    private boolean withCheeseCrust = false;

    private boolean isConfirmed = false;

    private BigDecimal price = BigDecimal.valueOf(100.5);

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
        if (pizzaType != null) {
            return "Your current pizza order: " + pizzaType;
        } else {
            return "No pizza chosen yet.";
        }
    }
}

