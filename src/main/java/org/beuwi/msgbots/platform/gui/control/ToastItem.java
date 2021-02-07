package org.beuwi.msgbots.platform.gui.control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.enums.ToastType;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

// Toast Message
public class ToastItem extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "toast-item";

	// private static final int DEFAULT_NOTICE_WIDTH = 250;
	private static final int DEFAULT_NOTICE_HEIGHT = 60;

	@FXML private HBox hbxBoxRoot;
	@FXML private ImageView imvBoxIcon;
	@FXML private Label lblBoxTitle;
	@FXML private Label lblBoxText;
	@FXML private Button btnBoxClose;
	@FXML private HBox hbxBtnBar;

	private final FormLoader loader;
	private final ContextMenu menu;

	public ToastItem(ToastType type, String title, String content) {
		loader = new FormLoader("frame", "toast-item-frame", this);
		menu = new ContextMenu(
			new MenuItem("Copy Text", event -> CopyStringAction.execute(title + "\n\n" + content))
		);
		menu.setNode(this);

		hbxBoxRoot.setPickOnBounds(true);
		// hbxBoxRoot.setMouseTransparent(false);

		lblBoxTitle.setText(title);
		lblBoxTitle.setWrapText(true);

		lblBoxText.setText(content);
		lblBoxText.setWrapText(true);

		imvBoxIcon.setImage(ResourceUtils.getImage(type.toString()));

		btnBoxClose.setGraphic(AllSVGIcons.get("Box.Close"));
		btnBoxClose.setOnAction(event -> {
			getView().getChildren().remove(this);
		});

		/* setOnMouseClicked(event -> {
			requestFocus();
		}); */

		getChildren().setAll(hbxBoxRoot);
		// setMinWidth(DEFAULT_NOTICE_WIDTH);
		// setMaxWidth(DEFAULT_NOTICE_WIDTH);
		setMinHeight(DEFAULT_NOTICE_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	private ToastView parent;
	public void setView(ToastView parent) {
		this.parent = parent;
	}
	public ToastView getView() {
		return parent;
	}

	public HBox getButtonBar() {
		return hbxBtnBar;
	}
}