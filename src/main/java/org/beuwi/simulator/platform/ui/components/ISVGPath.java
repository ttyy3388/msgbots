package org.beuwi.simulator.platform.ui.components;

import javafx.scene.shape.SVGPath;

public class ISVGPath extends SVGPath
{
	public ISVGPath(String path)
	{
		this.setContent(path);
	}
}