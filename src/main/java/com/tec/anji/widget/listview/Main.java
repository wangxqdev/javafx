package com.tec.anji.widget.listview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * ListView
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button btn_left = new Button("单选");
        ListView<String> lv_left = new ListView<>(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G"
                , "H"));
        lv_left.setStyle("-fx-pref-width: 200px; -fx-pref-height: 150px");
        SelectionModel model_left = lv_left.getSelectionModel();
        VBox left = new VBox(btn_left, lv_left);
        left.setStyle("-fx-padding: 10px; -fx-spacing: 10px; -fx-alignment: top_center");

        btn_left.setOnAction(e ->
        {
            model_left.select(lv_left.getItems().size() - 1);
            lv_left.scrollTo(model_left.getSelectedIndex());
            lv_left.requestFocus();
        });

        Button btn_right = new Button("多选");
        ListView<String> lv_right = new ListView<>(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G"
                , "H"));
//        可编辑状态
        lv_right.setEditable(true);
        lv_right.setCellFactory(TextFieldListCell.forListView());
//        多选模式
        SelectionModel model_right = lv_right.getSelectionModel();
        ((MultipleSelectionModel) model_right).setSelectionMode(SelectionMode.MULTIPLE);
        lv_right.setStyle("-fx-pref-width: 200px; -fx-pref-height: 150px");
        VBox right = new VBox(btn_right, lv_right);
        right.setStyle("-fx-padding: 10px; -fx-spacing: 10px; -fx-alignment: top_center");

        btn_right.setOnAction(e -> ((MultipleSelectionModel) model_right).getSelectedItems().forEach(System.out::println));

        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setRight(right);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("ListView");
        primaryStage.show();
    }
}
