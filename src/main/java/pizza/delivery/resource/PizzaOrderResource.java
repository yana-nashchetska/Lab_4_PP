package pizza.delivery.resource;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.service.PizzaOrderService;
import pizza.delivery.dto.PizzaOrderDTO;


@RestController
@RequestMapping("/order-pizza")
@AllArgsConstructor
public class PizzaOrderResource {

    private PizzaOrderService pizzaOrderService;
    @PostMapping("/{userId}/{orderId}/{pizzaType}")
    public void orderPizza(@PathVariable Long userId, @PathVariable Long orderId, @PathVariable String pizzaType) {
        pizzaOrderService.orderPizza(userId, orderId,pizzaType);
    }
    @PostMapping("/{customerId}/{orderId}/add-sauce")
    public void addSauceToPizza(@PathVariable String customerId, @PathVariable Long orderId, @RequestBody String sauceType) {
        pizzaOrderService.addSauce(Long.valueOf(customerId), orderId, sauceType);
    }

    @PostMapping("/{customerId}/{orderId}/add-cheese-crust")
    public void addCheeseCrust(@PathVariable String customerId, @PathVariable Long orderId) {
        pizzaOrderService.addCheeseCrust(Long.valueOf(customerId), orderId);
    }

    @DeleteMapping("/{orderId}")
    public void deleteFromBasket(@PathVariable Long orderId) {
        pizzaOrderService.deleteFromBasket(orderId);
    }

    @DeleteMapping("/{customerId}/delete-all")
    public void deleteAllFromBasket(@PathVariable Long customerId) {
        pizzaOrderService.deleteAllFromBasket(customerId);
    }

    @GetMapping("/{orderId}")
    public PizzaOrderDTO findDTOById(@PathVariable Long orderId) {
        return pizzaOrderService.findDTOById(orderId);
    }
}
