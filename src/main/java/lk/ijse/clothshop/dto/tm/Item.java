package lk.ijse.clothshop.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Item {
    String itemId;
    String itemName;
    int quntity;
    String clothBrand;
}
