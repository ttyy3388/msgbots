package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.util.AllSVGIcons;

@DefaultProperty("content")
public class Tab extends HBox
{
	private static final String DEFAULT_STYLE_CLASS = "tab";

	private static final Pos DEFAULT_TAB_ALIGNMENT = Pos.CENTER;
	private static final int DEFAULT_TAB_SPACING = 10;
	private static final int DEFAULT_TAB_WIDTH = 80;
	private static final int DEFAULT_TAB_HEIGHT = 50;

	private static final int DEFAULT_LABEL_SIZE = 60;
	private static final int DEFAULT_BUTTON_SIZE = 10;

	private final ObjectProperty<Pos> align = new SimpleObjectProperty<>(Pos.CENTER);

	// Tab Closable Property
	private final BooleanProperty closable = new SimpleBooleanProperty(true);

	private final BooleanProperty pinned = new SimpleBooleanProperty(false);

	private final ObjectProperty<Node> content = new SimpleObjectProperty<>();

	// Title Label
	private final Label label = new Label();

	// Close Button
	private final Button button = new Button();

	// private final HBox header = new HBox();
	private final ContextMenu menu;

	// private final TabType type;

	private TabPane pane;

	{
		HBox.setHgrow(label, Priority.ALWAYS);
	}

	public enum Type
	{
		BUTTON, // Default

		TOGGLE // Option
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
		this.menu = new ContextMenu
		(
			new MenuItem("Close Tab", "Ctrl + W", closable, event -> pane.close(this)),
			new MenuItem("Close Other Tabs"),
			new MenuItem("Close Tabs to the Right"),
			new MenuItem("Close Tabs to the Left"),
			new MenuItem("Close All Tabs"),
			new SeparatorMenuItem(),
			new MenuItem("Pin Tab", event -> pinned.set(!pinned.get())),
			new SeparatorMenuItem(),
			new MenuItem("Select Next Tab", event -> pane.selectNextTab(this)),
			new MenuItem("Select Previous Tab", event -> pane.selectPreviousTab(this))
		);

		menu.setNode(this);

		if (title != null)
		{
			label.setText(title);
		}

		if (content != null)
		{
			this.content.set(content);
		}

		// content.setId("@content::" + title);

		button.setGraphic(AllSVGIcons.get("Tab.Close"));
		button.setPrefWidth(DEFAULT_BUTTON_SIZE);

		label.setMinWidth(DEFAULT_LABEL_SIZE);
		label.setAlignment(DEFAULT_TAB_ALIGNMENT);

		// Set Styles
		label.getStyleClass().add("tab-label");
		button.getStyleClass().add("tab-button");
		content.getStyleClass().add("tab-content");

		button.setOnAction(event ->
		{
			pane.close(this);
		});

		this.setOnMousePressed(event ->
		{
			pane.select(this);
		});

		align.addListener(change ->
		{
			label.setAlignment(align.get());
		});

		pinned.addListener(change->
		{
			closable.setValue(!pinned.get());

			/* List<Tab> tabs = pane.getTabs();

			tabs.remove(this);

			// find pinned tab
			for (int i = 0 ; i < tabs.size() ; i ++)
			{
				if (tabs.get(i).isPinned())
				{
					if (tabs.size() == i + 1)
					{
						tabs.add(i + 1, this);
						break;
					}
					else
					{
						continue ;
					}
				}
				else
				{
					tabs.add(i, this);
					break;
				}
			} */
		});

		closable.addListener((observable, oldValue, newValue) ->
		{
			button.setVisible(newValue);
			// button.setDisable(!newValue);
		});

		// setId("@tab::" + title);
		setPadding(new Insets(0, 10, 0, 30));
		setSpacing(DEFAULT_TAB_SPACING);
		setMinWidth(DEFAULT_TAB_WIDTH);
		// setPrefHeight(DEFAULT_TAB_HEIGHT);
		setAlignment(DEFAULT_TAB_ALIGNMENT);
		getChildren().addAll(label, button);
		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public void close()
	{
		pane.close(this);
	}

	public String getTitle()
	{
		return label.getText();
	}

	// Close Button
	public Button getButton()
	{
		return button;
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
        return pane;
    }

    public boolean isClosable()
	{
		return closable.get();
	}

	public boolean isPinned()
	{
		return pinned.get();
	}

	public Pos getAlign()
	{
		return align.get();
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

	public void setAlign(Pos pos)
	{
		this.align.set(pos);
	}

	public void setContent(Node content)
	{
		this.content.set(content);
	}

    public void setTabPane(TabPane pane)
    {
        this.pane = pane;
    }
}
