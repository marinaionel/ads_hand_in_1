package model.visitable;

import model.visitor.Visitor;

public class Addition implements Token {
    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
