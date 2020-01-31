package com.tec.anji.widget.textarea;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 循环查找字符串
 */
public class Main2 extends Application
{
    private Pattern pattern = Pattern.compile("");

    private int index = 0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TextField txtf_input = new TextField();
        Button btn_search = new Button("查找");
        HBox hBox = new HBox(10, txtf_input, btn_search);

        txtf_input.textProperty().addListener((observable, oldValue, newValue) ->
        {
            pattern = Pattern.compile(String.format("(%s)", newValue));
            index = 0;
        });

        TextArea txta_display = new TextArea(randomString(200));
        txta_display.setStyle("-fx-wrap-text: true; -fx-font-size: 16px");

        btn_search.setOnAction(e ->
        {
            String input = txtf_input.getText();
            String display = txta_display.getText();
            Matcher matcher = pattern.matcher(display);
            if (matcher.find(index))
            {
                int begin = matcher.start();
                int end = begin + input.length();
                txta_display.requestFocus();
                txta_display.selectRange(begin, end);
                index = end;
                if (!display.substring(index).contains(input))
                {
                    index = 0;
                }
            }
        });

        VBox root = new VBox(10, hBox, txta_display);
        root.setStyle("-fx-padding: 10px");

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.setTitle("查找字符串");
        primaryStage.show();
    }

    private String randomString(int length)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            builder.append((char) (97 + (int) Math.round(Math.random() * 24)));
        }
        return builder.toString();
    }
}
