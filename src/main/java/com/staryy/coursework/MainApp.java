package com.staryy.coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("coursework.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Credits");
        stage.setScene(scene);
        stage.getIcons().add(new Image("C:\\Users\\VladS\\IdeaProjects\\Coursework\\src\\main\\resources\\com\\staryy\\coursework\\img\\icon.png"));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}