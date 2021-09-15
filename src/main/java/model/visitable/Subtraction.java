package model.visitable;

import model.visitor.Visitor;

public class Subtraction implements Token {
    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
