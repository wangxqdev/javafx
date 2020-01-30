package com.tec.anji.widget.textarea;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;

/**
 * TextArea
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TextField txtField = new TextField();
//        UnaryOperator用于限制输入内容
        txtField.setTextFormatter(new TextFormatter<>(change ->
        {
            String input = change.getText();
            if (input.matches("\\w"))
            {
                return change;
            }
            return null;
        }));

        TextArea txtArea = new TextArea();
        txtArea.setStyle("-fx-font-size: 16px");
        txtArea.scrollLeftProperty().addListener((observable, oldValue, newValue) -> System.out.println("水平滚动条 : " + newValue.doubleValue()));
//        StringConverter用于转换输入内容
        txtArea.setTextFormatter(new TextFormatter<>(new BooleanStringConverter()));

        VBox root = new VBox(10, txtField, txtArea);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false);
        primaryStage.setTitle("TextArea");
        primaryStage.show();
    }
}
