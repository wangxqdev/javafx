package com.tec.anji.widget.hyperlink;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Hyperlink link = new Hyperlink("跳转网址");
        link.setUserData("https://www.bilibili.com");
        link.setStyle("-fx-border-width: 0px");
        link.setOnAction(e ->
        {
            HostServices services = getHostServices();
            services.showDocument(String.valueOf(link.getUserData()));
        });

        BorderPane root = new BorderPane();
        root.setCenter(link);

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setTitle("HyperLink");
        primaryStage.show();
    }
}
