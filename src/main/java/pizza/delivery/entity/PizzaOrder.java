package pizza.delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

// Клас для представлення замовлення піци
@Data
@AllArgsConstructor
@Entity
@Table(name = "pizzaOrders")
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PizzaType pizzaType;

    private SauceType sauceType;

    private boolean withCheeseCrust;

    // Метод для вибору типу піци //Нефінальна версія імплементації
    public PizzaOrder choosePizza(PizzaType pizzaType, SauceType sauceType, boolean withCheeseCrust) {
        PizzaOrder pizzaOrder = new PizzaOrder(0L, pizzaType, sauceType, withCheeseCrust);
        System.out.println("You chose " + pizzaType.getDisplayName() + " pizza with " +
                sauceType.getDisplayName() + " sauce and " +
                (withCheeseCrust ? "cheese crust." : "regular crust."));
        return pizzaOrder;
    }

    // Метод для отримання інформації про замовлену піцу
    public String toString() {
        if (pizzaType != null) {
            return "Your current pizza order: " + pizzaType.getDisplayName();
        } else {
            return "No pizza chosen yet.";
        }
    }
}

