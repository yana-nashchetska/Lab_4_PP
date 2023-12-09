package pizza.delivery.service;

import org.springframework.web.bind.annotation.PathVariable;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.entity.Customer;
import pizza.delivery.entity.PizzaOrder;

import java.util.List;

public interface PizzaOrderService {
    public PizzaOrderDTO findDTOById(final Long id);

    public List<PizzaOrderDTO> findAll();

    public PizzaOrderDTO save(final PizzaOrderDTO pizzaOrderDTO);

    public void deleteById(Long id);

    void orderPizza(Long userId, Long pizzaOrderId);
}
