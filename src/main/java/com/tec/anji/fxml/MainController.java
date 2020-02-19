package com.tec.anji.fxml;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController
{
    @FXML
    private Button hello;

    @FXML
    private Label label;

    @FXML
    private void action(Event e)
    {
        System.out.println(label.getText());
    }
}
