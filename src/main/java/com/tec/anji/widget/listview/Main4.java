package com.tec.anji.widget.listview;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义listView
 */
public class Main4 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ListView<Student> listView = new ListView<>(FXCollections.observableArrayList(param ->
                new Observable[]{param.nameProperty(), param.ageProperty(), param.scoreProperty()}));
        ObservableList<Student> students = listView.getItems();
        for (int i = 0; i < 8; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i + 1), value > 50 ? value - 50 : value, value);
            students.add(student);
        }
        listView.setStyle("-fx-max-width: 200px; -fx-pref-height: 150px");

//        双击编辑行
        listView.setEditable(true);
        List<GridPane> lines = new ArrayList<>();
        AtomicInteger index = new AtomicInteger();
        listView.setOnEditStart(e -> index.set(e.getIndex()));

        listView.setCellFactory(param -> new ListCell<Student>()
        {
            @Override
            protected void updateItem(Student item, boolean empty)
            {
                super.updateItem(item, empty);

                if (!empty)
                {
                    Label lbl_name = new Label(item.getName());
                    Label lbl_age = new Label(String.valueOf(item.getAge()));
                    Label lbl_score = new Label(String.valueOf(item.getScore()));

                    GridPane gridPane = new GridPane();
                    gridPane.add(lbl_name, 0, 0);
                    gridPane.add(lbl_age, 1, 0);
                    gridPane.add(lbl_score, 2, 0);
                    gridPane.setStyle("-fx-padding: 5px; -fx-hgap: 20px; -fx-alignment: center_left");
                    lines.add(gridPane);

                    this.setGraphic(gridPane);
                }
            }

            @Override
            public void startEdit()
            {
                super.startEdit();

                int editingIndex = index.get();
                GridPane gridPane = lines.get(editingIndex);
                Student current = listView.getItems().get(editingIndex);

                TextField txtf_name = new TextField(current.getName());
                TextField txtf_age = new TextField(String.valueOf(current.getAge()));
                TextField txtf_score = new TextField(String.valueOf(current.getScore()));

                txtf_name.setPrefWidth(40);
                txtf_age.setPrefWidth(40);
                txtf_score.setPrefWidth(40);

                gridPane.getChildren().clear();
                gridPane.add(txtf_name, 0, 0);
                gridPane.add(txtf_age, 1, 0);
                gridPane.add(txtf_score, 2, 0);

                this.setGraphic(gridPane);

                txtf_name.setOnKeyReleased(e ->
                {
                    if (KeyCode.ENTER.equals(e.getCode()))
                    {
                        if (!txtf_name.getText().isEmpty())
                        {
                            current.setName(txtf_name.getText());
                        }
                        if (!txtf_age.getText().isEmpty())
                        {
                            current.setAge(Integer.valueOf(txtf_age.getText()));
                        }
                        if (!txtf_score.getText().isEmpty())
                        {
                            current.setScore(Double.valueOf(txtf_score.getText()));
                        }
                        this.commitEdit(current);
                    }
                });

                txtf_age.setOnKeyReleased(e ->
                {
                    if (KeyCode.ENTER.equals(e.getCode()))
                    {
                        if (!txtf_name.getText().isEmpty())
                        {
                            current.setName(txtf_name.getText());
                        }
                        if (!txtf_age.getText().isEmpty())
                        {
                            current.setAge(Integer.valueOf(txtf_age.getText()));
                        }
                        if (!txtf_score.getText().isEmpty())
                        {
                            current.setScore(Double.valueOf(txtf_score.getText()));
                        }
                        this.commitEdit(current);
                    }
                });

                txtf_score.setOnKeyReleased(e ->
                {
                    if (KeyCode.ENTER.equals(e.getCode()))
                    {
                        if (!txtf_name.getText().isEmpty())
                        {
                            current.setName(txtf_name.getText());
                        }
                        if (!txtf_age.getText().isEmpty())
                        {
                            current.setAge(Integer.valueOf(txtf_age.getText()));
                        }
                        if (!txtf_score.getText().isEmpty())
                        {
                            current.setScore(Double.valueOf(txtf_score.getText()));
                        }
                        this.commitEdit(current);
                    }
                });
            }

            @Override
            public void cancelEdit()
            {
                super.cancelEdit();

                int editingIndex = index.get();
                GridPane gridPane = lines.get(editingIndex);
                Student current = listView.getItems().get(editingIndex);

                Label lbl_name = new Label(current.getName());
                Label lbl_age = new Label(String.valueOf(current.getAge()));
                Label lbl_score = new Label(String.valueOf(current.getScore()));

                gridPane.getChildren().clear();
                gridPane.add(lbl_name, 0, 0);
                gridPane.add(lbl_age, 1, 0);
                gridPane.add(lbl_score, 2, 0);

                this.setGraphic(gridPane);
            }
        });

        VBox root = new VBox(listView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("refresh()");
        primaryStage.show();
    }

    private class Student
    {
        private SimpleStringProperty name = new SimpleStringProperty();

        private SimpleIntegerProperty age = new SimpleIntegerProperty();

        private SimpleDoubleProperty score = new SimpleDoubleProperty();

        public Student(String name, int age, double score)
        {
            this.name.set(name);
            this.age.set(age);
            this.score.set(score);
        }

        public String getName()
        {
            return name.get();
        }

        public SimpleStringProperty nameProperty()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name.set(name);
        }

        public int getAge()
        {
            return age.get();
        }

        public SimpleIntegerProperty ageProperty()
        {
            return age;
        }

        public void setAge(int age)
        {
            this.age.set(age);
        }

        public double getScore()
        {
            return score.get();
        }

        public SimpleDoubleProperty scoreProperty()
        {
            return score;
        }

        public void setScore(double score)
        {
            this.score.set(score);
        }
    }
}
