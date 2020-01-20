package com.tec.anji.aa;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

/**
 * Stage
 */
public class MyStage extends Application
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
    public void start(javafx.stage.Stage primaryStage) throws Exception
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
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前宽度 : " + newValue));
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前高度 : " + newValue));
//        设置透明度
//        primaryStage.setOpacity(0.5);
//        设置置顶
//        primaryStage.setAlwaysOnTop(true);
//        实时获取X、Y轴坐标
        primaryStage.xProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前X轴坐标 : " + newValue));
        primaryStage.yProperty().addListener((observable, oldValue, newValue) -> System.out.println("当前Y轴坐标 : " + newValue));
//        设置窗口样式
//        primaryStage.initStyle(StageStyle.DECORATED);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.initStyle(StageStyle.UNIFIED);
//        设置模态化窗口(primaryStage不能设置模态化窗口)
//        primaryStage.initModality(Modality.NONE);
//        primaryStage.initModality(Modality.WINDOW_MODAL);
//        primaryStage.initModality(Modality.APPLICATION_MODAL);
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
