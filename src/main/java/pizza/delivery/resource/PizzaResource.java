package pizza.delivery.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.entity.Pizza;
import pizza.delivery.repository.PizzaRepository;

@RestController
@RequestMapping("/pizza")
public class PizzaResource {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/{id}")
    public Pizza getPizza(final @PathVariable Long id){
        return pizzaRepository.findById(id);
    }

    @PostMapping("/")
    public String addPizza(final @RequestBody Pizza pizza) {
        pizzaRepository.save(pizza);
        return "It works!";
    }
}
