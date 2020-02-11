package com.tec.anji.event.mouse.drag;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 图形拖动
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Button");

        AnchorPane root = new AnchorPane(button);
        AnchorPane.setTopAnchor(button, 50.0);
        AnchorPane.setLeftAnchor(button, 50.0);

        button.setOnMouseReleased(e -> button.setOnMouseDragged(null));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("图形拖动");

        button.setOnMousePressed(e ->
        {
            double btn_localX = e.getX();
            double btn_localY = e.getY();
            button.setOnMouseDragged(event ->
            {
                double hValue = event.getSceneX() - btn_localX;
                if (hValue > 0 && hValue < root.getWidth() - button.getWidth())
                {
                    AnchorPane.setLeftAnchor(button, hValue);
                }
                double vValue = event.getSceneY() - btn_localY;
                if (vValue > 0 && vValue < root.getHeight() - button.getHeight())
                {
                    AnchorPane.setTopAnchor(button, event.getSceneY() - btn_localY);
                }
            });
        });
        primaryStage.show();
    }
}
