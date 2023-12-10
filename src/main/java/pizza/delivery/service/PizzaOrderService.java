package pizza.delivery.service;

import pizza.delivery.dto.PizzaOrderDTO;

import java.util.List;

public interface PizzaOrderService {
    PizzaOrderDTO findDTOById(final Long id);

    void addSauce(Long customerId, Long orderId, String sauceType);

    List<PizzaOrderDTO> findAll();

    PizzaOrderDTO save(final PizzaOrderDTO pizzaOrderDTO);

    void addCheeseCrust(Long customerId, Long orderId);
    void deleteFromBasket(Long orderId);

    void deleteAllFromBasket(Long customerId);

    void orderPizza(Long userId, Long pizzaOrderId, String pizzaType);
}
