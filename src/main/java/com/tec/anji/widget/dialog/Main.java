package com.tec.anji.widget.dialog;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Dialog
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Open Dialog");
        button.setOnAction(e ->
        {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("这是标题");
            dialog.setContentText("这是内容");
            dialog.setGraphic(new Label("这是图标"));
//            设置宽高
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.setPrefSize(300, 300);
            ObservableList<ButtonType> buttonTypes = dialogPane.getButtonTypes();
            buttonTypes.add(ButtonType.YES);
            buttonTypes.add(ButtonType.NO);
//            二级弹窗
//            dialog.showAndWait();
            dialog.show();
        });

        HBox root = new HBox(button);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Dialog");
        primaryStage.show();
    }
}
