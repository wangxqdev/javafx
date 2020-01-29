package com.tec.anji.widget.textfield;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
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
        Label lbl_username = new Label("Username:");
        lbl_username.setLayoutX(30);
        lbl_username.setLayoutY(105);

        TextField txt_username = new TextField();
        txt_username.setLayoutX(100);
        txt_username.setLayoutY(100);
        lbl_username.setOnMouseClicked(e ->
        {
            if (MouseButton.PRIMARY.equals(e.getButton()) && e.getClickCount() == 1)
            {
                txt_username.requestFocus();
            }
        });

        Label lbl_password = new Label("Password:");
        lbl_password.setLayoutX(30);
        lbl_password.setLayoutY(155);

        PasswordField pw_password = new PasswordField();
        pw_password.setLayoutX(100);
        pw_password.setLayoutY(150);
        pw_password.setPromptText("最多输入8个字符");
        lbl_password.setOnMouseClicked(e ->
        {
            if (MouseButton.PRIMARY.equals(e.getButton()) && e.getClickCount() == 1)
            {
                pw_password.requestFocus();
            }
        });
        pw_password.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue.length() > 8)
            {
                pw_password.setText(oldValue);
            }
        });

        Group group = new Group();
        group.getChildren().addAll(lbl_username, txt_username, lbl_password, pw_password);

        Scene scene = new Scene(group, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TextField");
        primaryStage.show();
    }
}
