package com.tec.anji.widget.scrollbar;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ScrollBar
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        VBox vBox = new VBox(10);
        ObservableList<Node> buttons = vBox.getChildren();
        for (int i = 0; i < 10; i++)
        {
            buttons.add(new Button(String.format("Button%s", i)));
        }
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setStyle("-fx-orientation: vertical; -fx-block-increment: 20; -fx-unit-increment: 10");
        scrollBar.valueProperty().addListener((observable, oldValue, newValue) -> vBox.setLayoutY(-newValue.doubleValue()));

        AnchorPane root = new AnchorPane(vBox, scrollBar);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("ScrollBar");
        primaryStage.show();

        AnchorPane.setLeftAnchor(scrollBar, vBox.getWidth());
        scrollBar.setPrefHeight(vBox.getHeight());
    }
}
