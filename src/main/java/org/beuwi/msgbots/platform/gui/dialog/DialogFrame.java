package org.beuwi.msgbots.platform.gui.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.MenuItem;
import org.beuwi.msgbots.platform.gui.layout.BorderPane;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.window.WindowFrame;
import org.beuwi.msgbots.platform.gui.window.WindowType;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public abstract class DialogFrame extends BorderPane {
	private static final String DEFAULT_STYLE_CLASS = "dialog-box";
	private static final Insets DEFAULT_PADDING_INSETS = new Insets(5.0);

	// WINDOW
	@FXML private BorderPane brpWinRoot;
	@FXML private BorderPane brpWinMain; // Window Content
	@FXML private ImageView imvWinIcon;
	@FXML private Button btnWinClose;
	@FXML private Label lblWinTitle;

	@FXML private HBox hbxTitleBar;

	// DIALOG
	@FXML private ImageView imvDialogIcon;
	@FXML private HBox<Button> hbxButtonBar;

	@FXML private Button btnAction;
	@FXML private Button btnCancel;

	private final FormLoader loader;

	private final DialogEvent event;
	private final WindowFrame frame;
	private final ContextMenu menu;

	private final Stage stage;

	// Default Type : None
	private final DialogType type;

	// private StageStyle winstyle = StageStyle.UNDECORATED; // Default
	// private Modality modality = Modality.WINDOW_MODAL; // Default
	// private Stage owner = null; // Default

	private String title;
	private Node content;

	public DialogFrame() {
		this(DialogType.NONE);
	}

	public DialogFrame(DialogType type) {
		this(type, new Stage());
	}

	public DialogFrame(DialogType type, Stage stage) {
		this.stage = stage;
		this.type = type;

		loader = new FormLoader("frame", "dialog-box-frame", this);
		menu = new ContextMenu(
			new MenuItem("Close")
		);

		frame = new WindowFrame(stage);
		event = new DialogEvent(stage);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(Node content) {
		this.content = content;
	}

	/* public void setModality(Modality modality) {
		this.modality = modality;
	}

	public void setWinStyle(StageStyle winstyle) {
		this.winstyle = winstyle;
	} */

	public Stage getStage() {
		return stage;
	}

	public String getTitle() {
		return title;
	}

	public Node getContent() {
		return content;
	}

	/* public StageStyle getWinStyle() {
		return winstyle;
	}

	public Modality getModality() {
		return modality;
	}

	public Stage getOwner() {
		return owner;
	} */

	/* public void setUseTitlebar(boolean value) {
		if (value) {
			brpWinRoot.getChildren().add(hbxTitleBar);
		}
		else {
			brpWinRoot.getChildren().remove(hbxTitleBar);
		}
	} */

	// Use Action Button, Use Cancel Button
	public void setUseButton(boolean action, boolean cancel) {
		if (!action) {
			hbxButtonBar.getChildren().remove(btnAction);
		}
		if (!cancel) {
			hbxButtonBar.getChildren().remove(btnCancel);
		}
	}

	public HBox<Button> getButtonBar() {
		return hbxButtonBar;
	}

	public Button getActionButton() {
		return btnAction;
	}

	public Button getCancelButton() {
		return btnCancel;
	}

	private void initWinSize() {
		// content.setMinWidth(content.getPrefWidth());
		// content.setMinHeight(content.getPrefHeight());
		// content.setMaxWidth(content.getPrefHeight());
		// content.setMaxHeight(content.getPrefHeight());
	}

	public void create() {
		super.setCenter(brpWinRoot);
		event.setMovable(brpWinRoot);

		brpWinMain.setCenter(content);
		lblWinTitle.setText(title);

		btnWinClose.setGraphic(AllSVGIcons.get("Dialog.Close"));
		btnWinClose.setOnAction(event -> {
			stage.close();
		});

		/* ------------------------------------------------------ */

		btnAction.addEventHandler(ActionEvent.ACTION, event -> {
			this.action();
		});

		btnCancel.addEventHandler(ActionEvent.ACTION, event -> {
			this.close();
		});

		/* ------------------------------------------------------ */

		switch (type) {
			// None : Remove icon
			case NONE : brpWinMain.getChildren().remove(imvDialogIcon); break;

			// Others : Display icon
			case EVENT : imvDialogIcon.setImage(ResourceUtils.getImage("event_big")); break;
			case WARNING : imvDialogIcon.setImage(ResourceUtils.getImage("warning_big")); break;
			case ERROR : imvDialogIcon.setImage(ResourceUtils.getImage("error_big")); break;
		}

		/* ------------------------------------------------------ */

		/* stage.focusedProperty().addListener(change -> {
			// pseudoClassStateChanged(FOCUSED_PSEUDO_CLASS, stage.isFocused());
		}); */

		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
				case ESCAPE : stage.close(); break;
				case ENTER :
					this.action();
					this.close();
					break;
			}
		});

		// this.setPadding(DEFAULT_PADDING_INSETS);

		frame.setContent(this);
		frame.setTitle(title);
		frame.setType(WindowType.DIALOG);
		frame.create();
	}

	public void close() {
		stage.close();
	}

	public abstract void open();
	// public abstract void init();
	public abstract void action();
}