package org.beuwi.msgbots.platform.app.action;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;
import org.beuwi.msgbots.platform.app.view.dialogs.ShowErrorDialog;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class WriteImageFileAction implements Action {
	@Override
	public void init() {

	}

	// Before > After
	public static void execute(File before, String format, File after) {
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(new Image(before.toURI().toString()), null), "png", after);
		}
		catch (IOException e) {
            DisplayErrorDialogAction.execute(e);
		}
	}

	@Override
	public String getName() {
		return "write.image.file.action";
	}
}
