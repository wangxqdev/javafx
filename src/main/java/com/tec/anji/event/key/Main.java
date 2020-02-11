package com.tec.anji.event.key;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * KeyEvent
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Button");
        TextField textField = new TextField();
        Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setFill(Color.RED);

        FlowPane root = new FlowPane(button, textField, rectangle);
        root.setStyle("-fx-padding: 10px; -fx-hgap: 10px; -fx-vgap: 10px");

        button.setOnKeyPressed(e -> System.out.println(String.format("按下%s键", e.getCode())));
        button.setOnKeyReleased(e -> System.out.println(String.format("释放%s键", e.getCode())));
        textField.setOnKeyTyped(e -> System.out.println(String.format("键入%s", e.getCharacter())));
        rectangle.setOnMouseClicked(e -> rectangle.requestFocus());
        rectangle.setOnKeyReleased(e -> System.out.println(String.format("释放%s键", e.getCode())));

        primaryStage.setScene(new Scene( root, 500, 400));
        primaryStage.setTitle("KeyEvent");
        primaryStage.show();
    }
}
