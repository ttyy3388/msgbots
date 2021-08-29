package org.beuwi.msgbots.program.gui.window;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.program.gui.control.Button;
import org.beuwi.msgbots.program.gui.control.ContextMenu;
import org.beuwi.msgbots.program.gui.control.Label;
import org.beuwi.msgbots.program.gui.layout.BorderPane;
import org.beuwi.msgbots.program.gui.layout.HBox;
import org.beuwi.msgbots.program.gui.layout.StackPane;
import org.beuwi.msgbots.program.utils.AllSVGIcons;

// [WindowWrapper]로만 접근 가능
class WindowFrame extends StackPane {
	// private static final Insets DEFAULT_PADDING_INSETS = new Insets(5.0);
	// private static final Insets DEFAULT_CONTENT_MARGIN = new Insets(10, 10, 0, 10);

	@FXML private BorderPane brpWinMain;
	@FXML private Button btnWinClose;
	@FXML private Label lblWinTitle;
	@FXML private ImageView imvWinIcon;
	@FXML private HBox hbxTitleBar;

	private final ContextMenu ctxWinMenu = new ContextMenu();

	private final WindowBuilder builder;

	// Default
	private final FormLoader loader;
	private final WindowEvent event;
	private final WindowScene scene;
	private final WindowType type;
	private final Stage stage;

	private Node content;
	private String title;
	private Node focusNode;

	protected WindowFrame(WindowType type, Stage stage) {
		this.stage = stage;
		this.type = type;

		loader = new FormLoader();
		loader.setName("window-box-frame");
		loader.setRoot(this);
		loader.setController(this);
		loader.load();

		scene = new WindowScene(this);
		builder = new WindowBuilder(stage);
		event = new WindowEvent(stage);
		/* WindowType type = builder.getType();
		if (type.equals(WindowType.DIALOG)) {
			Region content = builder.getContent();
			content = new ShadowPane(content); // 그림자 효과 적용을 위해
			builder.setContent(content);
		} */
		getStyleClass().add("window-box");
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(Node content) {
		this.content = content;
	}
	public void focusNode(Node focusNode) {
		this.focusNode = focusNode;
	}

	public String getTitle() {
		return title;
	}
	public Node getContent() {
		return content;
	}
	public Stage getStage() {
		return stage;
	}

	/* ------------------------------------------------------ */

	public void setUseTitleBar(boolean value) {
		Parent parent = hbxTitleBar.getParent();
		if (value) {
			if (parent == null) {
				brpWinMain.setTop(hbxTitleBar);
			}
		}
		else {
			if (parent != null) {
				brpWinMain.getChildren().remove(hbxTitleBar);
			}
		}
	}
	public HBox getTitleBar() {
		return hbxTitleBar;
	}

	/* ------------------------------------------------------ */

	public void create() {
		if (content == null) {
			throw new RuntimeException("Content is not set");
		}

		switch (type){
			case WINDOW -> {
			}
			case DIALOG -> {
				// [Dialog Box]에서 처리하도록 변경함
				// stage.initModality(Modality.WINDOW_MODAL);
				// stage.initOwner(ViewManager.getStage());
			}
		}

		/* ------------------------------------------------------ */

		// 기본 아이템들 추가
		/* ctxWinMenu.getItems().addAll(
			new MenuItem("Maximize"),
			new MenuItem("Minimize"),
			new MenuItem("Close")
		); */
        // 구분 아이템을 추가한 후 커스텀 메뉴를 추가함
		// ctxWinMenu.getItems().add(new Separator());
		// ctxWinMenu.getItems().addAll(customMenus);
		ctxWinMenu.setNode(imvWinIcon);
		btnWinClose.setGraphic(AllSVGIcons.get("Window.Close"));
		btnWinClose.setOnAction(event -> {
			stage.close();
		});
		brpWinMain.setCenter(content);
		lblWinTitle.setText(title);

		/* ------------------------------------------------------ */

		event.setMovable(this);

		// stage.initModality(Modality.WINDOW_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT);
		// scene.setFill(Color.TRANSPARENT);

		builder.setContent(this);
		builder.setTitle(title);
		builder.setScene(scene);
		builder.create();

		if (focusNode != null) {
			Platform.runLater(() -> {
				focusNode.requestFocus();
			});
		}
	}
}
