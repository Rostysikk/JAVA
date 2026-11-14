# OrderManagementSystem

Навчальний багатомодульний Java-проєкт: управління замовленнями інтернет-магазину.

## Вимоги
- JDK **17** (або 21)
- IntelliJ IDEA Community / Ultimate
- Maven (IntelliJ може завантажити Maven самостійно)
- Інтернет під час першого імпорту для завантаження Lombok та JavaFaker

## Запуск у IntelliJ IDEA
1. Розпакуйте ZIP у папку без кирилиці в шляху (наприклад, `C:\Projects\OrderManagementSystem`).
2. В IntelliJ IDEA виберіть **File → Open** та відкрийте кореневу папку `OrderManagementSystem` (де лежить головний `pom.xml`).
3. Погодьтеся з **Trust Project**, дочекайтеся імпорту Maven. Якщо потрібно, натисніть **Load Maven Changes**.
4. Встановіть **Project SDK = JDK 17**: File → Project Structure → Project.
5. Перевірте **Settings → Build, Execution, Deployment → Compiler → Annotation Processors → Enable annotation processing**. Для Lombok також бажано встановити плагін Lombok (у нових версіях IDEA підтримка може бути вбудованою).
6. Відкрийте `order-app/src/main/java/ua/edu/shop/app/Main.java` та натисніть зелений трикутник біля `main()` → **Run 'Main.main()'**.
7. Альтернатива через Maven: у терміналі з кореневої папки виконайте `mvn clean install`, потім `mvn -pl order-app exec:java -Dexec.mainClass=ua.edu.shop.app.Main` (для багатомодульного reactor зручніше запускати клас Main безпосередньо в IDEA).

## Структура
- **order-processing** — `Product`, `Electronics`, `Clothing`, `Order<T extends Product>`, `OrderProcessor<T extends Product>`, винятки.
- **order-storage** — `OrderRepository`, фабрика `Repositories`, потокобезпечна реалізація на `ConcurrentHashMap`.
- **order-concurrency** — `OrderExecutor`, пул із 4 потоків, `Future`, власна фабрика потоків.
- **order-app** — точка входу `Main` і генератор даних `DemoDataFactory` з JavaFaker.

Три бібліотечні модулі містять `module-info.java` з `requires` і `exports`. Модуль `order-app` є звичайним Maven-модулем без `module-info.java`: це спрощує використання стороннього JavaFaker у точці входу, не скасовуючи JPMS-інкапсуляцію трьох бібліотечних модулів під час модульного запуску. Важливо: якщо запускати все через звичайний classpath, JVM не забезпечує JPMS-інкапсуляцію; для її демонстрації потрібен запуск через module path.

## Відповідність завданню
| Вимога | Реалізація |
|---|---|
| Generics і `extends` | `Order<T extends Product>`, `OrderProcessor<T extends Product>` |
| Builder | `@Builder` на конструкторах `Electronics`, `Clothing` |
| JavaFaker | `DemoDataFactory` |
| Лямбди | `Main`, `OrderExecutor.submit()` |
| Method references | `processor::processOrder`, `System.out::println`, `Order::getTotal` |
| Багатопоточність | `ExecutorService`, `Future`, `NamedThreadFactory` |
| Винятки | `OrderProcessingException`, валідація конструкторів, обробка `InterruptedException` |
| Синхронізація | `ConcurrentHashMap`, `AtomicReference<OrderStatus>` |
| Java modules | `module-info.java` у трьох бібліотечних модулях |
| Контроль доступу | Пакети `*.internal` не експортуються у `module-info.java` |

## Що побачите в консолі
Паралельну обробку 12 замовлень у потоках `order-worker-1` ... `order-worker-4`, перелік замовлень зі статусами та підсумкову статистику. Назви товарів генеруються випадково.

## Примітки
- Сховище **in-memory**, тобто дані не переживають завершення програми; база даних за умовою не потрібна.
- Обробка замовлень і затримка `Thread.sleep(120)` імітують роботу зовнішнього сервісу, а не реальну оплату.
- Ліміт суми 100000 UAH демонструє бізнес-виняток.
- JavaFaker використовується для демонстраційних даних, не для реального каталогу.
