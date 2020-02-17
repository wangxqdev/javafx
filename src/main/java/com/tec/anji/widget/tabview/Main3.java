package com.tec.anji.widget.tabview;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ProcessBarTableCell
 * CheckBoxTableCell
 */
public class Main3 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ObservableList<Student> students =
                FXCollections.observableArrayList(param -> new Observable[]{param.nameProperty(), param.ageProperty()
                        , param.scoreProperty(), param.sexProperty()});
        for (int i = 0; i < 5; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i + 1), value > 50 ? value - 50 : value, value / 100.0,
                    value % 2 == 0);
            students.add(student);
        }

        TableColumn<Student, String> tbcol_name = new TableColumn<>("姓名");
        tbcol_name.setCellValueFactory(param -> param.getValue().nameProperty());

        TableColumn<Student, Number> tbcol_age = new TableColumn<>("年龄");
        tbcol_age.setCellValueFactory(param -> param.getValue().ageProperty());

        TableColumn<Student, Double> tbcol_score = new TableColumn<>("成绩");
//        通过反射方式构造TableColumn
        tbcol_score.setCellValueFactory(new PropertyValueFactory<>("score"));
        tbcol_score.setCellFactory(ProgressBarTableCell.forTableColumn());

        TableColumn<Student, Boolean> tbcol_sex = new TableColumn<>("性别");
        tbcol_sex.setCellValueFactory(param -> param.getValue().sexProperty());
        tbcol_sex.setCellFactory(CheckBoxTableCell.forTableColumn(tbcol_sex));

        TableView<Student> tbv_student = new TableView<>(students);
        tbv_student.getColumns().addAll(tbcol_name, tbcol_age, tbcol_score, tbcol_sex);
        tbv_student.setEditable(true);
        tbv_student.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbv_student.setStyle("-fx-max-width: 300px; -fx-pref-height: 150px");

        tbv_student.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue.isSex()));

        VBox root = new VBox(tbv_student);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TableView");
        primaryStage.show();
    }

    public class Student
    {
        private SimpleStringProperty name = new SimpleStringProperty();

        private SimpleIntegerProperty age = new SimpleIntegerProperty();

        private SimpleDoubleProperty score = new SimpleDoubleProperty();

        private SimpleBooleanProperty sex = new SimpleBooleanProperty();

        Student(String name, int age, double score, boolean sex)
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
