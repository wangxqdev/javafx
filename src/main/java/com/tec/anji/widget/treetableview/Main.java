package com.tec.anji.widget.treetableview;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.ChoiceBoxTreeTableCell;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.ProgressBarTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeTableView
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("测试");

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            students.add(newInstance(i + 1));
        }

        TreeItem<Student> leader = new TreeItem<>(newInstance(0));
        ObservableList<TreeItem<Student>> members = leader.getChildren();
        students.forEach(t -> members.add(new TreeItem<>(t)));

        TreeTableColumn<Student, String> col_name = new TreeTableColumn<>("姓名");
        col_name.setCellValueFactory(param -> param.getValue().getValue().nameProperty());

        TreeTableColumn<Student, Number> col_age = new TreeTableColumn<>("年龄");
        col_age.setCellValueFactory(param -> param.getValue().getValue().ageProperty());

        TreeTableColumn<Student, Double> col_score = new TreeTableColumn<>("成绩");
//        col_score.setCellValueFactory(param -> param.getValue().getValue().scoreProperty());
        col_score.setCellValueFactory(new TreeItemPropertyValueFactory<>("score"));

        TreeTableColumn<Student, Boolean> col_sex = new TreeTableColumn<>("性别");
        col_sex.setCellValueFactory(param -> param.getValue().getValue().sexProperty());

        TreeTableView<Student> treeTableView = new TreeTableView<>(leader);
        treeTableView.getColumns().addAll(col_name, col_age, col_score, col_sex);
        treeTableView.getRoot().setExpanded(true);
//        平均分布各列
        treeTableView.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        treeTableView.setStyle("-fx-max-width: 300px; -fx-pref-height: 200px");

//        SelectionModel<TreeItem<Student>> selectionModel = treeTableView.getSelectionModel();
//        ((TreeTableView.TreeTableViewSelectionModel<Student>) selectionModel).setSelectionMode(SelectionMode
//        .MULTIPLE);
//        ((TreeTableView.TreeTableViewSelectionModel<Student>) selectionModel).setCellSelectionEnabled(true);
        treeTableView.setEditable(true);
        col_name.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//        col_age.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(100, 200, 300));
        col_age.setCellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(100, 200, 300));
        col_score.setCellFactory(ProgressBarTreeTableCell.forTreeTableColumn());
        col_sex.setCellFactory(CheckBoxTreeTableCell.forTreeTableColumn(col_sex));

        VBox root = new VBox(button, treeTableView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TreeTableView");
        primaryStage.show();
    }

    private Student newInstance(int id)
    {
        int value = (int) Math.round(Math.random() * 100);
        return new Student(String.format("小%d", id), value > 50 ? value - 50 : value, value / 100.0, value % 2 == 0);
    }

    @EqualsAndHashCode
    public class Student
    {
        private SimpleStringProperty name = new SimpleStringProperty();

        private SimpleIntegerProperty age = new SimpleIntegerProperty();

        private SimpleDoubleProperty score = new SimpleDoubleProperty();

        private SimpleBooleanProperty sex = new SimpleBooleanProperty();

        public Student(String name, int age, double score, boolean sex)
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
