package pizza.delivery.exceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ExceptionResponseDTO{
    private String message;
}
