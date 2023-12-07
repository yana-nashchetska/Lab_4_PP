package pizza.delivery.repository;

import org.springframework.stereotype.Repository;
import pizza.delivery.entity.Pizza;

import java.util.ArrayList;
import java.util.List;

//I'm not sure if it will be in the final code, but for the testing it should be fine
@Repository
public class PizzaRepository {
    public List<Pizza> pizzaList = new ArrayList<>();

    private Long lastPizzaId = 0L;

    private Long generateId(){
        ++lastPizzaId;
        return lastPizzaId;
    }

    public void save(Pizza pizza) {
        pizza.setId(generateId());
        pizzaList.add(pizza);
    }

    public Pizza findById(final Long id) {
        return pizzaList.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }
}
