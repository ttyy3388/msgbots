package org.beuwi.msgbots.platform.gui.control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.enums.NoticeType;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

// Toast Message
public class NoticeItem extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "notice-item";

	// private static final int DEFAULT_NOTICE_WIDTH = 250;
	private static final int DEFAULT_NOTICE_HEIGHT = 80;

	@FXML private HBox hbxBoxRoot;
	@FXML private ImageView imvBoxIcon;
	@FXML private Label lblBoxTitle;
	@FXML private Label lblBoxText;
	@FXML private Button btnBoxClose;
	@FXML private HBox hbxBtnBar;

	private final FormLoader loader;
	private final ContextMenu menu;

	private NoticeView parent;

	public NoticeItem(NoticeType type, String title, String content) {
		loader = new FormLoader("frame", "notice-item-frame", this);
		menu = new ContextMenu(
			new MenuItem("Copy Text", event -> CopyStringAction.execute(title + "\n\n" + content))
		);
		menu.setNode(this);

		widthProperty().addListener(change -> {
			System.out.println(change);
			System.out.println("Width Changed");
		});

		lblBoxTitle.setText(title);
		lblBoxTitle.setWrapText(true);

		lblBoxText.setText(content);
		lblBoxText.setWrapText(true);

		imvBoxIcon.setImage(ResourceUtils.getImage(type.toString()));

		btnBoxClose.setGraphic(AllSVGIcons.get("Box.Close"));
		btnBoxClose.setOnAction(event -> {
			getView().getItems().remove(this);
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

	public void setView(NoticeView parent) {
		this.parent = parent;
	}

	public HBox getButtonBar() {
		return hbxBtnBar;
	}

	public NoticeView getView() {
		return parent;
	}
}