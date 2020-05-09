package org.beuwi.simulator.platform.ui.window;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import org.beuwi.simulator.utils.ResourceUtils;

public class IWindowScene extends Scene
{
	public IWindowScene(Region root)
	{
		super(root);

		setFill(Color.TRANSPARENT);

		getStylesheets().add(ResourceUtils.getTheme("base"));
		getStylesheets().add(ResourceUtils.getTheme("dark"));
	}
}