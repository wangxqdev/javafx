package com.tec.anji.widget.listview;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * 刷新列表
 */
public class Main3 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Label label = new Label("点击刷新列表");
        ListView<Student> listView = new ListView<>(FXCollections.observableArrayList(param ->
                new Observable[]{param.nameProperty(), param.ageProperty()}));
        ObservableList<Student> students = listView.getItems();
        for (int i = 0; i < 8; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i + 1), value > 50 ? value - 50 : value);
            students.add(student);
        }

        label.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
        listView.setStyle("-fx-max-width: 200px; -fx-pref-height: 200px");

        label.setOnMouseClicked(e ->
        {
            listView.getItems().forEach(stu ->
            {
//                偶数刷新列表
                if (listView.getItems().indexOf(stu) % 2 != 0)
                {
                    stu.setAge(100);
                }
            });
//            强制刷新列表
//            listView.refresh();
        });

        listView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Student>()
        {
            @Override
            public String toString(Student object)
            {
                return String.format("%s - %s", object.getName(), object.getAge());
            }

            @Override
            public Student fromString(String string)
            {
                return null;
            }
        }));

        VBox root = new VBox(label, listView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("refresh()");
        primaryStage.show();
    }

    private class Student
    {
        private SimpleStringProperty name = new SimpleStringProperty();

        private SimpleIntegerProperty age = new SimpleIntegerProperty();

        public Student(String name, int age)
        {
            this.name.set(name);
            this.age.set(age);
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
    }
}
