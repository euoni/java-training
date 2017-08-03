package java8.ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ButtonPane extends Application {
	@Override
	public void start(Stage stage) {
		final BorderPane pane = new BorderPane();
		pane.setTop(new Button("Top"));
		BorderPane.setAlignment(pane.getTop(), Pos.TOP_CENTER);
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		pane.setBottom(new Button("Bottom"));
		BorderPane.setAlignment(pane.getBottom(), Pos.BOTTOM_CENTER);

		stage.setScene(new Scene(pane));
		stage.setTitle("Button");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
