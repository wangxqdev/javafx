package com.tec.anji.binding.primitive;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 双向绑定
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TextField txtf1 = new TextField();
        TextField txtf2 = new TextField();
        HBox hBox = new HBox(10, txtf1, txtf2);
        hBox.setStyle("-fx-alignment: center");

        VBox root = new VBox(10, hBox);
        root.setStyle("-fx-padding: 10px");

//        设置双向绑定
        txtf1.textProperty().bindBidirectional(txtf2.textProperty());

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Bidirectional");
        primaryStage.show();
    }
}
