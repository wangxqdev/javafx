package com.tec.anji.widget.keycode;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * KeyCodeCombination
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
        Scene scene = new Scene(new Group());
//        设置快捷方式
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN), () -> System.out.println("run()"));
        scene.setCursor(Cursor.cursor(getClass().getResource("/img/icon.png").toExternalForm()));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Keycode");
        primaryStage.getIcons().add(new Image("/img/icon.png"));
        primaryStage.show();
    }
}
