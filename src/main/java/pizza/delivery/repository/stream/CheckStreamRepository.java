package pizza.delivery.repository.stream;

import org.springframework.stereotype.Repository;
import pizza.delivery.entity.Check;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CheckStreamRepository {
    private List<Check> checkList = new ArrayList<>();
    private final AtomicLong lastCheckId = new AtomicLong(1);

    public Check save(final Check check) {
        check.setId(lastCheckId.incrementAndGet());
        checkList.add(check);
        return check;
    }

    public List<Check> findAll() {
        return checkList;
    }


    public Optional<Check> findById(final Long id) {
        return checkList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void deleteById(final Long id) {
        checkList = checkList.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }
}
