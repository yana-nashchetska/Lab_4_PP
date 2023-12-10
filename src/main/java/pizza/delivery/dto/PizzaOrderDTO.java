package pizza.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.PizzaOrder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaOrderDTO {
    private long id;
    @NotNull
    @NotBlank
    private String pizzaType = "Margherita";

    @NotNull
    @NotBlank
    private String sauceType = "Default";

    @NotNull
    @NotBlank
    private Boolean withCheeseCrust = false;

    private Boolean confirmed = false;

    public static PizzaOrderDTO toDTO(final PizzaOrder pizzaOrder) {
        final PizzaOrderDTO pizzaOrderDTO = new PizzaOrderDTO();

        pizzaOrderDTO.setPizzaType(pizzaOrder.getPizzaType());
        pizzaOrderDTO.setSauceType(pizzaOrder.getSauceType());
        pizzaOrderDTO.setWithCheeseCrust(pizzaOrder.isWithCheeseCrust());
        pizzaOrderDTO.setConfirmed(pizzaOrder.isConfirmed());

        return pizzaOrderDTO;
    }
}