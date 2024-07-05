package lk.ijse.teleleavesbilling.Tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersTm {
    private  String OR_ID;
    private  String Date;
    private  String Address;
    private  String Quantity;
}
