package ua.edu.finance;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class TransactionAnalyzerTest {
    private final List<Transaction> transactions = List.of(
        new Transaction("01-02-2023", 100, "Зарплата"),
        new Transaction("15-02-2023", -50, "Кафе"),
        new Transaction("05-03-2023", -150, "Кафе"),
        new Transaction("06-03-2023", -20, "Транспорт"));
    @Test void testCalculateTotalBalance() { assertEquals(-120, TransactionAnalyzer.calculateTotalBalance(transactions)); }
    @Test void testCountTransactionsByMonth() {
        assertEquals(2, TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023"));
        assertEquals(2, TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023"));
        assertEquals(0, TransactionAnalyzer.countTransactionsByMonth(transactions, "04-2023"));
    }
    @Test void testFindTopExpenses() {
        List<Transaction> many = new ArrayList<>();
        for (int i = 1; i <= 15; i++) many.add(new Transaction("01-02-2023", -i, "Категорія"));
        many.add(new Transaction("01-02-2023", 1000, "Дохід"));
        List<Transaction> top = TransactionAnalyzer.findTopExpenses(many);
        assertEquals(10, top.size()); assertEquals(-15, top.get(0).getAmount());
        assertEquals(-6, top.get(9).getAmount());
    }
    @Test void testExpenseExtremes() {
        LocalDate from = LocalDate.of(2023, 3, 1), to = LocalDate.of(2023, 3, 31);
        assertEquals(-150, TransactionAnalyzer.findLargestExpense(transactions, from, to).orElseThrow().getAmount());
        assertEquals(-20, TransactionAnalyzer.findSmallestExpense(transactions, from, to).orElseThrow().getAmount());
        assertTrue(TransactionAnalyzer.findLargestExpense(transactions, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31)).isEmpty());
    }
    @Test void testExpenseGroups() {
        assertEquals(200, TransactionAnalyzer.expensesByCategory(transactions).get("Кафе"));
        assertEquals(170, TransactionAnalyzer.expensesByMonth(transactions).get("03-2023"));
    }
}
