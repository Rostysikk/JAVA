package ua.edu.finance;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;
/** Статичні операції аналізу, без збереження стану. */
public abstract class TransactionAnalyzer {
    private TransactionAnalyzer() { }
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter MONTH = DateTimeFormatter.ofPattern("MM-uuuu");
    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        YearMonth period = YearMonth.parse(monthYear, MONTH);
        return (int) transactions.stream().filter(t -> YearMonth.from(date(t)).equals(period)).count();
    }
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream().filter(t -> t.getAmount() < 0)
            .sorted(Comparator.comparingDouble(Transaction::getAmount)).limit(10).collect(Collectors.toList());
    }
    public static Optional<Transaction> findLargestExpense(List<Transaction> transactions, LocalDate from, LocalDate to) {
        return expensesInPeriod(transactions, from, to).min(Comparator.comparingDouble(Transaction::getAmount));
    }
    public static Optional<Transaction> findSmallestExpense(List<Transaction> transactions, LocalDate from, LocalDate to) {
        return expensesInPeriod(transactions, from, to).max(Comparator.comparingDouble(Transaction::getAmount));
    }
    private static Stream<Transaction> expensesInPeriod(List<Transaction> transactions, LocalDate from, LocalDate to) {
        if (from.isAfter(to)) throw new IllegalArgumentException("Початок періоду пізніше кінця");
        return transactions.stream().filter(t -> t.getAmount() < 0)
            .filter(t -> !date(t).isBefore(from) && !date(t).isAfter(to));
    }
    public static Map<String, Double> expensesByCategory(List<Transaction> transactions) {
        return transactions.stream().filter(t -> t.getAmount() < 0)
            .collect(Collectors.groupingBy(Transaction::getDescription, TreeMap::new,
                Collectors.summingDouble(t -> -t.getAmount())));
    }
    public static Map<String, Double> expensesByMonth(List<Transaction> transactions) {
        return transactions.stream().filter(t -> t.getAmount() < 0)
            .collect(Collectors.groupingBy(t -> date(t).format(MONTH), TreeMap::new,
                Collectors.summingDouble(t -> -t.getAmount())));
    }
    private static LocalDate date(Transaction t) { return LocalDate.parse(t.getDate(), DATE); }
}
