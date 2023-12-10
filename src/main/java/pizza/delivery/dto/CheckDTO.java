package pizza.delivery.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.Check;
import pizza.delivery.entity.PizzaOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckDTO {
    private Long id;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private BigDecimal totalSum;

    @Valid
    @NotNull
    private List<PizzaOrder> orders;

    public static CheckDTO toDTO(final Check check) {
        final CheckDTO checkDTO = new CheckDTO();

        checkDTO.setId(check.getId());
        checkDTO.setDate(check.getDate());
        checkDTO.setTotalSum(check.getTotalSum());
        checkDTO.setOrders(check.getOrders());

        return checkDTO;
    }
}
