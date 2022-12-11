package com.staryy.coursework.Controllers;

import com.staryy.coursework.Database.DatabaseExecuter;
import com.staryy.coursework.Database.biler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddCreditController implements Initializable {
    private final String[] mas = {"Yes", "No"};
    private String answer, name;
    @FXML
    private Button addCreditButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> bankIdField;

    @FXML
    private TextField creditTimeField;

    @FXML
    private ChoiceBox<String> extendableField;

    @FXML
    private TextField extendedPercentageField;

    @FXML
    private TextField maxExtendableMoneyField;

    @FXML
    private TextField moneyGivenField;

    @FXML
    private TextField creditPercentageField;
    @FXML
    private Label hint;

    @FXML
    void addCreditFunction(ActionEvent event) throws IOException, SQLException {
        int ext = 0;
        if(answer.equals("Yes")){
            ext = 1;
        }
        if(Integer.parseInt(creditPercentageField.getText()) <= 0){
            hint.setText("Percentage can't be 0 or less");
        }
        else{
            int moneyToGive = Integer.parseInt(moneyGivenField.getText()) + (Integer.parseInt(moneyGivenField.getText()) * Integer.parseInt(creditPercentageField.getText())/100);
            biler res = DatabaseExecuter.dbExecuteSelect(1,"null","Select bankId from banks where banks.name = '"+name+"';");
            if(ext == 1){
                DatabaseExecuter.dbExecute("INSERT INTO credits (moneyGiven, percentage, months, moneyToGive, creditLineExtendable, maxExtendableMoney, extendedPercentage, bankId) VALUES "
                        + "("+moneyGivenField.getText()+", "+creditPercentageField.getText()+", "+creditTimeField.getText()+", "+moneyToGive+", "+ext+", "+maxExtendableMoneyField.getText()+", "+extendedPercentageField.getText()+", "+res.getId()+");");
            }
            else{
                DatabaseExecuter.dbExecute("INSERT INTO credits (moneyGiven, percentage, months, moneyToGive, creditLineExtendable, maxExtendableMoney, extendedPercentage, bankId) VALUES "
                        + "("+moneyGivenField.getText()+", "+creditPercentageField.getText()+", "+creditTimeField.getText()+", "+moneyToGive+", "+ext+", 0, 0, "+res.getId()+");");
            }
            GoToMainButton.GoToMenuButton(event);
        }
    }

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        extendableField.getItems().addAll(mas);
        extendableField.setOnAction(this::getMas);
        try {
            bankIdField.getItems().addAll(DatabaseExecuter.dbExecuteSelectNames("name","Select name from banks"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        bankIdField.setOnAction(this::getName);
        try {
            if(DatabaseExecuter.dbExecuteSelect(1,"null","select count(*) from banks").getId() == 0){
                bankIdField.setDisable(true);
                extendableField.setDisable(true);
                creditTimeField.setDisable(true);
                moneyGivenField.setDisable(true);
                creditPercentageField.setDisable(true);
                extendedPercentageField.setDisable(true);
                maxExtendableMoneyField.setDisable(true);
                addCreditButton.setDisable(true);
                hint.setText("There are no banks");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getMas(ActionEvent actionEvent) {
        answer = extendableField.getValue();
    }

    private void getName(ActionEvent actionEvent){
        name = bankIdField.getValue();
    }
}