package viewModel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.visitable.Number;
import model.visitable.*;
import model.visitor.CalculatorVisitor;
import model.visitor.Visitor;
import view.components.ShowDialog;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.logging.Logger;

public class MainUiViewModel implements ViewModel {
    private StringProperty textOnScreen = new SimpleStringProperty();

    private DecimalFormat format;
    private final Logger LOGGER = Logger.getLogger(MainUiViewModel.class.getName());

    private Visitor visitor = new CalculatorVisitor();

    public MainUiViewModel() {
        resetTextOnScreen();

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        format = new DecimalFormat("0.###################", otherSymbols);
    }

    public void dot() {
        if (!textOnScreen.getValue().contains("."))
            Platform.runLater(() -> textOnScreen.setValue(textOnScreen.getValue() + "."));
    }

    public void pushNumber(int num) {
        if (num == 0) {
            Platform.runLater(() -> {
                try {
                    textOnScreen.setValue(String.valueOf(format.parse(textOnScreen.getValue() + num)));
                } catch (ParseException e) {
                    LOGGER.severe("parse error");
                }
            });
        } else
            Platform.runLater(() -> {
                try {
                    textOnScreen.setValue(format.format(format.parse(textOnScreen.getValue() + num)));
                } catch (ParseException e) {
                    LOGGER.severe("parse error");
                }
            });
    }

    public StringProperty textOnScreenProperty() {
        return textOnScreen;
    }

    public void enter() {
        if (textOnScreen.getValue().length() > 0) {
            var number = new Number(Double.parseDouble(textOnScreen.getValue()));
            number.accept(visitor);
            resetTextOnScreen();
        }
    }

    public void plus() {
        validateIfEmpty();
        var plus = new Addition();
        try {
            plus.accept(visitor);
        } catch (EmptyStackException e) {
            showError();
            return;
        }
        setResultValue();
    }

    private void showError() {
        ShowDialog.showError("Invalid operand", "You did not enter 2 numbers yet.");
    }

    private void setResultValue() {
        Platform.runLater(() -> textOnScreen.setValue(format.format(((CalculatorVisitor) visitor).getValue())));
    }

    private void resetTextOnScreen() {
        Platform.runLater(() -> textOnScreen.setValue(""));
    }

    public void mul() {
        validateIfEmpty();
        var mul = new Multiplication();
        try {
            mul.accept(visitor);
        } catch (EmptyStackException e) {
            showError();
            return;
        }
        setResultValue();
    }

    public void minus() {
        validateIfEmpty();
        var minus = new Subtraction();
        try {
            minus.accept(visitor);
        } catch (EmptyStackException e) {
            showError();
            return;
        }
        setResultValue();
    }

    public void div() {
        validateIfEmpty();
        var div = new Division();
        try {
            div.accept(visitor);
        } catch (ArithmeticException e) {
            Platform.runLater(() -> textOnScreen.setValue("Error"));
            return;
        } catch (EmptyStackException e) {
            showError();
            return;
        }
        setResultValue();
    }

    public void clear() {
        resetTextOnScreen();
        visitor = new CalculatorVisitor();
    }

    private void validateIfEmpty() {
        if (textOnScreen.getValue().length() > 0) {
            ShowDialog.showWarning("Number not entered", "You did not enter the current number.");
        }
    }
}
