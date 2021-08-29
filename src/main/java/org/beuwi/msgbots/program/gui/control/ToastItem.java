package org.beuwi.msgbots.program.gui.control;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.enums.ToastType;
import org.beuwi.msgbots.program.app.action.CopyClipboardAction;
import org.beuwi.msgbots.program.gui.layout.HBox;
import org.beuwi.msgbots.program.utils.AllSVGIcons;
import org.beuwi.msgbots.utils.ResourceUtils;
import org.beuwi.msgbots.program.utils.GlobalActions;

// Toast Message
public class ToastItem extends HBox {
	// private static final int DEFAULT_WIDTH = 250;
	private static final int DEFAULT_HEIGHT = 80;

	@FXML private ImageView imvIcon;
	@FXML private Label lblTitle;
	@FXML private Label lblText;
	@FXML private Button btnClose;

	private final FormLoader loader;
	private final ContextMenu menu;

	private ToastView parent;

	public ToastItem(ToastType type, String title, String content) {
		loader = new FormLoader();
		loader.setName("toast-item-frame");
		loader.setRoot(this);
		loader.setController(this);
		loader.load();

		menu = new ContextMenu(
			GlobalActions.COPY_TEXT.custom(event -> {
				CopyClipboardAction.getInstance().execute(title + "\n\n" + content);
			}).toMenu()
		);
		menu.setNode(this);

		// hbxRoot.setPickOnBounds(false);
		// hbxRoot.setFocusTraversable(true);
		// hbxRoot.setMouseTransparent(true);

		lblTitle.setText(title);
		lblTitle.setWrapText(true);

		lblText.setText(content);
		lblText.setWrapText(true);

		imvIcon.setImage(ResourceUtils.getImage(type.toString()));

		btnClose.setGraphic(AllSVGIcons.get("Box.Close"));
		btnClose.setOnAction(event -> {
			getView().getItems().remove(this);
		});

		/* setOnMouseClicked(event -> {
			requestFocus();
		}); */

		addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			getView().selectItem(this);
		});

		// setMinWidth(DEFAULT_WIDTH);
		// setMaxWidth(DEFAULT_WIDTH);
		setMinHeight(DEFAULT_HEIGHT);

		// :info, :error, :warning
		pseudoClassStateChanged(PseudoClass.getPseudoClass(type.toString()), true);
		getStyleClass().add("toast-item");
	}

	public void setView(ToastView parent) {
		this.parent = parent;
	}

	public ToastView getView() {
		return parent;
	}

	/* public H getButtonBar() {
		return hbxBtnBar;
	} */
}