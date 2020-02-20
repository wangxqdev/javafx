package com.tec.anji.widget.group;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Group
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
        Button b1 = new Button("b1");
        Button b2 = new Button("b2");
        Group group = new Group();
        ObservableList<Node> children = group.getChildren();
        children.addAll(b1, b2);
        for (int i = 0; i < children.size(); i++)
        {
            Button button = (Button) children.get(i);
            button.setPrefWidth(100);
            button.setPrefHeight(100);
            button.setLayoutX(i * 200);
            button.setLayoutY(10);
        }
        b1.setOnAction(e -> b2.setLayoutX(b2.getLayoutX() + 10));

        Scene scene = new Scene(group);
        scene.setCursor(Cursor.cursor(getClass().getResource("/img/icon.png").toExternalForm()));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Group");
        primaryStage.getIcons().add(new Image("/img/icon.png"));
        primaryStage.show();
    }
}
