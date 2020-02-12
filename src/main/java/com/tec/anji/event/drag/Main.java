package com.tec.anji.event.drag;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 文本拖拽
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Label label = new Label("Hello world");
        TextField textField = new TextField();

        label.setOnDragDetected(e ->
        {
            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText());
            Dragboard dragboard = label.startDragAndDrop(TransferMode.MOVE);
            dragboard.setContent(content);
        });

        textField.setOnDragOver(e -> e.acceptTransferModes(TransferMode.MOVE));

        textField.setOnDragDropped(e ->
        {
            textField.setText(label.getText());
            e.setDropCompleted(true);
        });

        label.setOnDragDone(e ->
        {
            if (TransferMode.MOVE.equals(e.getTransferMode()))
            {
                label.setText("");
            }
        });

        AnchorPane root = new AnchorPane(label, textField);
        AnchorPane.setLeftAnchor(textField, 100.0);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("DragEvent");
        primaryStage.show();
    }
}
