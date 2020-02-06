package com.tec.anji.widget.progressbar;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * ProgressBar、ProgressIn
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ProgressBar progressBar = new ProgressBar(0);
        ProgressIndicator progressIndicator = new ProgressIndicator(0);

        AnchorPane root = new AnchorPane(progressBar, progressIndicator);
        AnchorPane.setTopAnchor(progressBar, 50.0);
        AnchorPane.setLeftAnchor(progressBar, 50.0);
        AnchorPane.setTopAnchor(progressIndicator, 50.0);
        AnchorPane.setLeftAnchor(progressIndicator, 200.0);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("ProgressBar");
        primaryStage.show();

        new ProgressRunning(progressIndicator, 0, 1000).start();
    }

    private class ProgressRunning extends ScheduledService<Double>
    {
        private ProgressIndicator progressIndicator;

        private double progress;

        private ProgressRunning(ProgressIndicator progressIndicator, int delay, int period)
        {
            this.progressIndicator = progressIndicator;
            this.setDelay(Duration.millis(delay));
            this.setPeriod(Duration.millis(period));
        }

        @Override
        protected Task<Double> createTask()
        {
            return new Task<Double>()
            {
                @Override
                protected Double call() throws Exception
                {
                    return progress += 0.1;
                }

                @Override
                protected void updateValue(Double value)
                {
                    if ((int) progressIndicator.getProgress() == 1)
                    {
                        progressIndicator.setProgress(0);
                        ProgressRunning.this.cancel();
                        return;
                    }
                    super.updateValue(value);
                    progressIndicator.setProgress(value);
                }
            };
        }
    }
}
