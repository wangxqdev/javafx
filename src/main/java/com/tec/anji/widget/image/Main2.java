package com.tec.anji.widget.image;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * PixelReader
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Image image = new Image(getClass().getResource("/rgba.png").toExternalForm());
        PixelReader pixelReader = image.getPixelReader();
        int hexColor = pixelReader.getArgb(0, 0);
        System.out.println(String.format("red = %d, green = %d, blue = %d, alpha = %d",
                hexColor >> 16 & 0xff, hexColor >> 8 & 0xff, hexColor & 0xff, hexColor >> 24 & 0xff));

        Color color = pixelReader.getColor(0, 0);
        System.out.println(String.format("red = %f, green = %f, blue = %f, alpha = %f",
                color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, color.getOpacity() * 255));

        byte[] buffer = new byte[3 * 3 * 4];
        pixelReader.getPixels(0, 0, 3, 3, PixelFormat.getByteBgraInstance(), buffer, 0, 12);
        for (int i = 0; i < buffer.length; i += 4)
        {
            System.out.println(String.format("red = %d, green = %d, blue = %d, alpha = %d",
                    buffer[i] & 0xff, buffer[i + 1] & 0xff, buffer[i + 2] & 0xff, buffer[i + 3] & 0xff));
        }
        Platform.exit();
    }
}
