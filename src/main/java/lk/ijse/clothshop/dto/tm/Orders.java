package lk.ijse.clothshop.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Orders {
    String orderId;
    Date date;
    double price;
    String custId;
    String item;
    double itemPrice;
    double bonus;
    String amount;
}
