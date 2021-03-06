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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.text.NumberFormat;

/**
 * 1. 编辑模式
 * 2. 单选、多选模式
 * 3. 单元格选择模式
 * 4. 禁用排序
 */
public class Main2 extends Application
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
            Student student = new Student(String.format("小%s", i + 1), value > 50 ? value - 50 : value, value,
                    value % 2 == 0);
            students.add(student);
        }
        TableColumn<Student, String> tbcol_name = new TableColumn<>("姓名");
        tbcol_name.setCellValueFactory(param -> param.getValue().nameProperty());
        TableColumn<Student, Number> tbcol_age = new TableColumn<>("年龄");
        tbcol_age.setCellValueFactory(param -> param.getValue().ageProperty());
        TableColumn<Student, Number> tbcol_score = new TableColumn<>("成绩");
        tbcol_score.setCellValueFactory(param -> param.getValue().scoreProperty());
        TableColumn<Student, Boolean> tbcol_sex = new TableColumn<>("性别");
        tbcol_sex.setCellValueFactory(param -> param.getValue().sexProperty());

        TableView<Student> tbv_student = new TableView<>(students);
        tbv_student.getColumns().addAll(tbcol_name, tbcol_age, tbcol_score, tbcol_sex);
//        菜单按钮
        tbv_student.setTableMenuButtonVisible(true);
//        平均分布各列
        tbv_student.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        禁用排序
        tbcol_name.setSortable(false);
        tbcol_sex.setSortable(false);
        tbv_student.setStyle("-fx-max-width: 300px; -fx-pref-height: 150px");
//        tbv_student.getColumns().forEach(t -> t.setPrefWidth(300.0 / tbv_student.getColumns().size()));

//        可编辑状态
        tbv_student.setEditable(true);
        tbcol_name.setCellFactory(TextFieldTableCell.forTableColumn());
        tbcol_age.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter(NumberFormat.getIntegerInstance())));
        tbcol_score.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
//        tbcol_sex.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

//        单选、多选模式
//        tbv_student.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        tbv_student.setOnMouseClicked(e -> System.out.println(tbv_student.getSelectionModel().getSelectedItems()));
        TableView.TableViewSelectionModel<Student> selectionModel = tbv_student.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue == null && oldValue != null)
            {
                System.out.println(String.format("选择相同行: %s", oldValue));
                return;
            }
            System.out.println(String.format("选择不同行: %s", newValue));
        });

//        单元格选择模式
        selectionModel.setCellSelectionEnabled(true);
        selectionModel.getSelectedCells().addListener((ListChangeListener<? super TablePosition>) c ->
        {
            TablePosition position = selectionModel.getSelectedCells().get(0);
            System.out.println(String.format("选择第%d行、第%d列", position.getRow() + 1, position.getColumn() + 1));
        });

        VBox root = new VBox(tbv_student);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TableView");
        primaryStage.show();
    }

    private class Student
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
