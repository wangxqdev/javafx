package com.tec.anji.layout.grid;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Login extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Label lbl_username = new Label("账号:");
        Label lbl_password = new Label("密码:");
        lbl_username.setStyle("-fx-font-size: 14px");
        lbl_password.setStyle("-fx-font-size: 14px");

        TextField txtf_username = new TextField();
        PasswordField txtf_password = new PasswordField();
        txtf_username.setUserData("admin");
        txtf_password.setUserData("admin");
//        光标定位
        lbl_username.setOnMouseClicked(new FocusHandler(txtf_username));
        lbl_password.setOnMouseClicked(new FocusHandler(txtf_password));

        Button btn_clear = new Button("清除");
        Button btn_login = new Button("登录");
        HBox hBox_btn = new HBox(btn_clear, btn_login);
        hBox_btn.setStyle("-fx-alignment: center_right; -fx-spacing: 20px");

        Text txt_hint = new Text();
        HBox hBox_hint = new HBox(txt_hint);
        txt_hint.setStyle("-fx-font-size: 14px; -fx-fill: red");
        hBox_hint.setStyle("-fx-alignment: center_right");

        GridPane root = new GridPane();
        root.add(lbl_username, 0, 0);
        root.add(lbl_password, 0, 1);
        root.add(txtf_username, 1, 0);
        root.add(txtf_password, 1, 1);
        root.add(hBox_btn, 1, 2);
        root.add(hBox_hint, 1, 4);
        root.setStyle("-fx-background-color: #FFFAF0; -fx-hgap: 10px; -fx-vgap: 10px; -fx-alignment: center");

        btn_clear.setOnAction(e ->
        {
            txtf_username.clear();
            txtf_password.clear();
            txt_hint.setText("");
        });
        btn_login.setOnAction(e ->
        {
            if (txtf_username.getUserData().equals(txtf_username.getText())
                    && txtf_password.getUserData().equals(txtf_password.getText()))
            {
                txt_hint.setText("登录成功");
            }
            else
            {
                txt_hint.setText("用户名或密码错误");
//                过渡动画
                FadeTransition fade = new FadeTransition(Duration.millis(100), root);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.play();
            }
        });

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setTitle("登录界面");
        primaryStage.show();
    }

    private class FocusHandler implements EventHandler<MouseEvent>
    {
        private Node node;

        FocusHandler(Node node)
        {
            this.node = node;
        }

        @Override
        public void handle(MouseEvent event)
        {
            if (MouseButton.PRIMARY.equals(event.getButton()) && 1 == event.getClickCount())
            {
                node.requestFocus();
            }
        }
    }
}
