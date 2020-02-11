package com.tec.anji.event.mouse;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * MouseEvent
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Rectangle rectangle = new Rectangle(100, 100, Color.YELLOW);
        Circle circle = new Circle(50, Color.RED);
        circle.setPickOnBounds(true);

        VBox root = new VBox(rectangle, circle);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);

        root.setOnMouseClicked(e -> System.out.println(String.format("%s--鼠标点击", getEventResourceName(e))));

        rectangle.setOnMouseClicked(e -> System.out.println("鼠标单击"));
        rectangle.setOnMousePressed(e -> System.out.println("鼠标按下"));
        rectangle.setOnMouseReleased(e -> System.out.println("鼠标释放"));
        rectangle.setOnMouseEntered(e -> System.out.println("鼠标进入"));
        rectangle.setOnMouseExited(e -> System.out.println("鼠标退出"));

        circle.setOnMouseClicked(e ->
        {
            System.out.println(String.format("%s--鼠标点击", getEventResourceName(e)));
//            阻止事件传递
            e.consume();
        });

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("MouseEvent");
        primaryStage.show();
    }

    private String getEventResourceName(Event event)
    {
        return event.getSource().getClass().getSimpleName();
    }
}
