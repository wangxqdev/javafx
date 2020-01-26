package com.tec.anji.layout.box;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * HBox and VBox
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        Button b3 = new Button("button3");

        HBox hbox = new HBox(b1, b2, b3);
        hbox.setStyle("-fx-background-color: red; -fx-padding: 10px; -fx-spacing: 10px; -fx-alignment: center");

        AnchorPane root = new AnchorPane(hbox);
        root.setStyle("-fx-background-color: gray");
//        拉伸布局
        AnchorPane.setLeftAnchor(hbox, 0.0);
        root.widthProperty().addListener((observable, oldValue, newValue) -> AnchorPane.setRightAnchor(hbox, newValue.doubleValue() / 2));

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("Box");
        primaryStage.show();
    }
}
