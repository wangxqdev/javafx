package com.tec.anji.widget.tab;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab
 */
public class Main extends Application
{
    private List<ImageView> icons = new ArrayList<ImageView>()
    {{
        add(initImage("/skype.png"));
        add(initImage("/ins.png"));
        add(initImage("/wechat.png"));
    }};

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TabPane tabPane = new TabPane();
        ObservableList<Tab> tabs = tabPane.getTabs();
        for (int i = 0; i < 3; i++)
        {
            Tab tab = new Tab(String.format("Tab%s", i + 1));
            tab.setGraphic(icons.get(i));
            tabs.add(tab);
        }
        tabPane.setSide(Side.RIGHT);
        tabPane.setStyle("-fx-pref-width: 200px; -fx-pref-height: 200px; -fx-background-color: #FFFAF0");
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(String.format("选择%s", newValue.getText())));

        AnchorPane root = new AnchorPane(tabPane);
        root.widthProperty().addListener((observable, oldValue, newValue) -> AnchorPane.setLeftAnchor(tabPane,
                (newValue.doubleValue() - tabPane.getWidth()) / 2));
        root.heightProperty().addListener((observable, oldValue, newValue) -> AnchorPane.setTopAnchor(tabPane,
                (newValue.doubleValue() - tabPane.getHeight()) / 2));

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Tab");
        primaryStage.show();

        AnchorPane.setTopAnchor(tabPane, (root.getHeight() - tabPane.getHeight()) / 2);
        AnchorPane.setLeftAnchor(tabPane, (root.getWidth() - tabPane.getWidth()) / 2);
    }

    private ImageView initImage(String path)
    {
        ImageView imageView = new ImageView(path);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        return imageView;
    }
}
