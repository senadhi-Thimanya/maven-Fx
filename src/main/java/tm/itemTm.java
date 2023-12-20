package tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class itemTm {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private Button delete;
}
