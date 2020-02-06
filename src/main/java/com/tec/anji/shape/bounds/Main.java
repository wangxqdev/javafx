package com.tec.anji.shape.bounds;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Bounds
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Button");
        HBox hBox = new HBox(button);
        hBox.setStyle("-fx-pref-width: 200px; -fx-pref-height: 200px; -fx-background-color: yellow; -fx-alignment: center");

        AnchorPane root = new AnchorPane(hBox);
        AnchorPane.setTopAnchor(hBox, 100.0);
        AnchorPane.setLeftAnchor(hBox, 100.0);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Bounds");
        primaryStage.show();

        System.out.println(String.format("Button在HBox内的坐标: x = %s, y = %s", button.getLayoutX(), button.getLayoutY()));
        Bounds bounds1 = button.getLayoutBounds();
        System.out.println(String.format("Button在Layout内的坐标: x = %s, y = %s", bounds1.getMinX(), bounds1.getMinY()));
        Bounds bounds2 = button.getBoundsInLocal();
        System.out.println(String.format("Button在Local内的坐标: x = %s, y = %s", bounds2.getMinX(), bounds2.getMinY()));
        Bounds bounds3 = button.getBoundsInParent();
        System.out.println(String.format("Button在Parent内的坐标: x = %s, y = %s", bounds3.getMinX(), bounds3.getMinY()));
        Bounds bounds4 = button.localToScene(bounds2);
        System.out.println(String.format("Button在Scene内的坐标: x = %s, y = %s", bounds4.getMinY(), bounds4.getMinY()));
    }
}
