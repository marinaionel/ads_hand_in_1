import model.visitable.*;
import model.visitable.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.visitor.CalculatorVisitor;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CalculatorTest {
    private CalculatorVisitor calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalculatorVisitor();
    }

    @Test
    public void randomTests() {
        var n1 = new Number(76.8);
        var n2 = new Number(67.8);
        Token operator = new Addition();
        n1.accept(calculator);
        n2.accept(calculator);
        operator.accept(calculator);
        assertTrue(calculator.getValue() == 144.6);

        setUp();

        n1 = new Number(76.8);
        n2 = new Number(67.8);
        operator = new Subtraction();
        n1.accept(calculator);
        n2.accept(calculator);
        operator.accept(calculator);
        assertTrue(calculator.getValue() == 76.8 - 67.8);

        setUp();

        n1 = new Number(76.8);
        n2 = new Number(67.8);
        operator = new Multiplication();
        n1.accept(calculator);
        n2.accept(calculator);
        operator.accept(calculator);
        assertTrue(calculator.getValue() == 76.8 * 67.8);

        setUp();

        n1 = new Number(76.8);
        n2 = new Number(67.8);
        operator = new Division();
        n1.accept(calculator);
        n2.accept(calculator);
        operator.accept(calculator);
        assertTrue(calculator.getValue() == 76.8 / 67.8);
    }

    @Test
    public void divisionBy0() {
        var n1 = new Number(76.8);
        var n2 = new Number(0);
        var operator = new Division();
        n1.accept(calculator);
        n2.accept(calculator);
        assertThrows(ArithmeticException.class, () -> operator.accept(calculator));
    }

    @Test
    public void emptyStackException() {
        var n1 = new Number(76.8);
        var n2 = new Number(0.6);
        var operator = new Division();
        n1.accept(calculator);
        n2.accept(calculator);
        operator.accept(calculator);
        assertThrows(EmptyStackException.class, () -> operator.accept(calculator));
    }

    @Test
    public void multipleNumbers() {
        var n1 = new Number(76.8);
        var n2 = new Number(0.6);
        var n3 = new Number(78.9);
        var div = new Division();
        var add = new Addition();
        n1.accept(calculator);
        n2.accept(calculator);
        n3.accept(calculator);
        div.accept(calculator);
        add.accept(calculator);
        assertTrue(calculator.getValue() == (0.6 / 78.9) + 76.8);
    }
}