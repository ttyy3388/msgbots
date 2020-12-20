package org.beuwi.msgbots.platform.gui.control;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.enums.ToastType;
import org.beuwi.msgbots.platform.gui.layout.ShadowPanel;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

// Toast Message
public class Toast extends ShadowPanel
{
	private static final String DEFAULT_STYLE_CLASS = "toast";

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

	public Toast(ToastType type, String title, String content)
	{
		loader = new FormLoader("frame", "toast-box-frame", this);
		menu = new ContextMenu
		(
			new Menu("Copy Text", event -> CopyStringAction.execute(title + "\n\n" + content))
		);

		menu.setNode(this);

		super.setContent(hbxBoxRoot);

		hbxBoxRoot.setPickOnBounds(true);
		// hbxBoxRoot.setMouseTransparent(false);

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

		/* setOnMouseClicked(event ->
		{
			requestFocus();
		}); */

		setPickOnBounds(false);

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

	public ToastView getView()
	{
		return (ToastView) getParent();
	}
}