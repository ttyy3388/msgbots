package org.beuwi.msgbots.platform.gui.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.beuwi.msgbots.platform.gui.enums.NoticeType;
import org.beuwi.msgbots.platform.gui.layout.ShadowPane;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.util.AllSVGIcons;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class Notice extends ShadowPane
{
	private static final String DEFAULT_RESOURCE_NAME = "notice-box-frame";
	private static final String DEFAULT_STYLE_CLASS = "notice";

	private static final int DEFAULT_MIN_HEIGHT = 80;
	private static final int DEFAULT_PREF_HEIGHT = 80;

	@FXML private HBox hbxBoxRoot;
	@FXML private ImageView imvBoxIcon;
	@FXML private StackPane stpBoxMain;
	@FXML private Label lblBoxTitle;
	@FXML private Button btnBoxClose;

	public Notice(NoticeType type, String title, String content)
	{
		this(type, title, new Pane(new Label(content)));
	}

	public Notice(NoticeType type, String title, Region content)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm(DEFAULT_RESOURCE_NAME));
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		super.setContent(hbxBoxRoot);

		lblBoxTitle.setText(title);
		stpBoxMain.setContent(content);
		imvBoxIcon.setImage(ResourceUtils.getImage(type.toString()));

		btnBoxClose.setGraphic(AllSVGIcons.get("Box.Close"));
		btnBoxClose.setOnAction(event ->
		{
			getView().remove(this);
		});

		// setPickOnBounds(true);
		setMouseTransparent(false);

		addEventHandler(MouseEvent.MOUSE_MOVED, event ->
		{
			System.out.println(event.getTarget());
		});

		// setMinHeight(DEFAULT_MIN_HEIGHT);
		setPrefHeight(DEFAULT_PREF_HEIGHT);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	/* public void setView(ToastView parent)
	{
		this.parent = parent;
	} */

	public NoticeView getView()
	{
		return (NoticeView) getParent();
	}
}