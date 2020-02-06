package com.tec.anji.layout.dialogpane;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * DialogPane, 初探JavaFx多线程
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button button = new Button("Open Dialog");
        AnchorPane.setTopAnchor(button, 100.0);
        AnchorPane.setLeftAnchor(button, 100.0);
        button.setOnAction(e ->
        {
            DialogPane dialog = new DialogPane();
            dialog.setContentText("Context");
            dialog.setHeaderText("Header");
            dialog.setExpandableContent(new Text("Expandable"));
            dialog.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
            dialog.lookupButton(ButtonType.YES).setOnMouseClicked(mouseEvent -> System.out.println("click Yes"));
            dialog.lookupButton(ButtonType.NO).setOnMouseClicked(mouseEvent -> System.out.println("click No"));

            Stage stage = new Stage();
            stage.setScene(new Scene(dialog));
            stage.setTitle("Dialog");
            stage.show();

            new updateContextThread(stage, dialog).start();
        });

        AnchorPane root = new AnchorPane(button);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setTitle("DialogPane");
        primaryStage.show();
    }

    private class updateContextThread extends ScheduledService<Integer>
    {

        private Stage stage;

        private DialogPane dialog;

        private AtomicInteger countDown = new AtomicInteger(10);

        public updateContextThread(Stage stage, DialogPane dialog)
        {
            this.stage = stage;
            this.dialog = dialog;
            this.setDelay(Duration.millis(0));
            this.setPeriod(Duration.millis(1000));
        }

        @Override
        protected Task<Integer> createTask()
        {
            return new Task<Integer>()
            {
                /**
                 * 非UI线程，适用于复杂业务处理
                 * @return
                 */
                @Override
                protected Integer call()
                {
                    System.out.println("Current Thread : " + Thread.currentThread().getName());
                    return countDown.decrementAndGet();
                }

                /**
                 * UI线程，适用于更新组件
                 * @param value
                 */
                @Override
                protected void updateValue(Integer value)
                {
                    System.out.println("Current Thread : " + Thread.currentThread().getName());
                    dialog.setContentText(countDown.toString());
                    if (0 == countDown.get())
                    {
                        stage.close();
                        updateContextThread.this.cancel();
                    }
                }
            };
        }
    }
}
