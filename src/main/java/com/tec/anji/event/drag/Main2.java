package com.tec.anji.event.drag;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件拖拽
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(260);

        String defaultStyle = "-fx-pref-width: 260px; -fx-pref-height: 378px; -fx-background-color: yellow;";
        HBox hBox = new HBox(imageView);
        hBox.setStyle(defaultStyle);

        hBox.setOnDragEntered(e -> hBox.setStyle(defaultStyle.concat("-fx-border-style: dashed; -fx-border-color: " +
                "red; -fx-border-width: 5px")));
        hBox.setOnDragExited(e -> hBox.setStyle(defaultStyle));
        hBox.setOnDragOver(e -> e.acceptTransferModes(e.getTransferMode()));
        hBox.setOnDragDropped(e ->
        {
            Dragboard dragboard = e.getDragboard();
            if (dragboard.hasFiles())
            {
                try (FileInputStream fis = new FileInputStream(dragboard.getFiles().get(0)))
                {
                    imageView.setImage(new Image(fis));
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            else if (dragboard.hasUrl())
            {
                imageView.setImage(new Image(dragboard.getUrl()));
            }
        });

        AnchorPane root = new AnchorPane(hBox);
        AnchorPane.setTopAnchor(hBox, (500 - 378) / 2.0);
        AnchorPane.setLeftAnchor(hBox, (400 - 260) / 2.0);

        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.setResizable(false);
        primaryStage.setTitle("DragEvent");
        primaryStage.show();
    }
}
