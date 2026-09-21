
import java.util.ArrayList;
import java.util.List;

public class Shop {

    private List<Product> products = new ArrayList<>();

    private int nextOrderId = 1;

    public Shop() {

        products.add(new Product(1, "Ноутбук", 30000));
        products.add(new Product(2, "Смартфон", 20000));
        products.add(new Product(3, "Навушники", 3000));
        products.add(new Product(4, "Клавіатура", 2500));
        products.add(new Product(5, "Мишка", 1200));
    }

    public void showProducts() {

        System.out.println("\n--- КАТАЛОГ ТОВАРІВ ---");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Product getProductById(int id) {

        for (Product product : products) {

            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    public Order createOrder(Cart cart) {

        if (cart.getItems().isEmpty()) {
            return null;
        }

        List<CartItem> orderItems =
                new ArrayList<>(cart.getItems());

        Order order = new Order(
                nextOrderId++,
                orderItems,
                cart.getTotalPrice()
        );

        cart.clear();

        return order;
    }
}