package pizza.delivery.resource;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.service.PizzaOrderService;

@RestController
@RequestMapping("/api/order-pizza")
@AllArgsConstructor
public class PizzaOrderResource {
    @Autowired
    private PizzaOrderService pizzaOrderService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> orderPizza(@PathVariable Long userId) {
        pizzaOrderService.orderPizza(userId, "Margarita");
        return ResponseEntity.ok().build();
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