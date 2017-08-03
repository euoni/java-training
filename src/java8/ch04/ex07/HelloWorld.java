package java8.ch04.ex07;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {
	@Override
	public void start(Stage stage) {
		final Label message = new Label("Hello, JavaFX!");
		message.setFont(new Font(100));

		message.setBorder(new Border(
				new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, new CornerRadii(20), new BorderWidths(5))));

		stage.setScene(new Scene(message));
		stage.setTitle("Hello");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
