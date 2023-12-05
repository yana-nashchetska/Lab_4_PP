package pizza.delivery.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.Order;
import pizza.delivery.entity.Pizza;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    @NotNull
    @NotBlank
    private Pizza pizza;
    @Positive
    private Integer quantity;

    public static OrderDTO toDTO(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setPizza(order.getPizza());

        return orderDTO;
    }
}
