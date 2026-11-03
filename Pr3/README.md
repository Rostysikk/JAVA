# Calculator — готовий проєкт для IntelliJ IDEA

## Вимоги
- JDK 17 або новіша версія
- IntelliJ IDEA Community
- Інтернет при першому запуску тестів (для завантаження JUnit через Maven)

## Як відкрити
1. Розпакуйте архів Calculator_IntelliJ.zip.
2. Відкрийте IntelliJ IDEA → **File → Open**.
3. Виберіть папку **Calculator_IntelliJ** (або файл **pom.xml**) і натисніть **Open as Project**.
4. Якщо IntelliJ запитає про довіру до проєкту, виберіть **Trust Project**.
5. Дочекайтеся завантаження Maven. Перевірте, що вибрано **JDK 17+** у **File → Project Structure → Project**.
6. Відкрийте `src/main/java/com/example/calculator/Main.java` і натисніть зелений трикутник біля `main` → **Run 'Main.main()'**.
7. Вводьте операцію (+, -, *, /, sqrt) і числа в нижній вкладці **Run**. Для `sqrt` потрібно лише одне число.

## Як запустити тести
Відкрийте `src/test/java/com/example/calculator/CalculatorTest.java` і натисніть зелений трикутник біля назви класу → **Run 'CalculatorTest'**.
Або виконайте в терміналі проєкту: `mvn test`.

## Приклади
- `/`, `10`, `0` → повідомлення про ділення на нуль.
- `+`, `текст` → повідомлення про неправильний формат числа.
- `sqrt`, `-9` → власне виключення `InvalidInputException`.
- `*`, `3`, `4` → `Результат: 12.0`.

Кожен запит завершується повідомленням з блоку `finally`.
Від’ємні числа дозволені в чотирьох базових арифметичних операціях. `NaN` і `Infinity` заборонені.
