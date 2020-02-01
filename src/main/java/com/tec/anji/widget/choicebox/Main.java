package com.tec.anji.widget.choicebox;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * ChoiceBox
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TextField textField = new TextField();
        textField.setStyle("-fx-font-size: 14px");
        Button button = new Button("修改");
        HBox hBox = new HBox(10, textField, button);
        hBox.setStyle("-fx-alignment: center_left");

        ChoiceBox<Student> choiceBox = new ChoiceBox<>();
        choiceBox.setStyle("-fx-pref-width: 100px");
        ObservableList<Student> students = choiceBox.getItems();
        for (int i = 1; i <= 5; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i), value > 50 ? value - 50 : value, value);
//            属性值监听
            student.nameProperty().addListener((observable, oldValue, newValue) ->
            {
                int index = students.indexOf(student);
                students.remove(student);
                students.add(index, student);
            });
            students.add(student);
        }

//        对象数据转换
        choiceBox.setConverter(new StudentStringConverter());
        SingleSelectionModel<Student> selectionModel = choiceBox.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> textField.setText(newValue.getName()));
        selectionModel.select(0);

        button.setOnAction(e -> selectionModel.getSelectedItem().setName(textField.getText()));

        VBox root = new VBox(10, hBox, choiceBox);
        root.setStyle("-fx-padding: 10px");

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("ChoiceBox");
        primaryStage.show();
    }

    private class Student
    {
        private SimpleStringProperty name = new SimpleStringProperty();

        private SimpleIntegerProperty age = new SimpleIntegerProperty();

        private SimpleIntegerProperty score = new SimpleIntegerProperty();

        public Student(String name, int age, int score)
        {
            setName(name);
            setAge(age);
            setScore(score);
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

        public int getScore()
        {
            return score.get();
        }

        public SimpleIntegerProperty scoreProperty()
        {
            return score;
        }

        public void setScore(int score)
        {
            this.score.set(score);
        }
    }

    private class StudentStringConverter extends StringConverter<Student>
    {
        @Override
        public String toString(Student student)
        {
            if (null == student)
            {
                return "";
            }
            return student.getName();
        }

        @Override
        public Student fromString(String string)
        {
            return null;
        }
    }
}