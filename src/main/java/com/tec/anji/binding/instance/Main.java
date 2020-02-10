package com.tec.anji.binding.instance;

import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 单向绑定，<code>Bindings</code>
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Label lbl_index = new Label("索引");
        Label lbl_value = new Label("值");
        TextField txtf_index = new TextField();
        TextField txtf_value = new TextField();

        GridPane top = new GridPane();
        top.add(lbl_index, 0, 0);
        top.add(lbl_value, 0, 1);
        top.add(txtf_index, 1, 0);
        top.add(txtf_value, 1, 1);
        top.setStyle("-fx-vgap: 10px; -fx-hgap: 10px");

        VBox bottom = new VBox();
        ListProperty<String> list = new SimpleListProperty<>(FXCollections.observableArrayList("A", "B", "C", "D", "E"));
        for (int i = 0; i < list.size(); i++)
        {
            Label label = new Label();
            label.textProperty().bind(list.valueAt(i));
            label.setStyle("-fx-font-size: 20px");
            bottom.getChildren().add(label);
        }
        bottom.setStyle("-fx-spacing: 10px; -fx-background-color: #FFF5EE");

        txtf_value.textProperty().addListener((observable, oldValue, newValue) ->
        {
            String indexStr = txtf_index.getText();
            if (indexStr.matches(String.format("[0-%d]", list.size() - 1)))
            {
                list.set(Integer.parseInt(indexStr), newValue);
            }
        });

        VBox root = new VBox(top, bottom);
        root.setStyle("-fx-padding: 10px; -fx-spacing: 10px");

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Unidirectional");
        primaryStage.show();
    }
}
