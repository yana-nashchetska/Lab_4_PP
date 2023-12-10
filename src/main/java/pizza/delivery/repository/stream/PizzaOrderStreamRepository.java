package pizza.delivery.repository.stream;

import org.springframework.stereotype.Repository;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PizzaOrderStreamRepository  {
    private List<PizzaOrder> pizzaOrders = new ArrayList<>();
    private final AtomicLong lastUsedId = new AtomicLong(0);

    public PizzaOrder saveToBasket(final PizzaOrder pizzaOrder) {
        pizzaOrder.setId(lastUsedId.incrementAndGet());
        pizzaOrders.add(pizzaOrder);
        return pizzaOrder;
    }

    public void editPizzaOrder(CustomerDTO customerDTO, PizzaOrderDTO pizzaOrderDTO, String sauceTypeAdded) {
        customerDTO.getBasket().stream()
                .filter(e -> e.getPizzaType().equals(pizzaOrderDTO.getPizzaType()))
                .findFirst()
                .ifPresentOrElse(
                        e -> {
                            e.setSauceType(sauceTypeAdded);
                        },
                        () -> {
                            throw new BadRequestException(String.format("PizzaOrder with id {%s} not found", pizzaOrderDTO.getPizzaType()));
                        }
                );
    }

    public Optional<PizzaOrder> findById(final Long id){
        return pizzaOrders.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void deleteById(final Long id) {
        pizzaOrders = pizzaOrders.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

}
