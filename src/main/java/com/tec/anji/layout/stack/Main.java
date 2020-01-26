package com.tec.anji.layout.stack;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application
{
    private List<ImageView> icons = new ArrayList<ImageView>()
    {{
        add(initImage("/skype.png"));
        add(initImage("/ins.png"));
        add(initImage("/wechat.png"));
    }};

    private int index = 0;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button prev = new Button("上一页");
        Button next = new Button("下一页");

        StackPane center = new StackPane(icons.get(index));
        center.setStyle("-fx-alignment: center");
        FlowPane bottom = new FlowPane(prev, next);
        bottom.setStyle("-fx-background-color: gray; -fx-pref-height: 100px; -fx-alignment: center; -fx-hgap: 100px");

        BorderPane root = new BorderPane();
        root.setCenter(center);
        root.setBottom(bottom);

        prev.setOnAction(e ->
        {
            index = index == 0 ? 0 : index - 1;
            addOrUpdateNode(center);
        });
        next.setOnAction(e ->
        {
            index = index == icons.size() - 1 ? icons.size() - 1 : index + 1;
            addOrUpdateNode(center);
        });

        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.setTitle("StackPane");
        primaryStage.show();
    }

    private ImageView initImage(String path)
    {
        ImageView imageView = new ImageView(path);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        return imageView;
    }

    private void addOrUpdateNode(Pane parent)
    {
        ObservableList<Node> list = parent.getChildren();
        Node node = icons.get(index);
        if (list.contains(node))
        {
            list.remove(node);
        }
        list.add(node);
    }
}
