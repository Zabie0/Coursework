package com.staryy.coursework.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button addBankButton;

    @FXML
    private Button addCreditButton;

    @FXML
    private Button chooseCreditButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button displayInfoButton;

    @FXML
    private Button extendCreditLineButton;

    @FXML
    private Button payButton;

    @FXML
    private Button showAllBanksButton;

    @FXML
    private Button showCreditsButton;

    @FXML
    private Button showFilterCreditsButton;

    @FXML
    void addBankFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/addBank.fxml");
    }

    @FXML
    void addCreditFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/addCredit.fxml");
    }

    @FXML
    void chooseCreditFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/chooseCredit.fxml");
    }

    @FXML
    void depositFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/deposit.fxml");
    }

    @FXML
    void displayInfoFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/displayInfo.fxml");
    }

    @FXML
    void extendCreditLineFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/extendCreditLine.fxml");
    }

    @FXML
    void payFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/pay.fxml");
    }

    @FXML
    void showAllBanksFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/showAllBanks.fxml");
    }

    @FXML
    void showCreditsFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/showAllCredits.fxml");
    }

    @FXML
    void showFilterCreditsFunction(ActionEvent event) throws IOException {
        ChangeStage.changeStage(event, "src/main/resources/com/staryy/coursework/showFilterCredits.fxml");
    }

}
