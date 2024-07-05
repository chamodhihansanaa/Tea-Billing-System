package lk.ijse.teleleavesbilling.Tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceTm {
    private  String   Category;
    private  String  Duration;
    private  String  KG_Price;
}
