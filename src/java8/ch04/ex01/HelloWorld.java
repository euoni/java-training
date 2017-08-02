package java8.ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {
	@Override
	public void start(Stage stage) {
		final Label message = new Label("Hello, JavaFX!");
		message.setFont(new Font(100));

		final TextField textField = new TextField(message.getText());
		message.textProperty().bind(textField.textProperty());

		final VBox root = new VBox();
		root.getChildren().addAll(textField, message);

		stage.setScene(new Scene(root));
		stage.setTitle("Hello");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
