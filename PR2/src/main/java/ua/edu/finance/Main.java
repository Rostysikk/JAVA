
package ua.edu.finance;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Transaction> transactions = List.of(
                new Transaction("05-12-2023", -450, "Сільпо"),
                new Transaction("07-12-2023", -120, "Аптека"),
                new Transaction("10-12-2023", 8000, "Зарплата"),
                new Transaction("12-12-2023", 1500, "Авторські винагороди"),
                new Transaction("14-12-2023", -3200, "Оренда квартири"),
                new Transaction("16-12-2023", 2500, "Фріланс"),
                new Transaction("18-12-2023", -200, "Кав'ярня"),
                new Transaction("20-12-2023", -750, "Подарунки"),
                new Transaction("22-12-2023", -600, "Бензин"),
                new Transaction("24-12-2023", -950, "Ресторан")
        );

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("         ФІНАНСОВИЙ АНАЛІЗАТОР");
            System.out.println("========================================");
            System.out.println("  1. Переглянути всі транзакції");
            System.out.println("  2. Показати загальний баланс");
            System.out.println("  3. Транзакції за місяць");
            System.out.println("  4. ТОП-10 найбільших витрат");
            System.out.println("  5. Найбільша та найменша витрата");
            System.out.println("  6. Витрати за категоріями");
            System.out.println("  7. Витрати за місяцями");
            System.out.println("  8. Повний фінансовий звіт");
            System.out.println("  0. Вихід");
            System.out.println("========================================");
            System.out.print("Оберіть пункт меню: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println("Програму завершено.");
                break;
            }

            System.out.println("\n----------------------------------------");

            switch (choice) {
                case "1":
                    System.out.println("ВСІ ТРАНЗАКЦІЇ");
                    transactions.forEach(System.out::println);
                    break;

                case "2":
                    System.out.println("ЗАГАЛЬНИЙ БАЛАНС");
                    TransactionReportGenerator.printBalanceReport(
                            TransactionAnalyzer.calculateTotalBalance(transactions)
                    );
                    break;

                case "3":
                    System.out.print("Введіть місяць (MM-yyyy): ");
                    String month = scanner.nextLine().trim();
                    try {
                        TransactionReportGenerator.printTransactionsCountByMonth(
                                month,
                                TransactionAnalyzer.countTransactionsByMonth(
                                        transactions, month
                                )
                        );
                    } catch (RuntimeException e) {
                        System.out.println("Некоректний формат місяця.");
                    }
                    break;

                case "4":
                    TransactionReportGenerator.printTopExpensesReport(
                            TransactionAnalyzer.findTopExpenses(transactions)
                    );
                    break;

                case "5":
                    System.out.print("Початок періоду (yyyy-MM-dd): ");
                    String start = scanner.nextLine().trim();
                    System.out.print("Кінець періоду (yyyy-MM-dd): ");
                    String end = scanner.nextLine().trim();

                    try {
                        LocalDate from = LocalDate.parse(start);
                        LocalDate to = LocalDate.parse(end);

                        if (from.isAfter(to)) {
                            System.out.println("Початок пізніше кінця періоду.");
                            break;
                        }

                        TransactionReportGenerator.printExpenseExtremes(
                                TransactionAnalyzer.findLargestExpense(
                                        transactions, from, to
                                ),
                                TransactionAnalyzer.findSmallestExpense(
                                        transactions, from, to
                                )
                        );
                    } catch (RuntimeException e) {
                        System.out.println("Некоректна дата. Формат: yyyy-MM-dd");
                    }
                    break;

                case "6":
                    TransactionReportGenerator.printExpenseHistogram(
                            "Витрати за категоріями",
                            TransactionAnalyzer.expensesByCategory(transactions),
                            1000
                    );
                    break;

                case "7":
                    TransactionReportGenerator.printExpenseHistogram(
                            "Витрати за місяцями",
                            TransactionAnalyzer.expensesByMonth(transactions),
                            1000
                    );
                    break;

                case "8":
                    System.out.println("ПОВНИЙ ФІНАНСОВИЙ ЗВІТ");

                    TransactionReportGenerator.printBalanceReport(
                            TransactionAnalyzer.calculateTotalBalance(transactions)
                    );

                    TransactionReportGenerator.printTopExpensesReport(
                            TransactionAnalyzer.findTopExpenses(transactions)
                    );

                    TransactionReportGenerator.printExpenseHistogram(
                            "Витрати за категоріями",
                            TransactionAnalyzer.expensesByCategory(transactions),
                            1000
                    );

                    TransactionReportGenerator.printExpenseHistogram(
                            "Витрати за місяцями",
                            TransactionAnalyzer.expensesByMonth(transactions),
                            1000
                    );
                    break;

                default:
                    System.out.println("Невідомий пункт меню. Спробуйте ще раз.");
            }

            System.out.println("----------------------------------------");
        }
    }
}
