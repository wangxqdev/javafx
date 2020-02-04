package com.tec.anji.widget.listcell;

import com.tec.anji.model.Student;
import com.tec.anji.util.converter.StudentStringConverter;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * setCellFactory
 */
public class Main extends Application
{
    private static ListCell<Student> call(ListView<Student> param)
    {
        return new ListCell<Student>()
        {
            @Override
            protected void updateItem(Student item, boolean empty)
            {
                super.updateItem(item, empty);
                if (!empty)
                {
                    HBox hBox = new HBox(new Button(item.getName()), new Button(item.getName()));
                    hBox.setStyle("-fx-alignment: center; -fx-spacing: 10px");
                    this.setGraphic(hBox);
                }
            }
        };
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ComboBox<Student> comboBox = new ComboBox<>();
        ObservableList<Student> items = comboBox.getItems();
        for (int i = 1; i <= 5; i++)
        {
            int value = (int) Math.round(Math.random() * 100);
            Student student = new Student(String.format("小%s", i), value > 50 ? value - 50 : value, value);
            items.add(student);
        }
        comboBox.setConverter(new StudentStringConverter());
        comboBox.setCellFactory(Main::call);
        comboBox.setStyle("-fx-pref-width: 150px");

        AnchorPane root = new AnchorPane(comboBox);
        AnchorPane.setTopAnchor(comboBox, 100.0);
        AnchorPane.setLeftAnchor(comboBox, 10.0);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setResizable(false);
        primaryStage.setTitle("setCellFactory");
        primaryStage.show();
    }
}
