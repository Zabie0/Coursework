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

public class PayController implements Initializable{

    @FXML
    private TextField amountField;

    @FXML
    private Button backButton;

    @FXML
    private Label balanceText;

    @FXML
    private Label debtText;

    @FXML
    private Button payButton;
    @FXML
    private Label hint;
    @FXML
    private Label amountToPayText;
    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @FXML
    void payFunction(ActionEvent event) throws IOException, SQLException {
        biler user = DatabaseExecuter.dbExecuteSelect4("userBalance","userRepay","creditId","creditTime","select * from users where userId = 1");
        if(user.getId()<Integer.parseInt(amountField.getText())){
            hint.setText("Not enough money");
        }
        else if(user.getId1()<=0){
            hint.setText("You don't have a debt");
        }
        else{
            if(user.getId1()<=Integer.parseInt(amountField.getText())){
                DatabaseExecuter.dbExecute("update users set userBalance="+(Integer.parseInt(amountField.getText())-user.getId1())+", userRepay=0,creditId=0,creditTime=0,extendedLine=0 where userId = 1");
            }
            else{
                DatabaseExecuter.dbExecute("update users set userBalance=userBalance-"+Integer.parseInt(amountField.getText())+",userRepay=userRepay-"+Integer.parseInt(amountField.getText())+"where userId = 1");
            }
            GoToMainButton.GoToMenuButton(event);
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        biler res;
        try {
            res = DatabaseExecuter.dbExecuteSelect4("userBalance","userRepay","creditTime","creditId","select * from users where userId = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        debtText.setText("Debt: "+res.getId1()+"$");
        balanceText.setText("Balance: "+res.getId()+"$");
        if(res.getId1() <= 0){
            payButton.setDisable(true);
            amountField.setDisable(true);
            amountToPayText.setText("You don't have a debt");
        }
    }
}
