package com.tec.anji.widget.radiobutton;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * RadioButton
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        HBox hBox = new HBox(10);
        ObservableList<Node> children = hBox.getChildren();

        ToggleGroup toggleGroup = new ToggleGroup();
        ObservableList<Toggle> toggles = toggleGroup.getToggles();
        for (int i = 1; i <= 3; i++)
        {
            RadioButton rdoBox = new RadioButton(String.format("RadioButton%s", i));
            if (i == 1)
            {
                rdoBox.setSelected(true);
            }
            toggles.add(rdoBox);
            children.add(rdoBox);
        }
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> System.out.println(((RadioButton) newValue).getText()));

        AnchorPane root = new AnchorPane(hBox);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false);
        primaryStage.setTitle("RadioButton");
        primaryStage.show();

        AnchorPane.setTopAnchor(hBox, (root.getHeight() - hBox.getHeight()) / 2);
        AnchorPane.setLeftAnchor(hBox, (root.getWidth() - hBox.getWidth()) / 2);
    }
}
