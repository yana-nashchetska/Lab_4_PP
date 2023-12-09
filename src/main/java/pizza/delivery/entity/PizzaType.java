package pizza.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Перерахування для представлення видів піц //Пробую використати ломбок, не на 100% впевнений в імплементації
@Getter
@AllArgsConstructor
public enum PizzaType {
    MARGHERITA("Margherita"),
    PEPPERONI("Pepperoni"),
    VEGETARIAN("Vegetarian"),
    SEAFOOD("Seafood");

    private final String displayName;
}

