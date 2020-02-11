package com.tec.anji.event.mouse.drag;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
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

//        button1.setOnMouseDragged(e -> System.out.println("鼠标拖动"));
//        按下即检测
        button1.setOnMousePressed(e -> e.setDragDetect(true));
        button1.setOnDragDetected(e -> button1.startFullDrag());
//        button1.setOnMouseDragOver(e -> System.out.println("鼠标拖拽路过"));
        button1.setOnMouseDragEntered(e -> System.out.println("鼠标拖拽进入"));
        button1.setOnMouseDragExited(e -> System.out.println("鼠标拖拽退出"));
        button1.setOnMouseDragReleased(e -> System.out.println("鼠标拖拽释放"));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("MouseDrag");
        primaryStage.show();
    }
}
