package org.beuwi.simulator.platform.ui.window;

import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class WindowScene extends Scene
{
	public WindowScene(Region root)
	{
		super(root);

		getStylesheets().add(getClass().getResource("/themes/default.css").toExternalForm());
		getStylesheets().add(getClass().getResource("/themes/dark.css").toExternalForm());

		// getStylesheets().add(getClass().getResource("/styles/WindowView.css").toExternalForm());
	}
}