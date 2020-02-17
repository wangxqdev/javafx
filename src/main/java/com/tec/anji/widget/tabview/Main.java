package com.tec.anji.widget.tabview;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 初识TableView
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button btn_left = new Button("修改");
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (int i = 0; i < 5; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i + 1), value > 50 ? value - 50 : value, value,
                    value % 2 == 0);
            students.add(student);
        }
        TableColumn<Student, String> tbcol_name = new TableColumn<>("姓名");
        tbcol_name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        TableColumn<Student, Number> tbcol_age = new TableColumn<>("年龄");
        tbcol_age.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAge()));
        TableColumn<Student, Number> tbcol_score = new TableColumn<>("成绩");
        tbcol_score.setCellValueFactory(param -> new SimpleDoubleProperty(param.getValue().getScore()));
//        拆分单元格
        TableColumn<Student, ?> tbcol_group = new TableColumn<>("信息");
        tbcol_group.getColumns().addAll(tbcol_name, tbcol_age, tbcol_score);
        TableColumn<Student, Boolean> tbcol_sex = new TableColumn<>("性别");
        tbcol_sex.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue().isSex()));

        TableView<Student> tbv_student = new TableView<>(students);
        tbv_student.getColumns().addAll(tbcol_group, tbcol_sex);
        tbv_student.setStyle("-fx-pref-width: 200px; -fx-pref-height: 150px");

        btn_left.setOnAction(e ->
        {
            tbv_student.getItems().get(0).setName("小……");
            tbv_student.refresh();
        });

        VBox box_left = new VBox(btn_left, tbv_student);
        box_left.setStyle("-fx-spacing: 10px; -fx-alignment: center");

        Button btn_right = new Button("修改");
        ObservableList<Teacher> teachers =
                FXCollections.observableArrayList(param -> new Observable[]{param.nameProperty(), param.ageProperty()
                        , param.scoreProperty(), param.sexProperty()});
        for (int i = 0; i < 5; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Teacher teacher = new Teacher(String.format("大%s", i + 1), value > 50 ? value - 50 : value, value,
                    value % 2 == 0);
            teachers.add(teacher);
        }
        TableColumn<Teacher, String> tbcol_name2 = new TableColumn<>("姓名");
        tbcol_name2.setCellValueFactory(param -> param.getValue().nameProperty());
        TableColumn<Teacher, Number> tbcol_age2 = new TableColumn<>("年龄");
        tbcol_age2.setCellValueFactory(param -> param.getValue().ageProperty());
        TableColumn<Teacher, Number> tbcol_score2 = new TableColumn<>("成绩");
        tbcol_score2.setCellValueFactory(param -> param.getValue().scoreProperty());
        TableColumn<Teacher, Boolean> tbcol_sex2 = new TableColumn<>("性别");
        tbcol_sex2.setCellValueFactory(param -> param.getValue().sexProperty());

        TableView<Teacher> tbv_teacher = new TableView<>(teachers);
        tbv_teacher.getColumns().addAll(tbcol_name2, tbcol_age2, tbcol_score2, tbcol_sex2);
        tbv_teacher.setStyle("-fx-pref-width: 200px; -fx-pref-height: 150px");

        teachers.addListener((ListChangeListener<Teacher>) c ->
        {
            while (c.next())
            {
                System.out.println(c.wasUpdated());
            }
        });
        btn_right.setOnAction(e ->
        {
            Teacher first = tbv_teacher.getItems().get(0);
            first.setName("大……");
            first.setAge(100);
            first.setScore(100);
            first.setSex(!first.isSex());
        });

        VBox box_right = new VBox(btn_right, tbv_teacher);
        box_right.setStyle("-fx-spacing: 10px; -fx-alignment: center");

        HBox root = new HBox(box_left, box_right);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TableView");
        primaryStage.show();
    }

    @AllArgsConstructor
    @Data
    private class Student
    {

        private String name;

        private int age;

        private double score;

        private boolean sex;
    }


    private class Teacher
    {
        private SimpleStringProperty name = new SimpleStringProperty();

        private SimpleIntegerProperty age = new SimpleIntegerProperty();

        private SimpleDoubleProperty score = new SimpleDoubleProperty();

        private SimpleBooleanProperty sex = new SimpleBooleanProperty();

        Teacher(String name, int age, double score, boolean sex)
        {
            this.name.set(name);
            this.age.set(age);
            this.score.set(score);
            this.sex.set(sex);
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

        public boolean isSex()
        {
            return sex.get();
        }

        public SimpleBooleanProperty sexProperty()
        {
            return sex;
        }

        public void setSex(boolean sex)
        {
            this.sex.set(sex);
        }
    }
}
