package java8.ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleWindow extends Application {
	@Override
	public void start(Stage stage) {
		final Circle circle = new Circle();
		circle.setFill(Color.BLACK);

		final Pane root = new Pane();
		root.getChildren().add(circle);

		final Scene scene = new Scene(root);
		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
		circle.radiusProperty().bind(Bindings.divide(Bindings.min(scene.widthProperty(), scene.heightProperty()), 2.0));

		stage.setScene(scene);
		stage.setTitle("Circle");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
