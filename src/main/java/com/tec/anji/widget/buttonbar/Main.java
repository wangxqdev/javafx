package com.tec.anji.widget.buttonbar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button btn_apply = new Button("Apply");
        Button btn_finish = new Button("Finish");
        Button btn_cancel = new Button("Cancel");

        ButtonBar.setButtonData(btn_apply, ButtonBar.ButtonData.APPLY);
        ButtonBar.setButtonData(btn_finish, ButtonBar.ButtonData.FINISH);
        ButtonBar.setButtonData(btn_cancel, ButtonBar.ButtonData.CANCEL_CLOSE);

        ButtonBar buttonBar = new ButtonBar(ButtonBar.BUTTON_ORDER_WINDOWS);
        buttonBar.getButtons().addAll(btn_apply, btn_finish, btn_cancel);

        AnchorPane root = new AnchorPane(buttonBar);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("ButtonBar");
        primaryStage.show();
    }
}
