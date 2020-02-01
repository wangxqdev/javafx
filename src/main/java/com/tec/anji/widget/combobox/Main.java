package com.tec.anji.widget.combobox;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
        comboBox.setVisibleRowCount(5);
        comboBox.setConverter(new StudentStringConverter());

        TextField textField = comboBox.getEditor();
        textField.setOnMouseClicked(e -> {
            textField.clear();
            comboBox.setItems(students);
            comboBox.show();
        });

        textField.setOnKeyReleased(e ->
        {
            String input = comboBox.getEditor().getText();
            if (input.isEmpty())
            {
                comboBox.setItems(students);
            }
            else
            {
                ObservableList<Student> filteredStudents = students.filtered(stu -> stu.getName().contains(input));
                if (!filteredStudents.isEmpty())
                {
                    comboBox.setItems(filteredStudents);
                }
                else
                {
                    comboBox.setItems(null);
                }
            }
            comboBox.hide();
            comboBox.show();
        });

        HBox root = new HBox(comboBox);
        root.setStyle("-fx-padding: 20px; -fx-alignment: top_center");

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false);
        primaryStage.setTitle("ComboBox");
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
            if (student == null)
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
