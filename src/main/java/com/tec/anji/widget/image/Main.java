package com.tec.anji.widget.image;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Image
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ProgressIndicator progressIndicator = new ProgressIndicator(0);

        Image image = new Image(getClass().getResource("/img/dog.jpg").toExternalForm(), 250, 0, true, true, false);
//        backgroundLoading = true时触发
        image.progressProperty().addListener((observable, oldValue, newValue) -> progressIndicator.setProgress(newValue.doubleValue()));
        ImageView imageView = new ImageView(image);
//        设置圆角
        Bounds boundsInParent = imageView.getBoundsInParent();
        Rectangle rectangle = new Rectangle(boundsInParent.getMinX(), boundsInParent.getMinY(), imageView.prefWidth(-1), imageView.prefHeight(-1));
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(30);
        imageView.setClip(rectangle);

        VBox root = new VBox(imageView, progressIndicator);
        root.setStyle("-fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Image");
        primaryStage.show();
    }
}
