package pizza.delivery.service;

import org.springframework.web.bind.annotation.PathVariable;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.entity.Customer;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.entity.SauceType;

import java.util.List;

public interface PizzaOrderService {
    PizzaOrderDTO findDTOById(final Long id);

    void addSauce(Long orderId, SauceType sauceType);

    List<PizzaOrderDTO> findAll();

    PizzaOrderDTO save(final PizzaOrderDTO pizzaOrderDTO);

    void deleteById(Long id);

    void orderPizza(Long userId, Long pizzaOrderId);
}
