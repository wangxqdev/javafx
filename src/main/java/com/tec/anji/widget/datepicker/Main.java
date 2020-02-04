package com.tec.anji.widget.datepicker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * DatePicker
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        DatePicker datePicker = new DatePicker();

        AnchorPane root = new AnchorPane(datePicker);
        AnchorPane.setTopAnchor(datePicker, 50.0);
        AnchorPane.setLeftAnchor(datePicker, 50.0);

        datePicker.setOnAction(e -> System.out.println(datePicker.getValue()));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("DatePicker");
        primaryStage.show();
    }
}
