package com.tec.anji.thread.service;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * ScheduledService
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button btn_start = new Button("开始");
        Button btn_pause = new Button("暂定");
        Button btn_restart = new Button("重启");
        Button btn_reset = new Button("重置");
        ProgressBar progressBar = new ProgressBar(0);
        Text txt_state = new Text("state");
        Text txt_value = new Text("value");
        Text txt_title = new Text("title");
        Text txt_message = new Text("message");

        HBox root = new HBox(btn_start, btn_pause, btn_restart, btn_reset, progressBar, txt_state, txt_value,
                txt_title, txt_message);
        root.setStyle("-fx-pref-width: 600px; -fx-pref-height: 500px; -fx-spacing: 10px; -fx-alignment: center");

        MyScheduledService myScheduledService = new MyScheduledService(progressBar);

        btn_start.setOnAction(e -> myScheduledService.start());
        btn_pause.setOnAction(e -> myScheduledService.cancel());
        btn_restart.setOnAction(e ->
        {
            myScheduledService.restart();
            progressBar.setProgress(0);
        });
        btn_reset.setOnAction(e ->
        {
            myScheduledService.reset();
            progressBar.setProgress(0);
        });

        myScheduledService.stateProperty().addListener((observable, oldValue, newValue) -> txt_state.setText(String.valueOf(newValue)));
        myScheduledService.messageProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty())
            {
                txt_message.setText(newValue);
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ScheduledService");
        primaryStage.show();
    }

    private class MyScheduledService extends ScheduledService<Number>
    {
        private ProgressBar progressBar;

        MyScheduledService(ProgressBar progressBar)
        {
            this.progressBar = progressBar;
            this.setDelay(Duration.millis(1000));
            this.setPeriod(Duration.millis(100));
        }

        @Override
        protected Task<Number> createTask()
        {
            return new Task<Number>()
            {
                @Override
                protected Number call() throws Exception
                {
                    return progressBar.getProgress() * 100 + 5;
                }

                @Override
                protected void updateValue(Number value)
                {
                    super.updateValue(value);

                    if (isCancelled() || value.intValue() > 100)
                    {
                        MyScheduledService.this.cancel();
                        return;
                    }

                    int i = value.intValue();
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
                    progressBar.setProgress(value.doubleValue() / 100);
                }
            };
        }
    }
}
