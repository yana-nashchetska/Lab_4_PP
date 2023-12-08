package pizza.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Та ж імплементація, що і в PizzaType enum'і
@Getter
@AllArgsConstructor
public enum SauceType {
    BBQ("BBQ Sauce"),
    GARLIC("Garlic Sauce"),
    SWNSR("Sweet&Sour Sauce"),
    CHILI("Chili Sauce"),
    TOMATO("Tomato Sauce"),
    PESTO("Pesto Sauce");


    private final String displayName;
}
