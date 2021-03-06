package com.tec.anji.util.screen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Screen
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
        Screen primary = Screen.getPrimary();
        Rectangle2D full = primary.getBounds();
        Rectangle2D visual = primary.getVisualBounds();
        System.out.println("屏幕分辨率 : " + (int) full.getWidth() + "*" + (int) full.getHeight());
        System.out.println("屏幕可视范围分辨率 : " + (int) visual.getWidth() + "*" + (int) visual.getHeight());
        Platform.exit();
    }
}
