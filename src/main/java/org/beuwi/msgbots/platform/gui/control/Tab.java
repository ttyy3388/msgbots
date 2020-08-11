package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.util.AllSVGIcons;

import java.util.HashMap;
import java.util.Map;

public class Tab extends HBox
{
	private static final Pos DEFAULT_TAB_ALIGNMENT = Pos.CENTER;
	private static final int DEFAULT_TAB_SPACING = 7;
	private static final int DEFAULT_TAB_WIDTH = 50;
	// private static final int DEFAULT_TAB_HEIGHT = 50;

	private static final int DEFAULT_ICON_SIZE = 12;
	private static final int DEFAULT_BUTTON_SIZE = 10;

	// HBox : Header, Node : Content
	private final Map<HBox, Node> tab = new HashMap<>();

	private final ImageView image = new ImageView();

	// Title Label
	private final Label label = new Label();

	// Close Button
	private final Button button = new Button();

	// private final HBox header = new HBox();
	private final Node content;

	private final TabType type;
	private final String title;
	private final Image icon;

	private TabPane pane;

	public Tab()
	{
		this(null);
	}

	public Tab(String title)
	{
		this(title, new Pane());
	}

	public Tab(String title, Node content)
	{
		this(title, content, TabType.BUTTON);
	}

	public Tab(String title, Node content, TabType type)
	{
		this(null , title, content, type);
	}

	// 추후 사이드 옵션도 구현해야됨
	public Tab(Image icon, String title, Node content, TabType type)
	{
		this.type = type;
		this.icon = icon;
		this.title = title;
		this.content = content;

		if (icon != null)
		{
			image.setImage(icon);
			image.setFitWidth(DEFAULT_ICON_SIZE);
			image.setFitHeight(DEFAULT_ICON_SIZE);
		}

		if (title != null)
		{
			label.setText(title);
		}

		tab.put(this, content);

		content.setId("@content::" + title);

		button.setGraphic(AllSVGIcons.get("Tab.Close"));
		button.setPrefWidth(DEFAULT_BUTTON_SIZE);

		// Set Styles
		label.getStyleClass().add("tab-label");
		button.getStyleClass().add("tab-button");
		content.getStyleClass().add("tab-content");

		button.setOnAction(event ->
		{
			pane.close(this);
		});

		switch (type)
		{
			case BUTTON :

				this.setOnMousePressed(event ->
				{
					pane.select(this);
				});

                break;

			case TOGGLE :

                break;
		}

		setId("@tab::" + title);
		setPadding(new Insets(0, 10, 0 ,10));
		setSpacing(DEFAULT_TAB_SPACING);
		setMinWidth(DEFAULT_TAB_WIDTH);
		// setPrefHeight(DEFAULT_TAB_HEIGHT);
		setAlignment(DEFAULT_TAB_ALIGNMENT);
		getChildren().addAll(image, label, button);
		getStyleClass().setAll("tab");
	}

	public TabType getType()
	{
		return type;
	}

	public String getTitle()
	{
		return title;
	}

	// Close Button
	public Button getButton()
	{
		return button;
	}

	public HBox getHeader()
	{
		return this;
	}

	public Node getContent()
	{
		return content;
	}

    public TabPane getTabPane()
    {
        return pane;
    }

    public void setTabPane(TabPane pane)
    {
        this.pane = pane;
    }
}
