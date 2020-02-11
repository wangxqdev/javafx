package com.tec.anji.event.fire;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * fireEvent()
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button1 = new Button("Button1");
        Button button2 = new Button("Button2");

        HBox root = new HBox(button1, button2);
        root.setStyle("-fx-alignment: center; -fx-spacing: 50px");

        button1.setOnMouseClicked(e ->
        {
            MouseEvent copy = e.copyFor(e.getSource(), e.getTarget(), MouseEvent.MOUSE_RELEASED);
            Event.fireEvent(button2, copy);
        });
        button2.setOnMouseReleased(e -> System.out.println(String.format("%s--鼠标释放, 事件源 = %s, 事件先 = %s",
                ((Button) e.getSource()).getText(), e.getSource(), e.getTarget())));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("fireEvent");
        primaryStage.show();
    }
}
