package com.staryy.coursework.Controllers;

import com.staryy.coursework.Database.DatabaseExecuter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

public class AddBankController {

    @FXML
    private Button addBankButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField bankAddressField;

    @FXML
    private TextField bankEmailField;

    @FXML
    private TextField bankNameField;

    @FXML
    void addBankFunction(ActionEvent event) throws IOException {
        DatabaseExecuter.dbExecute("INSERT INTO banks (name, address, email) VALUES "
                + "('"+bankNameField.getText()+"', '"+bankAddressField.getText()+"', '"+bankEmailField.getText()+"');");
        GoToMainButton.GoToMenuButton(event);
    }

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }
}