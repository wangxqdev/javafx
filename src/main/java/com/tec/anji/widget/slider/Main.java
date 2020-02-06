package com.tec.anji.widget.slider;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Slider
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Slider slider = new Slider(0, 100, 0);
        slider.setStyle("-fx-show-tick-labels: true; -fx-show-tick-marks: true; -fx-major-tick-unit: 10; " +
                "-fx-snap-to-ticks: true; -fx-block-increment: 10; -fx-pref-width: 250px;");

        AnchorPane root = new AnchorPane(slider);
        AnchorPane.setTopAnchor(slider, 50.0);
        AnchorPane.setLeftAnchor(slider, 50.0);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Slider");
        primaryStage.show();

        new SliderRunning(slider, 0, 1000).start();
    }

    private class SliderRunning extends ScheduledService
    {

        private Slider slider;

        public SliderRunning(Slider slider, int delay, int period)
        {
            this.slider = slider;
            this.setDelay(Duration.millis(delay));
            this.setPeriod(Duration.millis(period));
        }

        @Override
        protected Task createTask()
        {
            return new Task()
            {
                @Override
                protected Object call() throws Exception
                {
                    // do nothing
                    return null;
                }

                @Override
                protected void updateValue(Object value)
                {
                    if (slider.getMax() == slider.getValue())
                    {
                        slider.setValue(slider.getMin());
                        SliderRunning.this.cancel();
                        return;
                    }
                    super.updateValue(value);
                    slider.increment();
                }
            };
        }
    }
}
