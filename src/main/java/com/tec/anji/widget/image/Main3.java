package com.tec.anji.widget.image;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 */
public class Main3 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        int width = 600;
        int height;

        Image imgLeft = new Image(getClass().getResource("/img/BLEACH1.jpg").toExternalForm(), width, 0, true, true);
        PixelReader pixelReader = imgLeft.getPixelReader();
        height = (int) imgLeft.getHeight();
        int[] buffer = new int[width * height / 2];
        pixelReader.getPixels(0, 0, width / 2, height, PixelFormat.getIntArgbPreInstance(), buffer, 0, width / 2);

        Image imgRight = new Image(getClass().getResource("/img/BLEACH2.jpg").toExternalForm(), width, 0, true, true);
        WritableImage writableImage = new WritableImage(imgRight.getPixelReader(), (int) imgRight.getWidth(),
                (int) imgRight.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        pixelWriter.setPixels(0, 0, width / 2, height, PixelFormat.getIntArgbPreInstance(), buffer, 0, width / 2);

        ImageView imageView = new ImageView(writableImage);
        AnchorPane root = new AnchorPane(imageView);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("PixelWriter");
        primaryStage.show();

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ImageIO.write(bufferedImage, "png", new File("C:\\Users\\admin\\IdeaProjects\\javafx\\target\\classes\\BLEACH" +
                ".png"));
    }
}
