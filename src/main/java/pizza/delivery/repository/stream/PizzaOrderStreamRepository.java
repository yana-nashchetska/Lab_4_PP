package pizza.delivery.repository.stream;

import org.springframework.stereotype.Repository;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.entity.SauceType;
import pizza.delivery.exceptions.BadRequestException;
import pizza.delivery.repository.PizzaOrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PizzaOrderStreamRepository  {
    private List<PizzaOrder> pizzaOrders = new ArrayList<>();
    private Long lastUsedId;


    private Long generateId() {
        ++lastUsedId;
        return lastUsedId;
    }
    public PizzaOrder saveToBasket(final PizzaOrder pizzaOrder) {
        pizzaOrder.setId(generateId());
        pizzaOrders.add(pizzaOrder);
        return pizzaOrder;
    }

    // переміщений код з Ресурсів:
/*
    public void savePizzaOrder(@RequestBody PizzaOrder pizzaOrder){
        final PizzaOrderDTO pizzaOrderDTO = PizzaOrderDTO.toDTO(pizzaOrder);
        pizzaOrderService.save(pizzaOrderDTO);
    }*/

    // що робимо з сейвом?

    // edit - Наш update
    public void editPizzaOrder(CustomerDTO customerDTO, PizzaOrderDTO pizzaOrderDTO, SauceType sauceTypeAdded) {
        customerDTO.getBasket().stream()
                .filter(e -> e.getPizzaType().equals(pizzaOrderDTO.getPizzaType()))
                .findFirst()
                .ifPresentOrElse(
                        e -> {
                            e.setSauceType(sauceTypeAdded);
                        },
                        () -> {
                            throw new BadRequestException(String.format("PizzaOrder with id {%s} not found", pizzaOrderDTO.getPizzaType().getDisplayName()));
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
                .filter(e -> e.getId().equals(id))
                .collect(Collectors.toList());
    }

}
