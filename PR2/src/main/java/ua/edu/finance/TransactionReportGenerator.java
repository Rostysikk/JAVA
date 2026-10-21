package ua.edu.finance;
import java.util.*;
/** Відображення звітів; усі методи статичні. */
public abstract class TransactionReportGenerator {
    private TransactionReportGenerator() { }
    public static void printBalanceReport(double balance) {
        System.out.printf(Locale.US, "Загальний баланс: %.2f грн%n", balance);
    }
    public static void printTransactionsCountByMonth(String month, int count) {
        System.out.printf("Кількість транзакцій за %s: %d%n", month, count);
    }
    public static void printTopExpensesReport(List<Transaction> expenses) {
        System.out.println("10 найбільших витрат:");
        expenses.forEach(t -> System.out.printf(Locale.US, "%s (%s): %.2f грн%n", t.getDescription(), t.getDate(), t.getAmount()));
    }
    public static void printExpenseExtremes(Optional<Transaction> largest, Optional<Transaction> smallest) {
        System.out.println("Найбільша витрата: " + largest.map(Object::toString).orElse("немає"));
        System.out.println("Найменша витрата: " + smallest.map(Object::toString).orElse("немає"));
    }
    /** Одна зірочка = 1000 грн; неповну тисячу округлюємо вгору. */
    public static void printExpenseHistogram(String heading, Map<String, Double> totals, double unit) {
        if (unit <= 0) throw new IllegalArgumentException("Масштаб має бути додатним");
        System.out.println(heading + " (1 * = " + unit + " грн):");
        totals.forEach((name, total) -> {
            long bars = (long) Math.ceil(total / unit);
            System.out.printf(Locale.US, "%s: %.2f грн %s%n", name, total, "*".repeat(Math.toIntExact(bars)));
        });
    }
}
