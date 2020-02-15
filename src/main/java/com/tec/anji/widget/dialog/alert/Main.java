package com.tec.anji.widget.dialog.alert;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Dialog
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Open Alert");
        button.setOnAction(e ->
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getButtonTypes().forEach(System.out::println);
            alert.show();
        });

        HBox root = new HBox(button);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Alert");
        primaryStage.show();
    }
}

