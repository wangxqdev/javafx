package com.tec.anji.thread.task;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

/**
 * Task
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button btn_start = new Button("开始");
        Button btn_cancel = new Button("取消");
        ProgressBar progressBar = new ProgressBar(0);
        Text txt_state = new Text("state");
        Text txt_value = new Text("value");
        Text txt_title = new Text("title");
        Text txt_message = new Text("message");

        HBox root = new HBox(btn_start, btn_cancel, progressBar, txt_state, txt_value, txt_title, txt_message);
        root.setStyle("-fx-pref-width: 600px; -fx-pref-height: 500px; -fx-spacing: 10px; -fx-alignment: center");

        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask);

        btn_start.setOnAction(e -> thread.start());
        btn_cancel.setOnAction(e -> myTask.cancel());
        myTask.progressProperty().addListener((observable, oldValue, newValue) -> progressBar.setProgress(newValue.doubleValue()));

        myTask.stateProperty().addListener((observable, oldValue, newValue) -> txt_state.setText(String.valueOf(newValue)));
        myTask.valueProperty().addListener((observable, oldValue, newValue) -> txt_value.setText(String.valueOf(newValue)));
        myTask.titleProperty().addListener((observable, oldValue, newValue) -> txt_title.setText(newValue));
        myTask.messageProperty().addListener((observable, oldValue, newValue) -> txt_message.setText(newValue));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Task");
        primaryStage.show();
    }

    private class MyTask extends Task<Number>
    {
        @Override
        protected Number call() throws Exception
        {
            updateTitle("更新进度条");
            for (int i = 0; i <= 100; i += 5)
            {
                if (isCancelled())
                {
                    break;
                }
                updateProgress(i, 100);
                if (i < 50)
                {
                    updateMessage("请耐心等待");
                }
                else if (i < 80)
                {
                    updateMessage("马上快好了");
                }
                else if (i < 90)
                {
                    updateMessage("即将完成");
                }
                else
                {
                    updateMessage("搞定");
                }
                TimeUnit.MILLISECONDS.sleep(200);
            }

            return 100;
        }
    }
}
