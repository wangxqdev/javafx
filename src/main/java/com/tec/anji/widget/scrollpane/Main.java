package com.tec.anji.widget.scrollpane;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ScrollPane
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        HBox hBox = new HBox(10);
        ObservableList<Node> buttons = hBox.getChildren();
        for (int i = 0; i < 10; i++)
        {
            buttons.add(new Button(String.format("Button%s", i)));
        }
        ScrollPane root = new ScrollPane(hBox);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("ScrollPane");
        primaryStage.show();
    }
}
