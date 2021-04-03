package org.beuwi.msgbots.platform.gui.dialog;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.MenuItem;
import org.beuwi.msgbots.platform.gui.layout.BorderPane;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.window.WindowFrame;
import org.beuwi.msgbots.platform.gui.window.WindowType;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public abstract class DialogFrame extends BorderPane {
	private static final String DEFAULT_STYLE_CLASS = "dialog-box";

	// private static final Insets DEFAULT_PADDING_INSETS = new Insets(5.0);
	// private static final Insets DEFAULT_CONTENT_MARGIN = new Insets(10, 10, 0, 10);

	private EventHandler<ActionEvent> onActionHandler;
	// private EventHandler<ActionEvent> onCloseHandler;
	private EventHandler<ActionEvent> onOpenHandler;

	// WINDOW
	@FXML private BorderPane brpWinRoot;
	@FXML private BorderPane brpWinContent;
	@FXML private ImageView imvWinIcon;
	@FXML private Button btnWinClose;
	@FXML private Label lblWinTitle;
	@FXML private HBox hbxTitleBar;

	// DIALOG
	// @FXML private StackPane stpDialogMain;
	@FXML private ImageView imvDialogIcon;
	@FXML private HBox<Button> hbxButtonBar;

	// FOOTER
	@FXML private HBox hbxFooterArea;
	@FXML private Label lblFooterText;

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
	private Pane content;
	private String footerText;

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

	public void setContent(Pane content) {
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

	public void setOnAction(EventHandler<ActionEvent> handler) {
		onActionHandler = handler;
	}
	public void setOnOpen(EventHandler<ActionEvent> handler) {
		onOpenHandler = handler;
	}
	/* public void setOnClose(EventHandler<ActionEvent> handler) {
		onCloseHandler = handler;
	} */

	// 필요한지에 대한 건 추후 생각
	/* public void setMargin(double margin) {
		BorderPane.setMargin(brpWinMain,
			new Insets(10, margin, 10, margin));
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

	public HBox getFooterArea() {
		return hbxFooterArea;
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

		brpWinContent.setCenter(content);
		lblWinTitle.setText(title);

		btnWinClose.setGraphic(AllSVGIcons.get("Dialog.Close"));
		btnWinClose.setOnAction(event -> {
			stage.close();
		});

		/* ------------------------------------------------------ */

		btnAction.addEventHandler(ActionEvent.ACTION, event -> {
			this.daction();
		});

		btnCancel.addEventHandler(ActionEvent.ACTION, event -> {
			this.dclose();
		});

		/* ------------------------------------------------------ */

		switch (type) {
			// None : 아이콘 미포함
			case NONE :
				brpWinContent.getChildren().remove(imvDialogIcon);
				break;

			// Others : 아이콘 지정
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
				case ESCAPE : dclose(); break;
				case ENTER : daction(); break;
			}
		});

		// this.setPadding(DEFAULT_PADDING_INSETS);

		frame.setContent(this);
		frame.setTitle(title);
		frame.setType(WindowType.DIALOG);
		frame.create();

		// 다이얼 로그 박스에서 따로 포커스를 요청하지 않는 이상
		// 윈도우 닫기 버튼에 포커스가 돼 있어서 엔터키 시 액션 함수가 실행되는게 아닌 그냥 닫기가 됨
		// 그래서 해당 이유로 인해 다이얼로그에 포커스를 넘김
		content.requestFocus();
		// 아마 다이얼 로그 박스에서 포커스를 요청하려면 "Platform.runLater"를 이용해야 할 거임
	}

	public void dclose() {
		// this.close();
		stage.close();
	}

	// 상속용도
	protected abstract void open();
	protected abstract boolean action();
	/* public void close() {

	}; */

	// 호출용도
	public void dopen() {
		open();
		// "setOnOpen"이 설정됐으면
		if (onOpenHandler != null) {
			onOpenHandler.handle(null);
		}
		create();
	}
	// 호출용도
	public void daction() {
		// 액션이 성공해야지 창이 닫히도록 함
		// 즉 다이얼로그가 액션에 대한 리턴 값을 넘겨줘야 함
		boolean result = action();
		if (result) {
			// "setOnAction"이 설정됐으면
			if (onActionHandler != null) {
				onActionHandler.handle(null);
			}

			dclose();
		}
	}

	/* private void create() {
		// onOpenHandler.handle(new ActionEvent());
		onOpenHandler.handle(null);
	}
	// public abstract void init();
	private void action() {
		// onActionHandler.handle(new ActionEvent());
		onActionHandler.handle(null);
	} */
}