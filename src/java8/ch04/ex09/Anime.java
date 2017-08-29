package java8.ch04.ex09;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Anime extends Application implements Initializable {
	@FXML
	private Ellipse path;
	@FXML
	private Circle planet;

	@Override
	public void start(Stage stage) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("Anime.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final PathTransition transition = new PathTransition();
		transition.setNode(planet);
		transition.setPath(path);
		transition.setDuration(Duration.millis(5000));
		transition.setInterpolator(Interpolator.LINEAR);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.play();
	}
}
