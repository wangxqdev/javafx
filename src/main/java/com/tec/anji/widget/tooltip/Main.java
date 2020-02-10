package com.tec.anji.widget.tooltip;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Tooltip
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        VBox vBox = new VBox();
        vBox.setStyle("-fx-pref-width: 200px; -fx-pref-height: 200px; -fx-background-image: url('/icon.png'); -fx-background-size: 100% 100%");

        Tooltip tooltip = new Tooltip();
        tooltip.setGraphic(vBox);

        Button button = new Button("Button");
        button.setTooltip(tooltip);

        AnchorPane root = new AnchorPane(button);
        AnchorPane.setTopAnchor(button, 50.0);
        AnchorPane.setLeftAnchor(button, 50.0);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Tooltip");
        primaryStage.show();
    }
}
