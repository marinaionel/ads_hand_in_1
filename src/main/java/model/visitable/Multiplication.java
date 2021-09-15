package model.visitable;

import model.visitable.Token;
import model.visitor.Visitor;

public class Multiplication implements Token {
    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
