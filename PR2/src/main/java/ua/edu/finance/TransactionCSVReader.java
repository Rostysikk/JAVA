package ua.edu.finance;
import java.io.*;
import java.util.*;
/** Утиліта CSV: не створюється як об'єкт. */
public abstract class TransactionCSVReader {
    private TransactionCSVReader() { }
    public static List<Transaction> readTransactions(String url) throws IOException {
        return readTransactions(new UrlDataSource(url));
    }
    public static List<Transaction> readTransactions(DataSource source) throws IOException {
        try (Reader reader = source.open()) { return parse(reader); }
    }
    public static List<Transaction> parse(Reader input) throws IOException {
        List<Transaction> result = new ArrayList<>();
        BufferedReader reader = input instanceof BufferedReader ? (BufferedReader) input : new BufferedReader(input);
        String line;
        int number = 0;
        while ((line = reader.readLine()) != null) {
            number++;
            if (line.isBlank()) continue;
            String[] fields = line.replace("\uFEFF", "").split(",", -1);
            if (fields.length != 3) throw new IOException("Рядок " + number + ": очікується 3 колонки");
            if (number == 1 && fields[0].trim().equalsIgnoreCase("Дата")) continue;
            try {
                result.add(new Transaction(fields[0].trim(), Double.parseDouble(fields[1].trim()), fields[2].trim()));
            } catch (NumberFormatException e) { throw new IOException("Рядок " + number + ": некоректна сума", e); }
        }
        return result;
    }
}
