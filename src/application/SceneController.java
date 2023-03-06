package application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;


public class SceneController  {
	
	@FXML
	private ProgressBar progressBarLearn;
	
	@FXML
	private Label labelLearningProgress;
	
	@FXML
	private Button buttonStartLearn;
	
	
	@FXML
	public void onButtonLearnClicked(ActionEvent event) {
	    // When you click on the button, this button is hidden, and the learning process begins.
	    // After the download is completed, the loader is replaced with a message about a successful download.

	    buttonStartLearn = (Button) event.getSource();
	    buttonStartLearn.setVisible(false);
	    labelLearningProgress.setVisible(true);
	    progressBarLearn.setVisible(true);

	    int totalIterations = 100000;

//	 // создаем новый поток и запускаем Main.LoadData(totalIterations)
//	    Thread loadDataThread = new Thread(() -> {
//	        Main.LoadData(totalIterations);
//	    });
//	    loadDataThread.start();
//
//	    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//	    scheduler.scheduleAtFixedRate(() -> Progress(progressBarLearn, labelLearningProgress), 0, 2000, TimeUnit.MICROSECONDS);
//
//	    // остановить выполнение после 10 секунд
//	    scheduler.schedule(() -> {
//	        scheduler.shutdown();
//	        // дожидаемся завершения выполнения потока
//	        try {
//	            loadDataThread.join();
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    }, 20, TimeUnit.SECONDS);
	    
	    Task<Integer> task = new Task<Integer>() {
	         @Override protected Integer call() throws Exception {
	             int iterations;
	             for (iterations = 0; iterations < 100000; iterations++) {
	                 if (isCancelled()) {
	                     break;
	                 }
	                 System.out.println("Iteration " + iterations);
	             }
	             return iterations;
	         }
	     };
	     
	     task.run();
	    		 
	}

	private void Progress(ProgressBar p, Label label) {
	    double value = p.getProgress();
	    if (value < 0) {
	        value = 0.1;
	    } else {
	        value += 0.0001;
	        if (value >= 1.0) {
	            value = 1.0;
	        }
	    }

	    p.setProgress(value);

	    double test = value;
	    Platform.runLater(() -> {
	        label.setText("Progress : " + Integer.toString((int) (test * 100)));
	    });
	}
	
}
