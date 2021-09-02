package org.beuwi.msgbots.utils;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.net.URL;

// 해당 클래스로 접근하는 파일들은 기본값 파일들임 ([FileManager] 클래스와 반대)
public class ResourceUtils {
    public static InputStream getStream(@NamedArg("path") String path) {
        return ResourceUtils.class.getResourceAsStream(path);
    }
    public static String getURL(@NamedArg("path") String path) {
        return ResourceUtils.class.getResource(path).toExternalForm();
    }
    public static InputStream getFont(@NamedArg("name") String name) {
        return ResourceUtils.class.getResourceAsStream("/font/" + name + ".ttf");
    }
    public static Image getImage(@NamedArg("name") String name) {
        return new Image(ResourceUtils.class.getResource("/image/" + name + ".png").toExternalForm());
    }
    public static URL getForm(@NamedArg("name") String name) {
        return ResourceUtils.class.getResource("/form/" + name + ".fxml");
    }
    public static String getHtml(@NamedArg("name") String name) {
        return ResourceUtils.class.getResource("/webpage/html/" + name + ".html").toExternalForm();
    }
    public static String getTheme(@NamedArg("name") String name) {
        return ResourceUtils.class.getResource("/theme/" + name + ".css").toExternalForm();
    }
}