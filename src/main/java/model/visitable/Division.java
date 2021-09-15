package model.visitable;

import model.visitor.Visitor;

public class Division implements Token {
    @Override
    public double accept(Visitor visitor) throws ArithmeticException {
        return visitor.visit(this);
    }
}
