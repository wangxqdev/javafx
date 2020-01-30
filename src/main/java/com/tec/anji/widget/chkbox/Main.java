package com.tec.anji.widget.chkbox;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * CheckBox
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        HBox hBox = new HBox(10);
        ObservableList<Node> children = hBox.getChildren();
        for (int i = 1; i <= 3; i++)
        {
            CheckBox checkBox = new CheckBox(String.format("CheckBox%s", i));
            if (i == 1)
            {
                checkBox.setSelected(true);
            }
            else if (i == 3)
            {
                checkBox.setAllowIndeterminate(true);
            }
            children.add(checkBox);
        }

        AnchorPane root = new AnchorPane(hBox);
        root.setOnMouseClicked(e ->
        {
            for (Node node : hBox.getChildren())
            {
                CheckBox chkBox = (CheckBox) node;
                System.out.println(String.format("%s是否被选中 = %s, 是否是不确定状态 = %s", chkBox.getText(), chkBox.isSelected()
                        , chkBox.isIndeterminate()));
            }
        });

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false);
        primaryStage.setTitle("CheckBox");
        primaryStage.show();

        AnchorPane.setTopAnchor(hBox, (root.getHeight() - hBox.getHeight()) / 2);
        AnchorPane.setLeftAnchor(hBox, (root.getWidth() - hBox.getWidth()) / 2);
    }
}
