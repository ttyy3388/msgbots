package org.beuwi.simulator.platform.application.actions;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.beuwi.simulator.platform.application.views.MainWindowView;

public class ShowToastMessageAction
{
	public static void update(String content)
	{
		Stage stage = new Stage();

		StackPane toast = new StackPane(new Text(content));
		toast.getStyleClass().add("toast");
		toast.setOpacity(0);

		Scene scene = new Scene(toast);
		scene.setFill(Color.TRANSPARENT);

		stage.initStyle(StageStyle.TRANSPARENT);
		stage.initOwner(MainWindowView.getStage());
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();

		Timeline fadeInTimeline = new Timeline();
		KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(500), new KeyValue(stage.getScene().getRoot().opacityProperty(), 1));
		fadeInTimeline.getKeyFrames().add(fadeInKey1);
		fadeInTimeline.setOnFinished(event ->
		{
			new Thread(() ->
			{
				Timeline fadeOutTimeline = new Timeline();
				KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(500), new KeyValue (stage.getScene().getRoot().opacityProperty(), 0));
				fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
				fadeOutTimeline.setOnFinished((aeb) -> stage.close());
				fadeOutTimeline.play();
			}).start();
		});

		fadeInTimeline.play();
	}
}