package pizza.delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pizza")
//FIXME: fix problem with DB. Чому не створюється таблиця?
//FIXME: чому не можна створити піцу без ідентифікатора?
public class Pizza {
    @Id // що це за поле внизу я пишу???
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String pizzaName;
    @Column
    private String mainIngredient;
    @Column
    private String pizzaSauce;
    @Column
    private BigDecimal price;

}
