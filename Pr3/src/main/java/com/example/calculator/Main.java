package com.example.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== КАЛЬКУЛЯТОР ===");
            System.out.println("Операції: +, -, *, /, sqrt");
            System.out.print("Введіть операцію: ");
            String operation = scanner.nextLine().trim();

            try {
                if (!operation.equals("+") && !operation.equals("-") &&
                    !operation.equals("*") && !operation.equals("/") &&
                    !operation.equals("sqrt")) {
                    throw new InvalidInputException("Невідома операція: " + operation);
                }

                System.out.print("Введіть перше число: ");
                double a = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                double result;

                if (operation.equals("sqrt")) {
                    result = calculator.squareRoot(a);
                } else {
                    System.out.print("Введіть друге число: ");
                    double b = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                    switch (operation) {
                        case "+": result = calculator.add(a, b); break;
                        case "-": result = calculator.subtract(a, b); break;
                        case "*": result = calculator.multiply(a, b); break;
                        case "/": result = calculator.divide(a, b); break;
                        default: throw new IllegalStateException("Невідома операція");
                    }
                }
                System.out.println("Результат: " + result);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть число, а не текст.");
            } catch (InvalidInputException | ArithmeticException e) {
                System.out.println("Помилка: " + e.getMessage());
            } finally {
                System.out.println("Обробку запиту завершено.");
            }
        }
    }
}
