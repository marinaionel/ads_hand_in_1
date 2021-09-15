package model.visitable;

import model.visitor.Visitor;

public interface Token {
    double accept(Visitor visitor);
}
