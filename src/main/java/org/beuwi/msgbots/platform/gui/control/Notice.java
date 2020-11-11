package org.beuwi.msgbots.platform.gui.control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.base.CopyAction;
import org.beuwi.msgbots.platform.gui.enums.NoticeType;
import org.beuwi.msgbots.platform.gui.layout.ShadowPanel;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class Notice extends ShadowPanel
{
	private static final String DEFAULT_STYLE_CLASS = "notice";

	// private static final int DEFAULT_NOTICE_WIDTH = 250;
	private static final int DEFAULT_NOTICE_HEIGHT = 80;

	@FXML private HBox hbxBoxRoot;
	@FXML private ImageView imvBoxIcon;
	@FXML private Label lblBoxTitle;
	@FXML private Label lblBoxContent;
	@FXML private Button btnBoxClose;
	@FXML private HBox hbxBtnBar;

	private final FormLoader loader;
	private final ContextMenu menu;

	public Notice(NoticeType type, String title, String content)
	{
		loader = new FormLoader("frame", "notice-box-frame", this);
		menu = new ContextMenu
		(
			new Menu("Copy Text", event -> CopyAction.execute(title + "\n\n" + content))
		);

		menu.setNode(this);

		super.setContent(hbxBoxRoot);

		lblBoxTitle.setText(title);
		lblBoxTitle.setWrapText(true);

		lblBoxContent.setText(content);
		lblBoxContent.setWrapText(true);

		imvBoxIcon.setImage(ResourceUtils.getImage(type.toString()));

		btnBoxClose.setGraphic(AllSVGIcons.get("Box.Close"));
		btnBoxClose.setOnAction(event ->
		{
			getView().remove(this);
		});

		setPickOnBounds(true);
		setMouseTransparent(false);

		// setMinWidth(DEFAULT_NOTICE_WIDTH);
		// setMaxWidth(DEFAULT_NOTICE_WIDTH);
		setMinHeight(DEFAULT_NOTICE_HEIGHT);
		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	/* public void setView(ToastView parent)
	{
		this.parent = parent;
	} */

	public HBox getButtonBar()
	{
		return hbxBtnBar;
	}

	public NoticeView getView()
	{
		return (NoticeView) getParent();
	}
}