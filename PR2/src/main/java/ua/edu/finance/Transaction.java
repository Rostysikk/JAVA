package ua.edu.finance;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Transaction {
    private String date; // dd-MM-yyyy
    private double amount;
    private String description; // категорія з CSV
}
