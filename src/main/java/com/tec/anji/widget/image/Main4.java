package com.tec.anji.widget.image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 字符图像
 */
public class Main4 extends Application
{
    private String[] graphics = new String[]{"#", "&", "W", "-", " "};

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Image image = new Image(getClass().getResource("/img/sample.jpg").toExternalForm());
        PixelReader pixelReader = image.getPixelReader();
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                builder.append(getGraphics((float) pixelReader.getColor(x, y).getRed()));
            }
            builder.append("\r\n");
        }
        ImageView imageView = new ImageView(image);

        BorderPane root = new BorderPane();
        root.setCenter(imageView);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Character Picture");
        primaryStage.show();

        String pathname = "C:\\Users\\admin\\Desktop\\char.txt";
        try (BufferedWriter writer =
                     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(pathname)))))
        {
            writer.write(builder.toString());
        }
    }

    private String getGraphics(float value)
    {
        return graphics[(int) (value * (graphics.length - 1))];
    }
}
