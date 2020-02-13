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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 自定义listView
 *
 * 另通过param.setOnEditStart，重写editStart、editCancel实现亦可
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

                    this.setGraphic(gridPane);

                    gridPane.setOnMouseClicked(e ->
                    {
                        if (MouseButton.PRIMARY.equals(e.getButton()) && 2 == e.getClickCount())
                        {
                            TextField txtf_name = new TextField(lbl_name.getText());
                            TextField txtf_age = new TextField(lbl_age.getText());
                            TextField txtf_score = new TextField(lbl_score.getText());

                            txtf_name.setPrefWidth(40);
                            txtf_age.setPrefWidth(40);
                            txtf_score.setPrefWidth(40);

                            gridPane.getChildren().removeAll();
                            gridPane.add(txtf_name, 0, 0);
                            gridPane.add(txtf_age, 1, 0);
                            gridPane.add(txtf_score, 2, 0);

                            txtf_name.setOnKeyPressed(e2 ->
                            {
                                if (KeyCode.ENTER.equals(e2.getCode()))
                                {
                                    if (!txtf_name.getText().isEmpty())
                                    {
                                        item.setName(txtf_name.getText());
                                    }
                                    gridPane.getChildren().removeAll();
                                    gridPane.add(new Label(item.getName()), 0, 0);
                                    gridPane.add(new Label(String.valueOf(item.getAge())), 1, 0);
                                    gridPane.add(new Label(String.valueOf(item.getScore())), 2, 0);
                                }
                            });
                        }
                    });

//                    实现双击编辑编辑
                    lbl_name.setOnMouseClicked(e ->
                    {
                        if (MouseButton.PRIMARY.equals(e.getButton()) && 2 == e.getClickCount())
                        {
                            TextField txtf_name = new TextField(lbl_name.getText());
                            txtf_name.setPrefWidth(40);
                            gridPane.getChildren().remove(lbl_name);
                            gridPane.add(txtf_name, 0, 0);
//                            双击获取焦点
                            txtf_name.requestFocus();

                            txtf_name.setOnKeyReleased(e2 ->
                            {
                                if (KeyCode.ENTER.equals(e2.getCode()))
                                {
                                    if (!txtf_name.getText().isEmpty())
                                    {
                                        item.setName(txtf_name.getText());
                                    }
                                    gridPane.getChildren().remove(txtf_name);
                                    gridPane.add(new Label(item.getName()), 0, 0);
                                }
                            });

                            txtf_name.focusedProperty().addListener((observable, oldValue, newValue) ->
                            {
                                if (!newValue)
                                {
                                    gridPane.getChildren().remove(txtf_name);
                                    gridPane.add(lbl_name, 0, 0);
                                }
                            });
                        }
//                        阻止事件传递
                        e.consume();
                    });
                }
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
