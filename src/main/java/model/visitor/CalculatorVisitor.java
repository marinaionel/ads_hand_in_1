package model.visitor;

import model.stack.StackLinkedList;
import model.visitable.Addition;
import model.visitable.Division;
import model.visitable.Multiplication;
import model.visitable.Subtraction;
import model.visitable.Number;

import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatorVisitor implements Visitor {
    private Stack<Double> stack = new StackLinkedList<>();

    public double getValue() {
        return stack.peek();
    }

    public CalculatorVisitor() {
        stack.clear();
    }

    @Override
    public double visit(Number number) {
        stack.push(number.getValue());
        return 0;
    }

    @Override
    public double visit(Addition addition) throws EmptyStackException {
        return stack.push(stack.pop() + stack.pop());
    }

    @Override
    public double visit(Subtraction subtraction) throws EmptyStackException {
        return stack.push(-stack.pop() + stack.pop());
    }

    @Override
    public double visit(Multiplication multiplication) throws EmptyStackException {
        return stack.push(stack.pop() * stack.pop());
    }

    @Override
    public double visit(Division division) throws ArithmeticException, EmptyStackException {
        double tmp = stack.pop();
        if (tmp == 0) throw new ArithmeticException("Division by 0");
        else return stack.push(stack.pop() / tmp);
    }
}
