package com.tec.anji.layout.grid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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

        GridPane root = new GridPane();
        root.add(b1, 0, 0);
        root.add(b2, 1, 0);
        root.add(b3, 0, 1);
        root.add(b4, 1, 1);
        root.setStyle("-fx-background-color: gray; -fx-hgap: 10px; -fx-vgap: 10px; -fx-padding: 10px");
        root.setGridLinesVisible(true);

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("GridPane");
        primaryStage.show();
    }
}
