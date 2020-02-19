package com.tec.anji.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));


        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("FXML");
        primaryStage.show();
    }
}
