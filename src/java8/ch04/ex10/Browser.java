package java8.ch04.ex10;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Browser extends Application implements Initializable {
	@FXML
	private Button back;
	@FXML
	private TextField url;
	@FXML
	private WebView view;

	@Override
	public void start(Stage stage) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("Browser.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final WebEngine engine = view.getEngine();
		engine.load("https://google.com/");

		back.disableProperty().bind(engine.getHistory().currentIndexProperty().isEqualTo(0));
		back.setOnAction(e -> engine.getHistory().go(-1));

		engine.locationProperty().addListener((observable, oldValue, newValue) -> url.setText(newValue));
		url.setOnAction(e -> engine.load(url.getText()));
	}
}
