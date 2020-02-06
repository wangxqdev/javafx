package com.tec.anji.layout.flowpane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FlowPane
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
        Button b1 = new Button("Button1");
        Button b2 = new Button("Button2");
        Button b3 = new Button("Button3");
        Button b4 = new Button("Button4");
        FlowPane root = new FlowPane(b1, b2, b3, b4);
        root.setStyle("-fx-background-color: gray; -fx-hgap: 10px; -fx-vgap: 10px; -fx-padding: 10px");
//        设置Margin
        FlowPane.setMargin(b1, new Insets(0, 10, 0, 0));
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("FlowPane");
        primaryStage.show();
    }
}
