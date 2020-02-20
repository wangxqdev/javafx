package com.tec.anji.widget.button;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Button
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
        Button button = new Button("测试按钮");
        button.setLayoutX(100);
        button.setLayoutY(100);
//        设置按钮样式
        button.setStyle("-fx-background-color:#FFDAB9; " +
                "-fx-background-insets:5px; " +
                "-fx-background-radius:5px; " +
                "-fx-border-color:#EDEDED; " +
                "-fx-pref-width:100px; " +
                "-fx-pref-height:100px; " +
                "-fx-border-color:#B3EE3A");
//        鼠标单击事件
//        button.setOnAction(e -> System.out.println(((Button) e.getSource()).getText()));
//        鼠标双击事件
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            if (e.getClickCount() == 2 && MouseButton.PRIMARY.equals(e.getButton()))
            {
                System.out.println("鼠标双击");
            }
        });
//        键盘释放事件
        button.setOnKeyReleased(e ->
        {
            if (KeyCode.A.equals(e.getCode()))
            {
                System.out.println("按下A");
            }
        });
        Group group = new Group();
        group.getChildren().add(button);

        Scene scene = new Scene(group, 400, 400);
        scene.setCursor(Cursor.cursor(getClass().getResource("/img/icon.png").toExternalForm()));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Button");
        primaryStage.getIcons().add(new Image("/img/icon.png"));
        primaryStage.show();
    }
}
