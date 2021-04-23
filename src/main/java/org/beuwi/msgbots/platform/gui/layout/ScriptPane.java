package org.beuwi.msgbots.platform.gui.layout;

import javafx.fxml.FXML;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

import java.io.File;

public class ScriptPane extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "script-pane";

	private final FormLoader loader;
	private final VBox root;

	@FXML private HBox titleBar;
	@FXML private Label pathLabel;
	@FXML private Editor editor;
	@FXML private ResizePane resizePane;
	@FXML private LogView logView;

	public ScriptPane(File file) {
		loader = new FormLoader("layout", "script-pane-layout", this);
		root = loader.getRoot();

		pathLabel.setText(file.getAbsolutePath());

		getChildren().setAll(root);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
