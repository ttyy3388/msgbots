package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.util.AllSVGIcons;

@DefaultProperty("content")
public class Tab extends HBox
{
	private static final Insets DEFAULT_TAB_PADDING = new Insets(0, 10, 0, 30);

	private static final String DEFAULT_STYLE_CLASS = "tab";
	private static final String BUTTON_STYLE_CLASS = "tab-button";
	private static final String TITLE_STYLE_CLASS = "tab-label";

	private static final Pos DEFAULT_TAB_ALIGNMENT = Pos.CENTER;
	private static final int DEFAULT_TAB_SPACING = 10;
	private static final int DEFAULT_TAB_WIDTH = 80;

	private static final int DEFAULT_BUTTON_SIZE = 10;
	private static final int DEFAULT_TITLE_SIZE = 60;

	private final ObjectProperty<Node> content = new SimpleObjectProperty<>(null);
	private final BooleanProperty closable = new SimpleBooleanProperty(true);
	private final BooleanProperty pinned = new SimpleBooleanProperty(false);

	// Tab Close Button
	private final Button button = new Button();

	// Tab Title Label
	private final Label label = new Label();

	private TabPane parent;

	{
		HBox.setHgrow(label, Priority.ALWAYS);
	}

	public Tab()
	{
		this(null);
	}

	public Tab(String title)
	{
		this(title, new Pane());
	}

	// 추후 사이드 옵션도 구현해야됨
	public Tab(String title, Node content)
	{
		if (title != null)
		{
			setId(title);
			setTitle(title);
		}

		if (content != null)
		{
			setContent(content);
		}

		label.setMinWidth(DEFAULT_TITLE_SIZE);
		label.setAlignment(DEFAULT_TAB_ALIGNMENT);
		label.getStyleClass().add(TITLE_STYLE_CLASS);

		button.setGraphic(AllSVGIcons.get("Tab.Close"));
		button.setPrefWidth(DEFAULT_BUTTON_SIZE);
		button.getStyleClass().add(BUTTON_STYLE_CLASS);

		this.setOnMousePressed(event ->
		{
			parent.select(this);
		});

		button.setOnAction(event ->
		{
			parent.close(this);
		});

		pinned.addListener((observable, oldValue, newValue)->
		{
			closable.setValue(!newValue);
		});

		closable.addListener((observable, oldValue, newValue) ->
		{
			button.setVisible(newValue);
			button.setDisable(!newValue);
		});

		// setId(/* "@tab::" + */ title);
		setPadding(DEFAULT_TAB_PADDING);
		setSpacing(DEFAULT_TAB_SPACING);
		setMinWidth(DEFAULT_TAB_WIDTH);
		// setMinHeight(DEFAULT_TAB_HEIGHT);
		// setMaxWidth(Double.MAX_VALUE);
		// setPrefHeight(DEFAULT_TAB_HEIGHT);
		setAlignment(DEFAULT_TAB_ALIGNMENT);
		getChildren().addAll(label, button);
		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public String getText()
	{
		return label.getText();
	}

	public String getTitle()
	{
		return label.getText();
	}

	// Get Title Label
	public Label getLabel()
	{
		return label;
	}

	public HBox getHeader()
	{
		return this;
	}

	public Node getContent()
	{
		return content.get();
	}

	public TabPane getTabPane()
	{
		return parent;
	}

	public boolean isClosable()
	{
		return closable.get();
	}

	public boolean isPinned()
	{
		return pinned.get();
	}

	public boolean isSelected()
	{
		return parent.getSelectedTab().equals(this);
	}

	public void setText(String text)
	{
		label.setText(text);
	}

	public void setTitle(String title)
	{
		label.setText(title);
	}

	public void setPinned()
	{
		setPinned(!pinned.get());
	}

	public void setPinned(boolean pinned)
	{
		this.pinned.set(pinned);
	}

	public void setClosable(boolean closable)
	{
		this.closable.set(closable);
	}

	public void setContent(Node content)
	{
		this.content.set(content);
	}

	public void setTabPane(TabPane parent)
	{
		this.parent = parent;
	}
}