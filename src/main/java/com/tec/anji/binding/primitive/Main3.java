package com.tec.anji.binding.primitive;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * Bidirectional
 */
public class Main3 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Button");
        TextField textField = new TextField();

        VBox root = new VBox(textField, button);
        root.setStyle("-fx-spacing: 10px; -fx-padding: 10px");

        IntegerProperty factor = new SimpleIntegerProperty(2);
        button.prefWidthProperty().bind(root.widthProperty().divide(factor));
        button.prefHeightProperty().bind(root.heightProperty().divide(factor));
        textField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        textField.textProperty().addListener((observable, oldValue, newValue) -> factor.set(Integer.parseInt(newValue)));

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Bidirectional");
        primaryStage.show();
    }
}
