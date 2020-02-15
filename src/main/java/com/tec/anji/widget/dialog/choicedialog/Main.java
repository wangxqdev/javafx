package com.tec.anji.widget.dialog.choicedialog;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ChoiceDialog
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Open ChoiceDialog");
        button.setOnAction(e ->
        {
            ObservableList<String> choices = FXCollections.observableArrayList("数据-A", "数据-B", "数据-C", "数据-D", "数据-E");
            ChoiceDialog<String> choiceDialog = new ChoiceDialog(choices.get(0), choices);
            choiceDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
            choiceDialog.show();
        });

        HBox root = new HBox(button);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ChoiceDialog");
        primaryStage.show();
    }
}
