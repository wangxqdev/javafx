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
        Label label = new Label("图片黏贴处");
        label.setStyle("-fx-padding: 100px; -fx-border-style: dashed; -fx-border-width: 5px; -fx-font-size: 20px");

        HBox root = new HBox(label);
        root.setStyle("-fx-alignment: center");

        Scene scene = new Scene(root, 500, 400);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN), () ->
        {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if (clipboard.getFiles().size() > 0)
            {
                try (FileInputStream fis = new FileInputStream(clipboard.getFiles().get(0)))
                {
                    ImageView imageView = new ImageView(new Image(fis));
                    imageView.setFitWidth(label.getWidth());
                    imageView.setFitHeight(label.getHeight());
                    ObservableList<Node> nodes = root.getChildren();
                    nodes.clear();
                    nodes.add(imageView);
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
