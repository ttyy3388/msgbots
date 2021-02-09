package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Scene;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.io.File;

public class ChangeColorThemeAction implements Action {
	/* public static void execute(File file) {
		execute(MainView.getStage().getScene(), file);
	} */
	public static void execute(ThemeType type) {
	    execute(MainView.getStage().getScene(), type);
	}

	/* public static void execute(Scene scene, File file) {
		scene.getStylesheets().setAll(file.getAbsolutePath());
	} */
	public static void execute(Scene scene, ThemeType type) {
        scene.getStylesheets().setAll(ResourceUtils.getTheme(type.toString()));
    }

	@Override
	public String getName() {
		return "set.color.theme.action";
	}
}
