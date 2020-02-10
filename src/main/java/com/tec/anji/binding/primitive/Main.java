package com.tec.anji.binding.primitive;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 单向绑定
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Button");

        VBox root = new VBox(10, button);
        root.setStyle("-fx-padding: 10px");
//        设置单向绑定
        button.prefWidthProperty().bind(root.widthProperty());

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Unidirectional");
        primaryStage.show();
    }
}
