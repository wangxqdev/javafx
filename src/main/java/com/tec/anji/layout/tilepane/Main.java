package com.tec.anji.layout.tilepane;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * TilePane，平铺布局
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TilePane root = new TilePane();
        ObservableList<Node> nodes = root.getChildren();
        for (int i = 1; i <= 6; i++)
        {
            Button button = new Button("Button".concat(Integer.toString(i)));
            nodes.add(button);
            if (i == 1)
            {
                button.setStyle("-fx-pref-width: 100px; -fx-pref-height: 50px");
            }
        }
        root.setStyle("-fx-padding: 10px; -fx-vgap: 10px; -fx-hgap: 10px");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("TilePane");
        primaryStage.show();
    }
}
