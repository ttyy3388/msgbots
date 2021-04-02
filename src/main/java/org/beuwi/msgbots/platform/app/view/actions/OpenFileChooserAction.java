package org.beuwi.msgbots.platform.app.view.actions;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;

import java.io.File;

public class OpenFileChooserAction implements Action {
	@Override
	public void init() {
	}

	public static File execute(String title, String description, String... extensions) {
		return execute(title, new ExtensionFilter(description, extensions));
	}

	public static File execute(String title, ExtensionFilter filter) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(filter);
		fileChooser.setTitle(title);

		return fileChooser.showOpenDialog(MainView.getStage());
	}

	@Override
	public String getName() {
		return "open.file.chooser.action";
	}
}
