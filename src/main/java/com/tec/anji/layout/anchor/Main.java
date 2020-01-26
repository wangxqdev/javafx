package com.tec.anji.layout.anchor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Anchor布局只能管理直接子元素，绝对布局
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
        Button button = new Button("Button");
        AnchorPane parent = new AnchorPane();
        AnchorPane son = new AnchorPane();

        parent.setStyle("-fx-background-color: green");
        son.setStyle("-fx-background-color: red");
//        拉伸布局
        AnchorPane.setTopAnchor(son, 0.0);
        parent.widthProperty().addListener((observable, oldValue, newValue) -> AnchorPane.setRightAnchor(son, newValue.doubleValue() / 2));
        parent.heightProperty().addListener((observable, oldValue, newValue) -> AnchorPane.setBottomAnchor(son, newValue.doubleValue() / 2));
        AnchorPane.setLeftAnchor(son, 0.0);

        AnchorPane.setRightAnchor(button, 0.0);
        AnchorPane.setBottomAnchor(button, 0.0);

        parent.getChildren().add(son);
        son.getChildren().add(button);

        primaryStage.setScene(new Scene(parent, 500, 500));
        primaryStage.setTitle("AnchorPane");
        primaryStage.show();
    }
}
