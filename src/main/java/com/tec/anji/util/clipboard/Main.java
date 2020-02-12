package com.tec.anji.util.clipboard;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ClipBoard
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(260);
        HBox hBox = new HBox(imageView);
        hBox.setStyle("-fx-pref-width: 260px; -fx-pref-height: 378px; -fx-background-color: yellow; -fx-border-style: dashed; -fx-border-color: red; -fx-border-width: 5px");

        AnchorPane root = new AnchorPane(hBox);
        AnchorPane.setTopAnchor(hBox, (500 - 378) / 2.0);
        AnchorPane.setLeftAnchor(hBox, (400 - 260) / 2.0);

        Scene scene = new Scene(root, 400, 500);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN), () ->
        {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if (clipboard.hasFiles())
            {
                try (FileInputStream fis = new FileInputStream(clipboard.getFiles().get(0)))
                {
                    imageView.setImage(new Image(fis));
                    hBox.setBorder(null);
                }
                catch (IOException e)
                {

                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("ClipBoard");
        primaryStage.show();
    }
}
