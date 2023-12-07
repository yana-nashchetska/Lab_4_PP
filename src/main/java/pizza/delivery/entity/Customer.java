package pizza.delivery.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    //FIXME: fix problem with DB
    //TODO: think about id generation and authorization
 @Id // вказуємо, що це поле є унікальним ідентифікатором
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private BigDecimal money; // будуть відніматись, при покупці
    // !!!подумати, чи будуть BigDecimal.ZER0  чи будемо задавати початкову суму
    @Column
    private Boolean isAuthorized = Boolean.FALSE; // буде перевірятись при покупці

    @OneToMany(mappedBy = "id")
    private List<Check> checkList;

    @OneToMany(mappedBy = "id")
    private List<Order> basket;// щамовлення, які ще не підтверджені, та які не пішли в чек
}
