package com.staryy.coursework.Controllers;

import com.staryy.coursework.Database.DatabaseExecuter;
import com.staryy.coursework.Database.biler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DepositController implements Initializable{

    @FXML
    private TextField amountField;

    @FXML
    private Button backButton;

    @FXML
    private Label balanceText;

    @FXML
    private Label debtText;

    @FXML
    private Button depositButton;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @FXML
    void depositFunction(ActionEvent event) throws IOException {
        DatabaseExecuter.dbExecute("update users set userBalance= userBalance + "+amountField.getText()+" where userId = 1");
        GoToMainButton.GoToMenuButton(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        biler res;
        try {
            res = DatabaseExecuter.dbExecuteSelect4("userBalance","userRepay","creditTime","creditId","select * from users where userId = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        debtText.setText("Debt: "+res.getId1()+"$");
        balanceText.setText("Balance: "+res.getId()+"$");
    }
}
