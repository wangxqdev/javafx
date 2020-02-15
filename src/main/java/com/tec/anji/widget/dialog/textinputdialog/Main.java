package com.tec.anji.widget.dialog.textinputdialog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * TextInputDialog
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Open ChoiceDialog");
        button.setOnAction(e ->
        {
            TextInputDialog textInputDialog = new TextInputDialog();
            Button btn_ok = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.OK);
            btn_ok.setOnAction(ex -> System.out.println(textInputDialog.getEditor().getText()));
            textInputDialog.show();
        });

        HBox root = new HBox(button);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TextInputDialog");
        primaryStage.show();
    }
}
