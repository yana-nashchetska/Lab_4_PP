package pizza.delivery.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.service.PizzaOrderService;

@RestController
@RequestMapping("/pizza")
@RequiredArgsConstructor
public class PizzaOrderResource {

    private final PizzaOrderService pizzaOrderService;

    @PostMapping("/order")
    public void save(@RequestBody PizzaOrder pizzaOrder){
        pizzaOrderService.saveOrder(pizzaOrder);
    }
}
