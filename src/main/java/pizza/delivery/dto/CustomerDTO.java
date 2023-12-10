package pizza.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.Customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;

    private Boolean isAuthorized = Boolean.FALSE;
    private List<PizzaOrderDTO> basket;

    private BigDecimal money = BigDecimal.valueOf(10_000);
    public static CustomerDTO toDTO(final Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setIsAuthorized(customer.getIsAuthorized());

        List<PizzaOrderDTO> pizzaOrderDTOs = customer.getBasket().stream()
                .map(PizzaOrderDTO::toDTO)
                .collect(Collectors.toList());

        customerDTO.setBasket(pizzaOrderDTOs);
        customerDTO.setMoney(customer.getMoney());

        return customerDTO;
    }

}
