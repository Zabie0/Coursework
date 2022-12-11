package com.staryy.coursework.Controllers;

import com.staryy.coursework.Database.DatabaseExecuter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowAllCreditsController implements Initializable {

    @FXML
    private TextArea allCreditsField;

    @FXML
    private Button backButton;
    @FXML
    private Label hint;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if(DatabaseExecuter.dbExecuteSelect(1,"null","select count(*) from credits").getId() == 0){
                allCreditsField.setDisable(true);
                hint.setText("No credits available");
            }
            else{
                DatabaseExecuter.dbExecuteShowAllCredits(allCreditsField, "select * from (credits inner join banks on (credits.bankId = banks.bankId))");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
