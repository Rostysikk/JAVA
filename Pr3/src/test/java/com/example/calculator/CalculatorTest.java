package com.example.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test void addition() throws InvalidInputException { assertEquals(7.5, calculator.add(3, 4.5)); }
    @Test void subtraction() throws InvalidInputException { assertEquals(-2, calculator.subtract(3, 5)); }
    @Test void multiplication() throws InvalidInputException { assertEquals(-12, calculator.multiply(-3, 4)); }
    @Test void division() throws InvalidInputException { assertEquals(2.5, calculator.divide(5, 2)); }
    @Test void divisionByZero() { assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0)); }
    @Test void negativeSquareRoot() { assertThrows(InvalidInputException.class, () -> calculator.squareRoot(-9)); }
    @Test void squareRoot() throws InvalidInputException { assertEquals(3, calculator.squareRoot(9)); }
    @Test void invalidNaN() { assertThrows(InvalidInputException.class, () -> calculator.add(Double.NaN, 2)); }
    @Test void invalidInfinity() { assertThrows(InvalidInputException.class, () -> calculator.multiply(2, Double.POSITIVE_INFINITY)); }
    @Test void invalidText() { assertThrows(NumberFormatException.class, () -> Double.parseDouble("слово")); }
    @Test void negativeInputsAreAllowed() throws InvalidInputException { assertEquals(-5, calculator.add(-2, -3)); }
}
