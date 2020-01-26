package com.tec.anji.layout.box;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * setManaged, setVisible区别
 */
public class Main2 extends Application
{
    private static boolean isManaged = false;
    private static boolean isVisible = false;
    private static double opacity = 0.0;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button b1 = new Button("Button1");
        Button b2 = new Button("Button2");
        Button b3 = new Button("Button3");
        Button b4 = new Button("b2.setManaged(" + isManaged + ")");
        Button b5 = new Button("b2.setVisible(" + isVisible + ")");
        Button b6 = new Button("b2.setOpacity(" + opacity + ")");

        HBox hBox = new HBox(b1, b2, b3);
        hBox.setStyle("-fx-background-color: #FFF68F; -fx-padding: 20px; -fx-spacing: 10px");

        b4.setOnAction(e ->
        {
            b2.setManaged(isManaged);
            isManaged = !isManaged;
            b4.setText("b2.setManaged(" + isManaged + ")");
            printChildren(hBox);
        });

        b5.setOnAction(e ->
        {
            b2.setVisible(isVisible);
            isVisible = !isVisible;
            b5.setText("b2.setManaged(" + isVisible + ")");
            printChildren(hBox);
        });

        b6.setOnAction(e ->
        {
            b2.setOpacity(opacity);
            opacity = opacity == 0 ? 1.0 : 0.0;
            b6.setText("b2.setOpacity(" + opacity + ")");
            printChildren(hBox);
        });

        VBox vBox = new VBox(b4, b5, b6);
        vBox.setStyle("-fx-background-color: #FFC1C1; -fx-padding: 20px; -fx-spacing: 10px;");

        AnchorPane root = new AnchorPane(hBox, vBox);
//        设置HBox
        AnchorPane.setTopAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
//        设置VBox
        hBox.heightProperty().addListener((observable, oldValue, newValue) -> AnchorPane.setTopAnchor(vBox, newValue.doubleValue()));
        AnchorPane.setRightAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("Box");
        primaryStage.show();
    }

    private static void printChildren(Pane pane)
    {
        System.out.println(String.format("当前父节点下子节点数量: %s", pane.getChildren().size()));
    }
}
