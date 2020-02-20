package com.tec.anji.event.drag;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义拖拽类型
 */
public class Main3 extends Application implements Serializable
{
    public static final DataFormat OBJECT = new DataFormat("data/object");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Student student = new Student("小王", 31, getClass().getResource("/img/sample.jpg").toExternalForm());

        Label lbl_detail = new Label("学生详情");
        TextField txtf_name = new TextField();
        TextField txtf_age = new TextField();
        ImageView imgv_photo = new ImageView();

        lbl_detail.setAlignment(Pos.CENTER);
        txtf_name.setStyle("");
        txtf_name.setPrefWidth(260);
        txtf_name.setEditable(false);
        txtf_age.setPrefWidth(260);
        txtf_age.setEditable(false);
        imgv_photo.setFitWidth(260);
        imgv_photo.setFitHeight(378);

        Label lbl_stu = new Label(student.getName());
        lbl_stu.setStyle("-fx-background-color: yellow");

        VBox vBox = new VBox(lbl_detail, txtf_name, txtf_age, imgv_photo);
        vBox.setStyle("-fx-pref-width: 280px; -fx-padding: 5px; -fx-spacing: 10px; -fx-border-color: red; " +
                "-fx-border-width: 5px; -fx-border-style: dashed; -fx-alignment: center");
        lbl_detail.prefWidthProperty().bind(vBox.widthProperty());

        lbl_stu.setOnDragDetected(e ->
        {
            ClipboardContent content = new ClipboardContent();
            content.put(DataFormat.lookupMimeType("data/object"), student);

            Dragboard dragboard = lbl_stu.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            dragboard.setContent(content);

            WritableImage writableImage = new WritableImage((int) vBox.getWidth(), (int) vBox.getHeight());
            vBox.snapshot(new SnapshotParameters(), writableImage);
            dragboard.setDragView(writableImage);
        });

        vBox.setOnDragOver(e -> e.acceptTransferModes(TransferMode.COPY_OR_MOVE));

        vBox.setOnDragDropped(e ->
        {
            Student stu = (Student) e.getDragboard().getContent(OBJECT);
            txtf_name.setText(stu.getName());
            txtf_age.setText(String.valueOf(stu.getAge()));
            imgv_photo.setImage(new Image(stu.getPhotoPath()));
        });

        HBox root = new HBox(lbl_stu, vBox);
        root.setStyle("-fx-alignment: center; -fx-spacing: 20px");

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("DragEvent");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @AllArgsConstructor
    @Data
    private class Student implements Serializable
    {
        private String name;

        private int age;

        private String photoPath;
    }
}
