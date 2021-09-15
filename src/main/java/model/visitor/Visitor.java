package model.visitor;

import model.visitable.Addition;
import model.visitable.Division;
import model.visitable.Multiplication;
import model.visitable.Subtraction;
import model.visitable.Number;

public interface Visitor {
    double visit(Number number);

    double visit(Addition addition);

    double visit(Subtraction subtraction);

    double visit(Multiplication multiplication);

    double visit(Division division);
}
