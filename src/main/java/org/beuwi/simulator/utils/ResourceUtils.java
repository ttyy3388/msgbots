package org.beuwi.simulator.utils;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ResourceUtils
{
	public static InputStream getFont(String name) throws Exception
	{
		return ResourceUtils.class.getResourceAsStream("/images/" + name);
	}

	public static Image getImage(String name)
	{
		return new Image(ResourceUtils.class.getResource("/images/" + name).toExternalForm());
	}
}