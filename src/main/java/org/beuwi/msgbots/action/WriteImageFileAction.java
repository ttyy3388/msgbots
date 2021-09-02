package org.beuwi.msgbots.action;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import org.beuwi.msgbots.base.impl.Executor;

import javax.imageio.ImageIO;
import java.io.File;

public class WriteImageFileAction implements Executor {
	private static WriteImageFileAction instance = null;

	// Before > After
	public void execute(File before, File after, String format) {
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(new Image(before.toURI().toString()), null), "png", after);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static WriteImageFileAction getInstance() {
		if (instance == null) {
			instance = new WriteImageFileAction();
		}
		return instance;
	}
}
