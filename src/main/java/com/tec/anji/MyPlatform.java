package com.tec.anji;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MyPlatform extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
//        runLater()
        System.out.println(Thread.currentThread().getName());
        Platform.runLater(() -> System.out.println(Thread.currentThread().getName()));
//        setImplicitExit(boolean)
        Platform.setImplicitExit(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
//        isSupported(ConditionalFeature)
        System.out.println(ConditionalFeature.SCENE3D.name() + " : " + Platform.isSupported(ConditionalFeature.SCENE3D));
        primaryStage.show();
    }
}
