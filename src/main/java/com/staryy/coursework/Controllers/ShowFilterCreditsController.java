package com.staryy.coursework.Controllers;

import com.staryy.coursework.Database.DatabaseExecuter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowFilterCreditsController implements Initializable {

    @FXML
    private TextField minMoneyField;

    @FXML
    private Button backButton;

    @FXML
    private TextArea filterCreditsField;

    @FXML
    private TextField maxMoneyField;
    @FXML
    private Label hint;
    @FXML
    private Button showFilterCreditsButton;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @FXML
    void showFilterCreditsFunction(ActionEvent event){
        if(Integer.parseInt(maxMoneyField.getText()) < Integer.parseInt(minMoneyField.getText())){
            hint.setText("No credits available");
        }
        else{
            DatabaseExecuter.dbExecuteShowAllCredits(filterCreditsField, "select * from (credits inner join banks on (credits.bankId = banks.bankId)) where moneyGiven<" + maxMoneyField.getText() + " and moneyGiven>" + minMoneyField.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if(DatabaseExecuter.dbExecuteSelect(1,"null","select count(*) from credits").getId() == 0){
                minMoneyField.setDisable(true);
                maxMoneyField.setDisable(true);
                filterCreditsField.setDisable(true);
                showFilterCreditsButton.setDisable(true);
                hint.setText("No credits available");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
