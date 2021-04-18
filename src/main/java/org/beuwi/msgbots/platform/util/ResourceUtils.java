package org.beuwi.msgbots.platform.util;

import javafx.scene.image.Image;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;

import java.io.InputStream;
import java.net.URL;

// 해당 클래스로 접근하는 파일들은 기본값 파일들임 ("FileManager"와 반대)
public class ResourceUtils {
	/* public static InputStream getData(String name) {
		return getStream("data/" + name);
	} */

	public static InputStream getStream(String path) {
		return ResourceUtils.class.getResourceAsStream(path);
	}

	public static String getURL(String path) {
		return ResourceUtils.class.getResource(path).toExternalForm();
	}

	public static InputStream getFont(String name) {
		return ResourceUtils.class.getResourceAsStream("/font/" + name + ".ttf");
	}

	public static Image getImage(String name) {
		return new Image(ResourceUtils.class.getResource("/image/" + name + ".png").toExternalForm());
	}

	public static URL getForm(String name) {
		return ResourceUtils.class.getResource("/form/" + name + ".fxml");
	}

	/* public static String getStyle(String name) {
		return ResourceUtils.class.getResource("/styles/" + name + ".css").toExternalForm();
	} */

	/* public static InputStream getData(String name) {
		return ResourceUtils.class.getResourceAsStream("/data/" + name);
	} */

	public static String getTheme(String name) {
		return ResourceUtils.class.getResource("/theme/" + name + ".css").toExternalForm();
	}

	public static String getTheme(ThemeType theme) {
		return ResourceUtils.class.getResource("/theme/" + theme.toString() + ".css").toExternalForm();
	}

	public static String getHtml(String name) {
		return ResourceUtils.class.getResource("/page/html/" + name + ".html").toExternalForm();
	}
	/* public static String getStyle(String name) throws Exception {
		return ResourceUtils.class.getResource("/page/style/" + name + ".css").toURI().toURL().toExternalForm();
	} */
}