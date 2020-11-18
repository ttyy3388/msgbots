package org.beuwi.msgbots.platform.gui.control;

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

// @DefaultProperty("content")
public class Tab extends HBox
{
	private static final Insets DEFAULT_TAB_PADDING = new Insets(0, 10, 0, 30);

	private static final String DEFAULT_STYLE_CLASS = "tab";
	// private static final String BUTTON_STYLE_CLASS = "tab-button";
	// private static final String TITLE_STYLE_CLASS = "tab-label";

	private static final Pos DEFAULT_TAB_ALIGNMENT = Pos.CENTER;
	private static final int DEFAULT_TAB_SPACING = 10;
	private static final int DEFAULT_TAB_WIDTH = 80;

	private static final int DEFAULT_BUTTON_SIZE = 10;
	private static final int DEFAULT_TITLE_SIZE = 50;

	private final ObjectProperty<Node> content = new SimpleObjectProperty(null);
	private final BooleanProperty closable = new SimpleBooleanProperty(true);
	// private final ObjectProperty<Type> type = new SimpleObjectProperty(null);

	// Tab Close Button ( Action Button )
	private final Button button = new Button();

	// Tab Title Label
	private final Label label = new Label();

	private final ContextMenu menu;

	private TabView parent;

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
			setText(title);
		}

		if (content != null)
		{
			setContent(content);
		}

		menu = new ContextMenu
		(
			new Menu("Close", "Ctrl + W", event -> parent.close(this)),
			new Menu("Close Others"),
			new Menu("Close All Tabs", event -> parent.close())
		);

		menu.setNode(this);

		label.setMinWidth(DEFAULT_TITLE_SIZE);
		label.setAlignment(DEFAULT_TAB_ALIGNMENT);

		button.setGraphic(AllSVGIcons.get("Tab.Close"));
		button.setPrefWidth(DEFAULT_BUTTON_SIZE);

		this.setOnMousePressed(event ->
		{
			getView().select(this);
		});

		button.setOnAction(event ->
		{
			getView().close(this);
		});

		closable.addListener((observable, oldValue, newValue) ->
		{
			button.setVisible(newValue);
			button.setDisable(!newValue);
		});

		setItems(label, button);
		// setId(/* "@tab::" + */ title);
		setPadding(DEFAULT_TAB_PADDING);
		setSpacing(DEFAULT_TAB_SPACING);
		setMinWidth(DEFAULT_TAB_WIDTH);
		// setMinHeight(DEFAULT_TAB_HEIGHT);
		// setMaxWidth(Double.MAX_VALUE);
		// setPrefHeight(DEFAULT_TAB_HEIGHT);
		setAlignment(DEFAULT_TAB_ALIGNMENT);
		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void setView(TabView parent)
	{
		this.parent = parent;
	}

	public void setText(String text)
	{
		label.setText(text);
	}

	public void setClosable(boolean closable)
	{
		this.closable.set(closable);
	}

	public void setContent(Node content)
	{
		this.content.set(content);
	}

	public String getText()
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

	// Get Action Button
	public Button getButton()
	{
		return button;
	}

	public TabView getView()
	{
		return parent;
	}

	public boolean isClosable()
	{
		return closable.get();
	}

	public boolean isSelected()
	{
		return parent.getSelectedTab().equals(this);
	}

	public BooleanProperty getClosableProperty()
	{
		return closable;
	}
}