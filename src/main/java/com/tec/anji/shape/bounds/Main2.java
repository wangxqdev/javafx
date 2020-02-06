package com.tec.anji.shape.bounds;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bounds
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button1 = new Button("Button1");
        Button button2 = new Button("Button2");
        button2.setStyle("-fx-pref-width: 200px; -fx-pref-height: 200px");

        AnchorPane root = new AnchorPane(button1, button2);
        AnchorPane.setTopAnchor(button2, 100.0);
        AnchorPane.setLeftAnchor(button2, 100.0);

        Scene scene = new Scene(root, 500, 400);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        scene.setOnKeyReleased(e ->
        {
            KeyCode keyCode = e.getCode();
            Bounds button2BoundsInParent = button2.getBoundsInParent();
            Bounds button1BoundsInParent = button1.getBoundsInParent();
            if (keyCode.equals(KeyCode.RIGHT))
            {
                AnchorPane.setLeftAnchor(button1, button1BoundsInParent.getMinX() + 10);
            }
            else if (keyCode.equals(KeyCode.DOWN))
            {
                AnchorPane.setTopAnchor(button1, button1BoundsInParent.getMinY() + 10);
            }
            else if (keyCode.equals(KeyCode.UP))
            {
                AnchorPane.setTopAnchor(button1, button1BoundsInParent.getMinY() - 10);
            }
            else if (keyCode.equals(KeyCode.LEFT))
            {
                AnchorPane.setLeftAnchor(button1, button1BoundsInParent.getMinX() - 10);
            }
            button1BoundsInParent = button1.getBoundsInParent();
            String nowString = LocalDateTime.now().toString();
            if (button2BoundsInParent.contains(button1BoundsInParent))
            {
                System.out.println(String.format("%s[%d]: contains", nowString, atomicInteger.get()));
            }
            if (button2BoundsInParent.intersects(button1BoundsInParent))
            {
                System.out.println(String.format("%s[%d]: intersects", nowString, atomicInteger.get()));
            }
            atomicInteger.getAndIncrement();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bounds");
        primaryStage.show();
    }
}
