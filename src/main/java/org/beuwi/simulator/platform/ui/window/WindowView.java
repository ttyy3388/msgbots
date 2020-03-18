package org.beuwi.simulator.platform.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowView extends Scene
{
	Region root = null;

	public WindowView(Stage stage, Region content, int type)
	{
		super(content);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxmls/WindowView.fxml"));
		loader.setController(new WindowController(stage, content, type));

		try
		{
			root = loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		super.setFill(Color.TRANSPARENT);
		super.setRoot(root);

		stage.setScene(this);
        stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.toFront();
	}
}