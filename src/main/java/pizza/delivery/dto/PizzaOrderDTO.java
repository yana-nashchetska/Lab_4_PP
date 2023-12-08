package pizza.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.entity.PizzaType;
import pizza.delivery.entity.SauceType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaOrderDTO {
    @NotNull
    @NotBlank
    private PizzaType pizzaType;

    @NotNull
    @NotBlank
    private SauceType sauceType;

    @NotNull
    @NotBlank
    private boolean withCheeseCrust;

    public static PizzaOrderDTO toDTO(final PizzaOrder pizzaOrder) {
        final PizzaOrderDTO pizzaOrderDTO = new PizzaOrderDTO();

        pizzaOrderDTO.setPizzaType(pizzaOrder.getPizzaType());
        pizzaOrderDTO.setSauceType(pizzaOrder.getSauceType());
        pizzaOrderDTO.setWithCheeseCrust(pizzaOrderDTO.isWithCheeseCrust());

        return pizzaOrderDTO;
    }
}
