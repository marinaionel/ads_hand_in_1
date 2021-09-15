package model.visitable;

import model.visitor.Visitor;

public class Number implements Token {

    private double value;

    public Number(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
