package view;

import com.jfoenix.controls.JFXButton;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import viewModel.MainUiViewModel;

import java.net.URL;
import java.util.ResourceBundle;

@FxmlPath("/MainUiView.fxml")
public class MainUiView implements FxmlView<MainUiViewModel>, Initializable {
    @InjectViewModel
    private MainUiViewModel viewModel;
    @FXML
    public JFXButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b_c, b_enter, b_plus, b_minus, b_div, b_mul, b_dot;
    @FXML
    public Label label_text;

    public MainUiView() {
    }

    public void b2Click(ActionEvent actionEvent) {
        viewModel.pushNumber(2);
    }

    public void b9Click(ActionEvent actionEvent) {
        viewModel.pushNumber(9);
    }

    public void b7Click(ActionEvent actionEvent) {
        viewModel.pushNumber(7);
    }

    public void b6Click(ActionEvent actionEvent) {
        viewModel.pushNumber(6);
    }

    public void b5Click(ActionEvent actionEvent) {
        viewModel.pushNumber(5);
    }

    public void b4Click(ActionEvent actionEvent) {
        viewModel.pushNumber(4);
    }

    public void b3Click(ActionEvent actionEvent) {
        viewModel.pushNumber(3);
    }

    public void b1Click(ActionEvent actionEvent) {
        viewModel.pushNumber(1);
    }

    public void b0Click(ActionEvent actionEvent) {
        viewModel.pushNumber(0);
    }

    public void b8Click(ActionEvent actionEvent) {
        viewModel.pushNumber(8);
    }

    public void clear(ActionEvent actionEvent) {
        viewModel.clear();
    }

    public void enter(ActionEvent actionEvent) {
        viewModel.enter();
    }

    public void plus(ActionEvent actionEvent) {
        viewModel.plus();
    }

    public void minus(ActionEvent actionEvent) {
        viewModel.minus();
    }

    public void div(ActionEvent actionEvent) {
        viewModel.div();
    }

    public void mul(ActionEvent actionEvent) {
        viewModel.mul();
    }

    public void dot(ActionEvent actionEvent) {
        viewModel.dot();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_text.textProperty().bind(viewModel.textOnScreenProperty());
    }
}
