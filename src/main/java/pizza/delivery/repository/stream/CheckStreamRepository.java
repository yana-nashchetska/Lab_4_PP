package pizza.delivery.repository.stream;

import pizza.delivery.entity.Check;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CheckStreamRepository {
    private List<Check> checkList = new ArrayList<>();

    private Long lastCheckId = 0L;

    private Long generateId() {
        ++lastCheckId;
        return lastCheckId;
    }

    public Check save(final Check check) {
        check.setId(generateId());
        checkList.add(check);
        return check;
    }

    public List<Check> findAll() {
        return checkList;
    }

    // TODO: Check update

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
