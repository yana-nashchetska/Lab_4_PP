package pizza.delivery.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

    private boolean isConfirmed; // отак буде норм?
    public static PizzaOrderDTO toDTO(final PizzaOrder pizzaOrder) {
        final PizzaOrderDTO pizzaOrderDTO = new PizzaOrderDTO();

        pizzaOrderDTO.setPizzaType(pizzaOrder.getPizzaType());
        pizzaOrderDTO.setSauceType(pizzaOrder.getSauceType());
        pizzaOrderDTO.setWithCheeseCrust(pizzaOrderDTO.isWithCheeseCrust());
// чи отут не треба сетати цезначення? мав би бутти по ідеї метод, який би обирав це замовлення, тобто встановлював значення як тру
        // а тоді інший метод додавав би до чеку ті замовлення, в яких це поле тру
        return pizzaOrderDTO;
    }
}
