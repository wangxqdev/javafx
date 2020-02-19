package com.tec.anji.thread.service;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

/**
 * Service
 */
public class Main extends Application
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

        MyService myService = new MyService();

        btn_start.setOnAction(e -> myService.start());
        btn_pause.setOnAction(e -> myService.cancel());
        btn_restart.setOnAction(e -> myService.restart());
        btn_reset.setOnAction(e ->
        {
            myService.reset();
            progressBar.setProgress(0);
        });
        myService.progressProperty().addListener((observable, oldValue, newValue) -> progressBar.setProgress(newValue.doubleValue()));

        myService.stateProperty().addListener((observable, oldValue, newValue) -> txt_state.setText(String.valueOf(newValue)));
        myService.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            if (Worker.State.READY.equals(myService.getState()))
            {
                txt_value.setText("value");
                return;
            }
            ;
        });
        myService.titleProperty().addListener((observable, oldValue, newValue) ->
        {
            if (Worker.State.READY.equals(myService.getState()))
            {
                txt_title.setText("title");
                return;
            }
            txt_title.setText(newValue);
        });
        myService.messageProperty().addListener((observable, oldValue, newValue) ->
        {
            if (Worker.State.READY.equals(myService.getState()))
            {
                txt_message.setText("message");
                return;
            }
            txt_message.setText(newValue);
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Service");
        primaryStage.show();
    }

    private class MyService extends Service<Number>
    {
        @Override
        protected Task<Number> createTask()
        {
            return new Task<Number>()
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
            };
        }
    }
}
