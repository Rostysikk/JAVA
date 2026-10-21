package ua.edu.finance;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class TransactionCSVReaderTest {
    @Test void testReadTransactionsFromCSV() throws Exception {
        DataSource source = () -> new StringReader("Дата,Сума,Категорія\n05-12-2023,-450,Сільпо\n07-12-2023,-120,Аптека\n");
        List<Transaction> result = TransactionCSVReader.readTransactions(source);
        assertEquals(2, result.size());
        assertEquals(-450, result.get(0).getAmount());
        assertEquals("Аптека", result.get(1).getDescription());
    }
    @Test void testInvalidAmount() {
        assertThrows(IOException.class, () -> TransactionCSVReader.parse(new StringReader("01-01-2023,abc,Кафе")));
    }
}
