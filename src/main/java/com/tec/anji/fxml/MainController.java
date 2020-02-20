package com.tec.anji.fxml;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 * 建议实现 Initializable 接口
 */
public class MainController implements Initializable
{
    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private void doAction(ActionEvent e)
    {
        System.out.println(label.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO 初始化方法
        label.setText(iso88591ToUtf8(resources.getString("label")));
        button.setText(iso88591ToUtf8(resources.getString("button")));
    }

    private String iso88591ToUtf8(String from)
    {
        return new String(from.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
