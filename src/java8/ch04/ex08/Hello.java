package java8.ch04.ex08;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Hello extends Application implements Initializable {
	@FXML
	private Label top;
	@FXML
	private Label center;
	@FXML
	private Label bottom;

	@Override
	public void start(Stage stage) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("Hello.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		top.setText("Top");
		center.setText("Center");
		bottom.setText("Bottom");
	}
}
