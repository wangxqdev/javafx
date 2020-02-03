package com.tec.anji.widget.combobox;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Arrays;

/**
 * ComboBox
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ComboBox<Student> comboBox = new ComboBox<>();
        ObservableList<Student> students = comboBox.getItems();
        for (int i = 1; i <= 5; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i), value > 50 ? value - 50 : value, value);
            students.add(student);
        }
        comboBox.setEditable(true);
        comboBox.setPlaceholder(new Label("无此学生"));
        comboBox.setVisibleRowCount(students.size());
        comboBox.setConverter(new StudentStringConverter());

        TextField textField = comboBox.getEditor();
        textField.setOnKeyPressed(e -> comboBox.hide());
        textField.setOnKeyReleased(e ->
        {
            KeyCode keyCode = e.getCode();
            String input = textField.getText();
            SelectionModel<Student> selectionModel = comboBox.getSelectionModel();
            if ((KeyCode.DELETE.equals(keyCode) || KeyCode.BACK_SPACE.equals(keyCode)) && !selectionModel.isEmpty())
            {
                if (students.filtered(stu -> stu.getName().equals(input)).size() > 0)
                {
                    return;
                }
                selectionModel.clearSelection();
            }
            if (input.isEmpty())
            {
                if (null == comboBox.getItems() || comboBox.getItems().size() < 5)
                {
                    refresh(comboBox, students);
                }
            }
            else
            {
                ObservableList<Student> filteredStudents = students.filtered(stu -> stu.getName().contains(input));
                if (!filteredStudents.isEmpty())
                {
                    refresh(comboBox, filteredStudents);
                }
                else
                {
                    refresh(comboBox, null);
                }
            }
            if (!textField.getText().equals(input))
            {
                textField.setText(input);
            }
            comboBox.show();
        });

        HBox root = new HBox(comboBox);
        root.setStyle("-fx-padding: 20px; -fx-alignment: top_center");

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false);
        primaryStage.setTitle("ComboBox");
        primaryStage.show();
    }

    private void refresh(ComboBox<Student> comboBox, ObservableList<Student> nodes)
    {
        if (null == nodes)
        {
            comboBox.setItems(null);
            comboBox.setVisibleRowCount(0);
        }
        else
        {
            comboBox.setItems(nodes);
            comboBox.setVisibleRowCount(nodes.size());
        }
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
            if (student == null)
            {
                System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
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
