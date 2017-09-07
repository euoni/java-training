package arclock;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Clock extends Application implements Initializable {
	private final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
	private QRCapture capture;

	@FXML
	private ImageView image;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timer.scheduleAtFixedRate(() -> {
			final BufferedImage frame = capture.process();
			if (frame != null) {
				final Image buf = SwingFXUtils.toFXImage(frame, null);
				Platform.runLater(() -> {
					image.setImage(buf);
					image.setFitWidth(buf.getWidth());
					image.setFitHeight(buf.getHeight());
				});
			}
		}, 0, 33, TimeUnit.MILLISECONDS);
	}

	@Override
	public void init() throws Exception {
		final int deviceNumber = Integer.parseInt(getParameters().getNamed().getOrDefault("device", "0"));
		final int width = Integer.parseInt(getParameters().getNamed().getOrDefault("width", "1280"));
		final int height = Integer.parseInt(getParameters().getNamed().getOrDefault("height", "720"));
		capture = new QRCapture(deviceNumber, width, height);
		capture.start();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Clock.fxml"));
		fxmlLoader.setController(this);
		final Parent root = fxmlLoader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		timer.shutdown();
		capture.stop();
	}
}
