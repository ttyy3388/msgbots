package org.beuwi.msgbots.platform.util;

import javafx.scene.image.Image;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;

import java.io.InputStream;
import java.net.URL;

public class ResourceUtils {
	public static InputStream getStream(String path) {
		return ResourceUtils.class.getResourceAsStream(path);
	}

	/* public static InputStream getData(String name) {
		return getStream("data/" + name);
	} */

	public static String getURL(String path) {
		return ResourceUtils.class.getResource(path).toExternalForm();
	}

	public static String getHtml(String theme, String name) {
		return ResourceUtils.class.getResource("/htmls/" + theme + "/" + name + ".html").toExternalForm();
	}
	public static String getHtml(String name) {
		return ResourceUtils.class.getResource("/htmls/" + name + ".html").toExternalForm();
	}

	public static InputStream getFont(String name) {
		return ResourceUtils.class.getResourceAsStream("/fonts/" + name + ".ttf");
	}

	public static Image getImage(String name) {
		return new Image(ResourceUtils.class.getResource("/images/" + name + ".png").toExternalForm());
	}

	public static URL getForm(String name) {
		return ResourceUtils.class.getResource("/forms/" + name + ".fxml");
	}

	public static String getStyle(String name) {
		return ResourceUtils.class.getResource("/styles/" + name + ".css").toExternalForm();
	}

	public static String getTheme(String name) {
		return ResourceUtils.class.getResource("/themes/" + name + ".css").toExternalForm();
	}

	public static String getTheme(ThemeType theme) {
		return ResourceUtils.class.getResource("/themes/" + theme.toString() + ".css").toExternalForm();
	}
}