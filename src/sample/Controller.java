package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.awt.*;

public class Controller {

    @FXML
    private TextField txtInput;

    @FXML
    private Button OkBtn;

    @FXML
    private TextArea txtOutput;

    @FXML
    private void printOutput() {
        txtOutput.setText().getText();
    }
}
