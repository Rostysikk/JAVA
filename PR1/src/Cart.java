
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {

        for (CartItem item : items) {

            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public void showCart() {

        if (items.isEmpty()) {
            System.out.println("Кошик порожній.");
            return;
        }

        System.out.println("\n--- КОШИК ---");

        for (CartItem item : items) {
            System.out.println(item);
        }

        System.out.println("Загальна сума: " + getTotalPrice() + " грн");
    }

    public double getTotalPrice() {

        double total = 0;

        for (CartItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public void clear() {
        items.clear();
    }
}