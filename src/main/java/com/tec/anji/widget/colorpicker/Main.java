package com.tec.anji.widget.colorpicker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * ColorPicker
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ColorPicker colorPicker = new ColorPicker();

        AnchorPane root = new AnchorPane(colorPicker);
        AnchorPane.setTopAnchor(colorPicker, 50.0);
        AnchorPane.setLeftAnchor(colorPicker, 50.0);

        colorPicker.setOnAction(e -> root.setStyle(String.format("-fx-background-color: #%s",
                colorPicker.getValue().toString().substring(2))));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("ColorPicker");
        primaryStage.show();
    }
}
