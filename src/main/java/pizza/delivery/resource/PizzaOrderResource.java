package pizza.delivery.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.repository.PizzaOrderRepository;

@RestController
@RequestMapping("/pizza")
public class PizzaOrderResource {
    @Autowired
    private PizzaOrderRepository pizzaRepository;

    @GetMapping("/{id}")
    public PizzaOrder getPizza(final @PathVariable Long id){
        return pizzaRepository.findById(id);
    }

    @PostMapping("/")
    public String addPizza(final @RequestBody PizzaOrder pizza) {
        pizzaRepository.save(pizza);
        return "It works!";
    }
}
