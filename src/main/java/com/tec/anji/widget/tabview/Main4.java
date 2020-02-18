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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 自定义单元格
 */
public class Main4 extends Application
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
        tbcol_age.setCellFactory(param -> new TableCell<Student, Number>()
        {
            @Override
            protected void updateItem(Number item, boolean empty)
            {
                super.updateItem(item, empty);

                if (!empty)
                {
                    Label lbl_age = new Label(String.valueOf(item));
                    HBox hBox = new HBox(lbl_age);
                    this.setGraphic(hBox);
                    this.setTooltip(new Tooltip(String.format("年龄:%d", item.intValue())));
                }
            }

            @Override
            public void startEdit()
            {
                super.startEdit();

                Student current = (Student) this.getTableRow().getItem();

                TextField tf_age = new TextField(String.valueOf(current.getAge()));
                HBox hBox = new HBox(tf_age);
                this.setGraphic(hBox);

                tf_age.setOnKeyReleased(e ->
                {
                    if (KeyCode.ENTER.equals(e.getCode()))
                    {
                        String newValue = tf_age.getText();
                        if (newValue.isEmpty())
                        {
                            newValue = String.valueOf(current.getAge());
                        }
                        this.commitEdit(Integer.valueOf(newValue));
                    }
                });
            }

            @Override
            public void cancelEdit()
            {
                super.cancelEdit();

                Student current = (Student) this.getTableRow().getItem();

                Label lbl_age = new Label(String.valueOf(current.getAge()));
                HBox hBox = new HBox(lbl_age);
                this.setGraphic(hBox);
            }

            @Override
            public void commitEdit(Number newValue)
            {
                super.commitEdit(newValue);
//                TODO 后处理
            }
        });


        TableColumn<Student, Number> tbcol_score = new TableColumn<>("成绩");
        tbcol_score.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<Student, Boolean> tbcol_sex = new TableColumn<>("性别");
        tbcol_sex.setCellValueFactory(param -> param.getValue().sexProperty());
        tbcol_sex.setCellFactory(CheckBoxTableCell.forTableColumn(tbcol_sex));

        TableView<Student> tbv_student = new TableView<>(students);
        tbv_student.getColumns().addAll(tbcol_name, tbcol_age, tbcol_score, tbcol_sex);
//        平均分布各列
        tbv_student.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbv_student.setEditable(true);
        tbv_student.setStyle("-fx-max-width: 300px; -fx-pref-height: 150px");

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
