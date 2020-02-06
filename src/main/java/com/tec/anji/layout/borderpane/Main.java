package com.tec.anji.layout.borderpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        AnchorPane top = new AnchorPane();
        top.setStyle("-fx-background-color: #FF7F24; -fx-pref-height: 100px;");
        AnchorPane right = new AnchorPane();
        right.setStyle("-fx-background-color: #FA8072; -fx-pref-width: 100px");
        AnchorPane bottom = new AnchorPane();
        bottom.setStyle("-fx-background-color: #CDAD00; -fx-pref-height: 100px");
        AnchorPane left = new AnchorPane();
        left.setStyle("-fx-background-color: #A8A8A8; -fx-pref-width: 100px");
        AnchorPane center = new AnchorPane();
        center.setStyle("-fx-background-color: #76EE00");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #FF34B3; -fx-padding: 10px; -fx-spacing: ");

        root.setTop(top);
        root.setRight(right);
        root.setBottom(bottom);
        root.setLeft(left);
        root.setCenter(center);

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("AnchorPane");
        primaryStage.show();
    }
}
