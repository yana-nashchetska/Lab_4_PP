package pizza.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.Pizza;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String ingredients;

    @NotNull
    @NotBlank
    private String sauce;

    @Positive
    private BigDecimal price;


    public static PizzaDTO toDTO(final Pizza pizza) {
        final PizzaDTO pizzaDTO = new PizzaDTO();

        pizzaDTO.setName(pizza.getPizzaName());
        pizzaDTO.setIngredients(pizza.getMainIngredient());
        pizzaDTO.setSauce(pizza.getPizzaSauce());
        pizzaDTO.setPrice(pizza.getPrice());


        return pizzaDTO;
    }
}
