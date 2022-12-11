package com.staryy.coursework.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartMenuController {

    @FXML
    private Button startButton;

    @FXML
    void startFunction(ActionEvent event) throws IOException {
        GoToMainButton.GoToMenuButton(event);
    }

}

