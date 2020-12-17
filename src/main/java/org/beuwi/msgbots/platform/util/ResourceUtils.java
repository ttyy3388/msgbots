package org.beuwi.msgbots.platform.util;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtils
{
	public static File getFile(String path)
	{
		Path uri = null;

		try {
			uri = Paths.get(ResourceUtils.class.getResource(path).toURI());
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return uri.toFile();
	}

	public static String getURL(String path)
	{
		return ResourceUtils.class.getResource(path).toExternalForm();
	}

	public static String getHtml(String name)
	{
		return ResourceUtils.class.getResource("/htmls/" + name + ".html").toExternalForm();
	}

	public static InputStream getFont(String name)
	{
		return ResourceUtils.class.getResourceAsStream("/fonts/" + name + ".ttf");
	}

	public static Image getImage(String name)
	{
		return new Image(ResourceUtils.class.getResource("/images/" + name + ".png").toExternalForm());
	}

	public static URL getForm(String name)
	{
		return ResourceUtils.class.getResource("/forms/" + name + ".fxml");
	}

	public static String getStyle(String name)
	{
		return ResourceUtils.class.getResource("/styles/" + name + ".css").toExternalForm();
	}

	public static String getTheme(String name)
	{
		return ResourceUtils.class.getResource("/themes/" + name + ".css").toExternalForm();
	}
}