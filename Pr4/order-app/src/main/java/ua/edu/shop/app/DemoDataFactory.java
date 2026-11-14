package ua.edu.shop.app;

import com.github.javafaker.Faker;
import ua.edu.shop.processing.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class DemoDataFactory {
    private final Faker faker = new Faker(new Locale("uk"));

    // Назви товарів підібрані за категоріями; Faker випадково обирає позицію.
    private static final String[] ELECTRONICS_NAMES = {
        "Смартфон", "Ноутбук", "Бездротові навушники", "Планшет",
        "Розумний годинник", "Портативна колонка", "Монітор", "Клавіатура",
        "Комп’ютерна миша", "Зовнішній SSD", "Вебкамера", "Павербанк"
    };
    private static final String[] ELECTRONICS_BRANDS = {
        "Samsung", "Lenovo", "Sony", "Xiaomi", "ASUS", "JBL", "Acer", "Logitech"
    };
    private static final String[] CLOTHING_NAMES = {
        "Футболка", "Джинси", "Худі", "Світшот", "Куртка", "Сорочка",
        "Спортивні штани", "Сукня", "Светр", "Шорти", "Поло", "Жилет"
    };
    private static final String[] MATERIALS = {
        "Бавовна", "Поліестер", "Денім", "Трикотаж", "Льон"
    };

    private String randomItem(String[] values) {
        return values[faker.random().nextInt(values.length)];
    }
    public List<Order<Electronics>> electronics(int count) {
        List<Order<Electronics>> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Electronics item = Electronics.builder()
                .id(UUID.randomUUID().toString().substring(0, 8))
                .name(randomItem(ELECTRONICS_NAMES))
                .brand(randomItem(ELECTRONICS_BRANDS))
                .price(1500 + i * 350)
                .warrantyMonths(12 + (i % 3) * 12)
                .build();
            result.add(new Order<>("EL-" + (1001 + i), item, 1 + i % 3));
        }
        return result;
    }
    public List<Order<Clothing>> clothing(int count) {
        String[] sizes = {"S", "M", "L", "XL"};
        List<Order<Clothing>> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Clothing item = Clothing.builder()
                .id(UUID.randomUUID().toString().substring(0, 8))
                .name(randomItem(CLOTHING_NAMES))
                .price(450 + i * 110)
                .size(sizes[i % sizes.length])
                .material(randomItem(MATERIALS))
                .build();
            result.add(new Order<>("CL-" + (2001 + i), item, 1 + i % 4));
        }
        return result;
    }
}
