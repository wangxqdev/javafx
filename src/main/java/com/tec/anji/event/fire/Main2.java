package com.tec.anji.event.fire;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Event Capturing && Event Bubbling
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Button");
        Label label = new Label("Hello world");

        HBox hBox = new HBox(10, button, label);
        hBox.setStyle("-fx-pref-width: 200px; -fx-pref-height: 200px; -fx-alignment: center; -fx-background-color: yellow");

        AnchorPane root = new AnchorPane(hBox);
        AnchorPane.setTopAnchor(hBox, 50.0);
        AnchorPane.setLeftAnchor(hBox, 50.0);

//        事件捕获
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("button.addEventFilter -- Source = %s, Target = %s", e.getSource(), e.getTarget())));
        hBox.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("hBox.addEventFilter -- Source = %s, Target = %s", e.getSource(), e.getTarget())));
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("root.addEventFilter -- Source = %s, Target = %s", e.getSource(), e.getTarget())));
//        事件冒泡
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println(String.format("button.addEventHandler -- Source = %s, Target = %s", e.getSource(), e.getTarget()));
//            阻止事件传递
            e.consume();
            if (e.isConsumed())
            {
                Event.fireEvent(hBox, e);
            }
        });
        hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("hBox.addEventHandler -- Source = %s, Target = %s", e.getSource(), e.getTarget())));
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("root.addEventHandler -- Source = %s, Target = %s", e.getSource(), e.getTarget())));
//        Button具有阻止事件冒泡功能
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("button.addEventHandler -- Source = %s, Target = %s", e.getSource(), e.getTarget())));
        hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("hBox.addEventHandler -- Source = %s, Target = %s", e.getSource(), e.getTarget())));
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.out.println(String.format("root.addEventHandler -- Source = %s, Target = %s", e.getSource(), e.getTarget())));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Event Capturing && Event Bubbling");
        primaryStage.show();
    }
}
