
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Order {

    private int id;
    private List<CartItem> items;
    private double totalPrice;

    public void showOrder() {

        System.out.println("\n--- ЗАМОВЛЕННЯ №" + id + " ---");

        for (CartItem item : items) {
            System.out.println(item);
        }

        System.out.println("До сплати: " + totalPrice + " грн");
    }
}