package pizza.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pizza.delivery.entity.Customer;

import java.util.List;

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

    // TODO: think about id generation and authorization
    //TODO: think about money field - чи будемо його робити в принципі, бо типу мані не
    // повинні відображатись в базі даних
    // чи мані буде нулем, а будемо задавати?

    private Boolean isAuthorized = Boolean.FALSE; // буде перевірятись при покупці
    private List<PizzaOrderDTO> basket;// замовлення, які ще не підтверджені


    // Чи потрібно нам як параметри передавати також OrderDTO?
    public static CustomerDTO toDTO(final Customer customer, final PizzaOrderDTO pizzaOrderDTO) {
        final CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setIsAuthorized(customer.getIsAuthorized());

        // customerDTO.setBasket(customer.getBasket());
        // TODO: ПОДУМАТИ ЯК ПЕРЕДАТИ КОРЗИНКУ, ОСКІЛЬКИ КОРЗИНКА ПОВИННА БУТИ ТИПУ ОРДЕРДТО, А В НАС ПЕРЕДАЮТЬСЯ ДАНІ ВІД КОРИСТУВАЧА, А В НЬОГО
        //  КОРЗИНКА ТИПУ ОРДЕР ПРОСТО. МОЖЛИВО ТРЕБА БУДЕ ПЕРЕДАВАТИ ЛІСТ ОРДЕРІВ, А НЕ ОРДЕРДТО
        return customerDTO;
    }
}
