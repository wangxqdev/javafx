package com.tec.anji.layout.text;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
        Text t1 = new Text("Text1Text1Text1Text1Text1");
        Text t2 = new Text("Text2Text2Text2Text2Text2");
        Text t3 = new Text("Text3Text3Text3Text3Text3");

        TextFlow root = new TextFlow(t1, t2, t3);
        root.setStyle("-fx-background-color: #FA8072");
        root.setLineSpacing(10);

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("TextPane");
        primaryStage.show();
    }
}
