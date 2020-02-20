package com.tec.anji.widget.pagination;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Pagination
 */
public class Main extends Application
{
    private List<ImageView> images = new ArrayList<ImageView>()
    {{
        add(new ImageView("/img/skype.png"));
        add(new ImageView("/img/ins.png"));
        add(new ImageView("/img/wechat.png"));
    }};

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Pagination pagination = new Pagination(images.size());
        pagination.setPageFactory(param ->
        {
            ImageView image = images.get(param);
            image.setFitWidth(300);
            image.setFitHeight(300);
            return image;
        });
        pagination.setStyle("-fx-page-information-visible: false; -fx-tooltip-visible: false");

        BorderPane root = new BorderPane();
        root.setCenter(pagination);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Pagination");
        primaryStage.show();
    }
}
