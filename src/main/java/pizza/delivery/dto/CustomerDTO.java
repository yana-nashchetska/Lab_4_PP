package pizza.delivery.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;

    private Boolean isAuthorized = Boolean.FALSE;

    @Valid
    @NotNull
    @Size(min = 1, max = 10)
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
