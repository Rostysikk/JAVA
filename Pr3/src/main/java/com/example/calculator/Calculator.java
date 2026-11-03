package com.example.calculator;

public class Calculator {
    private void validate(double... numbers) throws InvalidInputException {
        for (double number : numbers) {
            if (!Double.isFinite(number)) {
                throw new InvalidInputException("Числа мають бути скінченними (не NaN або Infinity).");
            }
        }
    }

    public double add(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validate(a, b);
        if (b == 0.0) {
            throw new ArithmeticException("Ділення на нуль заборонено.");
        }
        return a / b;
    }

    public double squareRoot(double a) throws InvalidInputException {
        validate(a);
        if (a < 0) {
            throw new InvalidInputException("Не можна обчислити квадратний корінь від від’ємного числа.");
        }
        return Math.sqrt(a);
    }
}
