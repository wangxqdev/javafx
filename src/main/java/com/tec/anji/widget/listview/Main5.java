package com.tec.anji.widget.listview;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * 搜索、排序
 */
public class Main5 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TextField textField = new TextField();
        Button button = new Button("排序");
        HBox hBox = new HBox(textField, button);
        hBox.setStyle("-fx-spacing: 10px; -fx-alignment: center");

        ObservableList<Student> students =
                FXCollections.observableArrayList(param -> new Observable[]{param.nameProperty(), param.ageProperty()});
        for (int i = 0; i < 20; i++)
        {
            String name = String.valueOf((char) (Math.floor(Math.random() * 24 + 97)));
            int age = (int) Math.floor(Math.random() * 100);
            students.add(new Student(name, age));
        }
        ListView<Student> listView = new ListView<>(students);
        listView.setStyle("-fx-max-width: 200px; -fx-pref-height: 150px");

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

        VBox root = new VBox(hBox, listView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

//        搜索
        textField.textProperty().addListener((observable, oldValue, newValue) -> listView.setItems(students.filtered(t -> t.getName().contains(newValue))));
//        排序
        button.setOnAction(e -> listView.setItems(students.sorted((o1, o2) -> o2.getAge() - o1.getAge())));
        root.setOnMouseClicked(e -> listView.setItems(students));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("搜索、排序");
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
