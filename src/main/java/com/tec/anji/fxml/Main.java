package com.tec.anji.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * FXML
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/index.fxml"), ResourceBundle.getBundle("DispResource"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/index.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("FXML");
        primaryStage.show();
    }
}
