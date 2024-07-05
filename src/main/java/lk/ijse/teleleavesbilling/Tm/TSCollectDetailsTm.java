package lk.ijse.teleleavesbilling.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TSCollectDetailsTm {
    private String Collect_ID;
    private String TS_ID;
    private String Amount_of_the_tea;
}
