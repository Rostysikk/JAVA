
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Shop shop = new Shop();
        Cart cart = new Cart();

        while (true) {

            System.out.println("\n===== ІНТЕРНЕТ-МАГАЗИН =====");
            System.out.println("1. Переглянути каталог");
            System.out.println("2. Додати товар до кошика");
            System.out.println("3. Переглянути кошик");
            System.out.println("4. Зробити замовлення");
            System.out.println("0. Вийти");

            System.out.print("Виберіть дію: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    shop.showProducts();
                    break;

                case 2:

                    shop.showProducts();

                    System.out.print("Введіть ID товару: ");
                    int id = scanner.nextInt();

                    Product product = shop.getProductById(id);

                    if (product == null) {
                        System.out.println("Товар не знайдено.");
                        break;
                    }

                    System.out.print("Введіть кількість: ");
                    int quantity = scanner.nextInt();

                    if (quantity <= 0) {
                        System.out.println(
                                "Кількість повинна бути більше 0."
                        );
                        break;
                    }

                    cart.addProduct(product, quantity);

                    System.out.println(
                            "Товар додано до кошика."
                    );

                    break;

                case 3:
                    cart.showCart();
                    break;

                case 4:

                    Order order = shop.createOrder(cart);

                    if (order == null) {
                        System.out.println("Кошик порожній.");
                    } else {
                        System.out.println(
                                "Замовлення успішно створено!"
                        );

                        order.showOrder();
                    }

                    break;

                case 0:

                    System.out.println("До побачення!");

                    scanner.close();
                    return;

                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }
}