package com.tec.anji.layout.splitpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * SplitPane
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        StackPane sp1 = new StackPane(new Button("Button One"));
        StackPane sp2 = new StackPane(new Button("Button Two"));
        StackPane sp3 = new StackPane(new Button("Button Three"));

        SplitPane root = new SplitPane(sp1, sp2, sp3);
        root.setDividerPositions(0.3f, 0.6f, 0.9f);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("SplitPane");
        primaryStage.show();
    }
}
