package com.tec.anji.widget.listview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * onHover && DragEvent
 */
public class Main6 extends Application implements Serializable
{
    public static final DataFormat OBJECT = new DataFormat("data/object");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (int i = 0; i < 5; i++)
        {
            String name = String.valueOf((char) (Math.floor(Math.random() * 24 + 97)));
            int age = (int) Math.floor(Math.random() * 100);
            students.add(new Student(name, age));
        }
        ListView<Student> listView = new ListView<>(students);
        listView.setStyle("-fx-max-width: 200px; -fx-pref-height: 150px");

        listView.setCellFactory(param -> new ListCell<Student>()
        {
            @Override
            protected void updateItem(Student item, boolean empty)
            {
                super.updateItem(item, empty);

                if (!empty)
                {
                    Label label = new Label(String.format("%s - %d", item.getName(), item.getAge()));
                    this.setGraphic(label);
                    this.hoverProperty().addListener((observable, oldValue, newValue) ->
                    {
                        if (newValue)
                        {
                            label.setStyle("-fx-pref-height: 24px; -fx-font-size: 16px");
                        }
                        else
                        {
                            label.setStyle("");
                        }
                    });

                    this.setOnDragDetected(e ->
                    {
                        ClipboardContent content = new ClipboardContent();
                        SelectionModel<Student> selectionModel = listView.getSelectionModel();
                        content.put(OBJECT, selectionModel.getSelectedItem());
                        content.putString(String.valueOf(selectionModel.getSelectedIndex()));

                        WritableImage image = new WritableImage(180, 30);
                        label.snapshot(new SnapshotParameters(), image);

                        Dragboard dragboard = this.startDragAndDrop(TransferMode.MOVE);
                        dragboard.setContent(content);
                        dragboard.setDragView(image);
                    });

                    this.setOnDragEntered(e -> label.setStyle("-fx-pref-height: 24px; -fx-font-size: 16px"));
                    this.setOnDragExited(e -> label.setStyle(""));

                    this.setOnDragOver(e -> e.acceptTransferModes(TransferMode.MOVE));

                    this.setOnDragDropped(e ->
                    {
                        int indexToMove = students.indexOf(item);
                        Student studentToMove = (Student) e.getDragboard().getContent(OBJECT);

                        students.remove(studentToMove);
                        students.add(indexToMove, studentToMove);
                    });
                }
            }
        });

        VBox root = new VBox(listView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("onHover && DragEvent");
        primaryStage.show();
    }

    @AllArgsConstructor
    @Data
    @EqualsAndHashCode
    private class Student implements Serializable
    {
        private String name;

        private int age;
    }
}
