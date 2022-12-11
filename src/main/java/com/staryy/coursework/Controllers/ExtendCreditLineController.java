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

public class ExtendCreditLineController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Label balanceText;

    @FXML
    private Label debtText;

    @FXML
    private Button extendButton;

    @FXML
    private TextField extendField;

    @FXML
    private Label hint;

    @FXML
    private Label maxMoneyText;
    @FXML
    private Label amountToExtendText;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @FXML
    void extendFunction(ActionEvent event) throws IOException, SQLException {
        biler user = DatabaseExecuter.dbExecuteSelect4("userBalance","userRepay","extendedLine","creditId","select * from users where userId = 1");
        biler res = DatabaseExecuter.dbExecuteSelect4("maxExtendableMoney","extendedPercentage","creditLineExtendable","moneyGiven","select * from credits where creditId="+user.getId3());
        if(user.getId2()==1){
            hint.setText("Credit line already extended");
        }
        else if(user.getId3()==0){
            hint.setText("You don't have any credits");
        }
        else if(res.getId2()==0){
            hint.setText("Credit can't be extended");
        }
        else if(Integer.parseInt(extendField.getText())>res.getId()){
            hint.setText("Too much money");
        }
        else{
            DatabaseExecuter.dbExecute("update users set userBalance=userBalance + "+Integer.parseInt(extendField.getText())+",userRepay=userRepay+"+(Integer.parseInt(extendField.getText())+(Integer.parseInt(extendField.getText())*res.getId1())/100)+",extendedLine=1 where userId = 1");
            GoToMainButton.GoToMenuButton(event);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        biler user;
        biler res;
        try {
            user = DatabaseExecuter.dbExecuteSelect4("userBalance","userRepay","extendedLine","creditId","select * from users where userId = 1");
            res = DatabaseExecuter.dbExecuteSelect(1,"null","select maxExtendableMoney from credits where creditId="+user.getId3());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        debtText.setText("Debt: "+user.getId1()+"$");
        balanceText.setText("Balance: "+user.getId()+"$");
        maxMoneyText.setText("Max money: "+res.getId()+"$");
        try {
            if(DatabaseExecuter.dbExecuteSelect(1,"null","select creditLineExtendable from credits where creditId= "+user.getId3()).getId() == 0){
                extendButton.setDisable(true);
                maxMoneyText.setText("Credit line can't be extended");
                amountToExtendText.setDisable(true);
                extendField.setDisable(true);
            }
            else if(user.getId2() == 1){
                extendButton.setDisable(true);
                maxMoneyText.setText("Credit line already extended");
                amountToExtendText.setDisable(true);
                extendField.setDisable(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

