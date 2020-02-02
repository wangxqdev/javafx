package com.tec.anji.widget.combobox;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
        comboBox.setVisibleRowCount(students.size());
        comboBox.setConverter(new StudentStringConverter());

        TextField textField = comboBox.getEditor();
        textField.setOnKeyPressed(e -> comboBox.hide());
        textField.setOnKeyReleased(e ->
        {
            String input = textField.getText();
            KeyCode keyCode = e.getCode();
            if ((KeyCode.DELETE.equals(keyCode) || KeyCode.BACK_SPACE.equals(keyCode))
                    && null != comboBox.getValue())
            {
                comboBox.setValue(null);
                textField.setText(input);
            }
            if (input.isEmpty())
            {
                if (null == comboBox.getItems() || comboBox.getItems().size() < 5)
                {
                    comboBox.setItems(students);
                    comboBox.setVisibleRowCount(students.size());
                }
            }
            else
            {
                ObservableList<Student> filteredStudents = students.filtered(stu -> stu.getName().contains(input));
                if (!filteredStudents.isEmpty())
                {
                    comboBox.setItems(filteredStudents);
                    comboBox.setVisibleRowCount(filteredStudents.size());
                }
                else
                {
                    comboBox.setItems(null);
                    comboBox.setVisibleRowCount(0);
                }
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
