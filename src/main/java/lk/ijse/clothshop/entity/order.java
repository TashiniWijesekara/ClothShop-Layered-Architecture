package lk.ijse.clothshop.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class order {
    String orderId;
    Date date;
    double price;
    String custId;
    String item;
    double itemPrice;
    double bonus;
    String amount;
}
