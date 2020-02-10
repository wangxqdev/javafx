package com.tec.anji.widget.filechooser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FileChooser
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TextField txtf_fileChooser = new TextField();
        TextField txtf_filesChooser = new TextField();
        TextField txtf_folderChooser = new TextField();
        txtf_fileChooser.setEditable(false);
        txtf_filesChooser.setEditable(false);
        txtf_folderChooser.setEditable(false);

        Button btn_fileChooser = new Button("选择单个文件");
        Button btn_filesChooser = new Button("选择多个文件");
        Button btn_txtOpener = new Button("选择文本文件");
        Button btn_txtSaver = new Button("保存文本文件");
        Button btn_foldChooser = new Button("选择文件夹");

        TextArea txta_display = new TextArea();
        txta_display.setStyle("-fx-wrap-text: true");

        GridPane root = new GridPane();

        root.add(txtf_fileChooser, 0, 0);
        root.add(txtf_filesChooser, 0, 1);
        root.add(txtf_folderChooser, 0, 5);

        root.add(btn_fileChooser, 1, 0);
        root.add(btn_filesChooser, 1, 1);
        root.add(btn_txtOpener, 1, 2);
        root.add(btn_txtSaver, 1, 3);
        root.add(btn_foldChooser, 1, 5);

        root.add(txta_display, 0, 4, 2, 1);

        root.setStyle("-fx-padding: 10px; -fx-vgap: 10px; -fx-hgap: 10px; -fx-alignment: center; " +
                "-fx-grid-lines-visible: true");

        btn_fileChooser.setOnAction(e ->
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择单个文件");
            fileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile());
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("文件类型", "*.txt"),
                    new FileChooser.ExtensionFilter("图片类型", "*.png", "*.jpg", "*.gif"),
                    new FileChooser.ExtensionFilter("所有类型", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null)
            {
                txtf_fileChooser.setText(selectedFile.getAbsolutePath());
            }
        });

        btn_filesChooser.setOnAction(e ->
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择多个文件");
            fileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile());
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("文件类型", "*.txt"),
                    new FileChooser.ExtensionFilter("图片类型", "*.png", "*.jpg", "*.gif"),
                    new FileChooser.ExtensionFilter("所有类型", "*.*"));
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
            if (!selectedFiles.isEmpty())
            {
                txtf_filesChooser.setText(selectedFiles.stream().map(t -> t.getAbsolutePath()).collect(Collectors.joining(";")));
            }
        });

        btn_txtOpener.setOnAction(e ->
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择文本文件");
            fileChooser.setInitialDirectory(new File(".\\src\\main\\java\\com\\tec\\anji\\widget\\filechooser"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("文件类型", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null)
            {
                try (BufferedReader reader =
                             new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile),
                                     StandardCharsets.UTF_8)))
                {
                    String line;
                    while (null != (line = reader.readLine()))
                    {
                        txta_display.appendText(line);
                        txta_display.appendText("\n");
                    }
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        btn_txtSaver.setOnAction(e ->
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("保存文本文件");
            fileChooser.setInitialDirectory(new File(".\\src\\main\\java\\com\\tec\\anji\\widget\\filechooser"));
            fileChooser.setInitialFileName("new.txt");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("文件类型", "*.txt"));
            File savedFile = fileChooser.showSaveDialog(primaryStage);
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savedFile))))
            {
                String content = txta_display.getText();
                if (!content.isEmpty())
                {
                    writer.write(content);
                    writer.flush();
                    txta_display.setText("");
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });

        btn_foldChooser.setOnAction(e ->
        {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择文件夹");
            directoryChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile());
            File selectedFolder = directoryChooser.showDialog(primaryStage);
            if (null != selectedFolder)
            {
                // TODO
            }
        });

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FileChooser");
        primaryStage.show();
    }
}
