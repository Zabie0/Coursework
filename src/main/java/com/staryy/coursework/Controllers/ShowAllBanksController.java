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

public class ShowAllBanksController implements Initializable {

    @FXML
    private TextArea allBanksField;

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
            if(DatabaseExecuter.dbExecuteSelect(1,"null","select count(*) from banks").getId() == 0){
                allBanksField.setDisable(true);
                hint.setText("No banks available");
            }
            else{
                DatabaseExecuter.dbExecuteShowAllBanks(allBanksField, "select * from banks");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
