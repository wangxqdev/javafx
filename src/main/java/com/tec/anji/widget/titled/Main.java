package com.tec.anji.widget.titled;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * TitledPane
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Text center = new Text();
        center.setFont(Font.font(14));

        Accordion accordion = new Accordion();
        ObservableList<TitledPane> titledPanes = accordion.getPanes();
        for (int i = 1; i <= 3; i++)
        {
            Button button = new Button(String.format("Button%s", i));
            TitledPane titledPane = new TitledPane(String.format("TitledPane%s", i), button);
            titledPanes.add(titledPane);
        }
        accordion.expandedPaneProperty().addListener((observable, oldValue, newValue) ->
        {
            if (null == newValue)
            {
                center.setText(String.format("%s 收缩", oldValue.getText()));
                return;
            }
            center.setText(String.format("%s 展开", newValue.getText()));
        });
        VBox left = new VBox(accordion);

        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setCenter(center);
        root.setStyle("-fx-background-color: #FFFAF0");

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("TitledPane");
        primaryStage.show();
    }
}
