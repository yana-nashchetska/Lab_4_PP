package pizza.delivery.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.Check;
import pizza.delivery.entity.Order;

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
    private BigDecimal totalPrice;

    @NotNull
    private List<Order> orders;

    public static CheckDTO toDTO(final Check check) {
        final CheckDTO checkDTO = new CheckDTO();

        checkDTO.setId(check.getId());
        checkDTO.setDate(check.getDate());
        checkDTO.setTotalPrice(check.getTotalPrice());
        checkDTO.setOrders(check.getOrders());

        return checkDTO;
    }
}