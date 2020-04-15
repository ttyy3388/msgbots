package org.beuwi.simulator.platform.ui.window;

import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class IWindowScene extends Scene
{
	public IWindowScene(Region root)
	{
		super(root);

		getStylesheets().add(getClass().getResource("/themes/base.css").toExternalForm());
		getStylesheets().add(getClass().getResource("/themes/dark.css").toExternalForm());
	}
}