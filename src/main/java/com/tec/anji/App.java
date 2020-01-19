package com.tec.anji;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Hello world!
 */
public class App extends Application
{
    public static void main(String[] args)
    {
//        launch(args);
        Application.launch(args);
    }

    @Override
    public void init() throws Exception
    {
        System.out.println("init()");
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("start(Stage)");
//        设置标题
        primaryStage.setTitle("JavaFX");
//        设置图标
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
//        设置最小化
//        primaryStage.setIconified(true);
//        设置最大化
//        primaryStage.setMaximized(true);
//        设置宽度、高度
//        primaryStage.setWidth(500);
//        primaryStage.setHeight(500);
//        primaryStage.setMaxWidth(800);
//        primaryStage.setMaxHeight(800);
//        primaryStage.setMinWidth(300);
//        primaryStage.setMinHeight(300);
//        实时获取宽度、高度
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前宽度: " + newValue));
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前高度: " + newValue));
        primaryStage.show();
        System.out.println("默认宽度: " + primaryStage.getWidth());
        System.out.println("默认高度: " + primaryStage.getHeight());
    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("stop()");
    }
}
