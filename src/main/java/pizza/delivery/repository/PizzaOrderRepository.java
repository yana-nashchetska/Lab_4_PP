package pizza.delivery.repository;

import org.springframework.stereotype.Repository;
import pizza.delivery.entity.PizzaOrder;

import java.util.ArrayList;
import java.util.List;

//Тестовий код, треба замінити на фінальну версію ASAP
@Repository
public class PizzaOrderRepository {
    public List<PizzaOrder> pizzaList = new ArrayList<>();

    private Long lastPizzaId = 0L;

    private Long generateId(){
        ++lastPizzaId;
        return lastPizzaId;
    }

    public void save(PizzaOrder pizza) {
        pizza.setId(generateId());
        pizzaList.add(pizza);
    }

    public PizzaOrder findById(final Long id) {
        return pizzaList.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }
}
