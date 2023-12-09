package pizza.delivery.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.service.PizzaOrderService;
import pizza.delivery.dto.PizzaOrderDTO;


@RestController
@RequestMapping("/pizza")
@RequiredArgsConstructor
public class PizzaOrderResource {

    private final PizzaOrderService pizzaOrderService;


    @PostMapping("/{userId}/{orderId}")
    public void orderPizza(@PathVariable Long userId, @PathVariable Long pizzaOrderId) {
        pizzaOrderService.orderPizza(userId, pizzaOrderId);
    }
}
