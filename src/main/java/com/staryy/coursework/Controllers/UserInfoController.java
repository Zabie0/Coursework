package com.staryy.coursework.Controllers;

import com.staryy.coursework.Database.DatabaseExecuter;
import com.staryy.coursework.Database.biler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userBalanceText;
    @FXML
    private Label userCreditIdText;
    @FXML
    private Label userTimeText;
    @FXML
    private Label userDebtText;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        biler res;
        try {
            res = DatabaseExecuter.dbExecuteSelect4("userBalance", "creditId", "creditTime", "userRepay", "select * from users where userId = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        userBalanceText.setText("User balance: "+res.getId()+"$");
        userCreditIdText.setText("Credit id: "+res.getId1());
        userTimeText.setText("Time: "+res.getId2()+" months");
        userDebtText.setText("Debt: "+res.getId3()+"$");
    }
}

