package org.beuwi.msgbots.platform.app.view.actions;

import javafx.stage.FileChooser;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;

import java.io.File;

public class OpenFileChooserAction implements Action {
	@Override
	public void init() {
	}

	public static File execute(String title, FileChooser.ExtensionFilter filter) {
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
