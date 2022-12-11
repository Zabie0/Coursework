package com.staryy.coursework.Controllers;

import com.staryy.coursework.Database.DatabaseExecuter;
import com.staryy.coursework.Database.biler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChooseCreditController implements Initializable {
    private biler res = null;
    private biler user = null;
    private String answer;
    @FXML
    private Button backButton;

    @FXML
    private Button chooseCreditButton;

    @FXML
    private ComboBox<String> creditIdChoice;

    @FXML
    private TextArea creditPropertiesArea;
    @FXML
    private Label idText;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @FXML
    void chooseCreditFunction(ActionEvent event) throws IOException, SQLException {
        res = DatabaseExecuter.dbExecuteSelect4("moneyGiven","creditId","months","moneyToGive","select * from credits where creditId= " + answer);
        int newUserBalance = user.getId1()+res.getId();
        DatabaseExecuter.dbExecute("update users set userBalance="+newUserBalance+",creditId="+res.getId1()+",creditTime="+res.getId2()+",userRepay="+res.getId3()+" where userId = 1");
        GoToMainButton.GoToMenuButton(event);
    }
    @FXML
    public void updateFunction(ActionEvent event){
        answer = creditIdChoice.getSelectionModel().getSelectedItem();
        boolean ext = false;
        res = DatabaseExecuter.dbExecuteSelect8("moneyGiven","percentage","months","moneyToGive","creditLineExtendable","maxExtendableMoney","extendedPercentage","name","select * from (credits inner join banks on (credits.bankId = banks.bankId))where creditId="+answer);
        if (res.getId4()==1) {
            ext = true;
        }
        creditPropertiesArea.setText("Money given: " + res.getId() + "$\nPercentage: " + res.getId1() + "%\nTime: " + res.getId2() + " months\nMoney to repay: " + res.getId3() + "$\nIs credit line extendable: " + ext + "\nMax money to extend: " + res.getId5() + "$\nExtended percentage: " + res.getId6() + "%\nBank: " + res.getName());
    }
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            creditIdChoice.getItems().setAll(DatabaseExecuter.dbExecuteSelectNames("creditId","select creditId from credits"));
            user = DatabaseExecuter.dbExecuteSelect4("creditId", "userBalance", "userId", "userRepay", "select * from users where userId = 1");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(user.getId()!=0){
            idText.setText("You have a credit");
            creditIdChoice.setDisable(true);
            chooseCreditButton.setDisable(true);
        }
        try {
            if(DatabaseExecuter.dbExecuteSelect(1,"null","select count(*) from credits").getId() == 0){
                idText.setText("No credits available");
                creditIdChoice.setDisable(true);
                chooseCreditButton.setDisable(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}