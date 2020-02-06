package com.tec.anji.widget.spinner;

import com.tec.anji.model.Student;
import com.tec.anji.util.converter.StudentStringConverter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Spinner
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (int i = 1; i <= 5; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i), value > 50 ? value - 50 : value, value);
            students.add(student);
        }

        Spinner<Student> spinner = new Spinner<>();
        SpinnerValueFactory spinnerValueFactory = new StudentSpinnerValueFactory(students);
        spinnerValueFactory.setConverter(new StudentStringConverter());
        spinner.setValueFactory(spinnerValueFactory);

        AnchorPane root = new AnchorPane(spinner);
        AnchorPane.setTopAnchor(spinner, 50.0);
        AnchorPane.setLeftAnchor(spinner, 10.0);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Spinner");
        primaryStage.show();
    }

    private class StudentSpinnerValueFactory extends SpinnerValueFactory<Student>
    {
        private ObservableList<Student> students = FXCollections.observableArrayList();

        private int index = -1;

        StudentSpinnerValueFactory(ObservableList<Student> students)
        {
            this.students.addAll(students);
        }

        @Override
        public void decrement(int steps)
        {
            index -= steps;
            if (index < 0)
            {
                index = students.size() - 1;
            }
            this.setValue(students.get(index));
        }

        @Override
        public void increment(int steps)
        {
            index += steps;
            if (index > students.size() - 1)
            {
                index = 0;
            }
            this.setValue(students.get(index));
        }
    }

}
