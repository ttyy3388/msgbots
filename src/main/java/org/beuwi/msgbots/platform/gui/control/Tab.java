package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.platform.util.AllSVGIcons;

// @DefaultProperty("content")
public class Tab extends HBox {
	private static final String DEFAULT_STYLE_CLASS = "tab";

	private static final Insets DEFAULT_TAB_PADDING = new Insets(0, 10, 0, 30);

	private static final Pos DEFAULT_TAB_ALIGNMENT = Pos.CENTER;
	private static final int DEFAULT_TAB_SPACING = 10;
	private static final int DEFAULT_TAB_WIDTH = 80;

	private static final int DEFAULT_BUTTON_SIZE = 10;
	private static final int DEFAULT_TITLE_SIZE = 50;

	private final ObjectProperty<Node> content = new SimpleObjectProperty(null);
	private final BooleanProperty closable = new SimpleBooleanProperty(true);
	// private final ObjectProperty<Type> type = new SimpleObjectProperty(null);
	private final StringProperty text = new SimpleStringProperty();

	// Tab Close Button ( Action Button )
	private final Button button = new Button();
	// Tab Title Label
	private final Label label = new Label();
	private final HBox root = new HBox();

	private final ContextMenu menu;

	private TabView parent;

	public Tab() {
		this(null);
	}

	public Tab(String title) {
		this(title, new Pane());
	}

	public Tab(String title, Node content) {
		textProperty().addListener(change -> {
			label.setText(getText());
		});
		closableProperty().addListener(change -> {
			button.setVisible(isClosable());
			// button.setDisable(!isClosable());
		});
		focusedProperty().addListener(change -> {
		});

		if (title != null) {
			setId(title);
			setText(title);
		}

		if (content != null) {
			setContent(content);
		}

		menu = new ContextMenu(
			new Menu("Close Tab", "Ctrl + W", event -> parent.closeTab(this)) /* .enable(closableProperty()) */,
			new Menu("Close Other Tabs", event -> parent.closeOtherTabs(this)) /* .enable(closableProperty()) */,
			new Menu("Close All Tabs", event -> parent.closeAllTabs()) /* .enable(closableProperty()) */,
			new Separator(),
			new Menu("Select Next Tab", "Ctrl + Tab", event -> parent.selectNextTab(this)),
			new Menu("Select Previous Tab", "Ctrl + Shift + Tab", event ->  parent.selectPrevTab(this))
		);

		menu.setNode(this);

		label.setMinWidth(DEFAULT_TITLE_SIZE);
		label.setAlignment(DEFAULT_TAB_ALIGNMENT);

		button.setGraphic(AllSVGIcons.get("Tab.Close"));
		button.setOnAction(event -> {
			// 탭 뷰가 지정돼지 않았을 경우가 없을 거 같긴 하지만 막아둠
			if (getView() != null) {
				getView().selectTab(this);
			}
		});
		button.setPrefWidth(DEFAULT_BUTTON_SIZE);

		addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			// 탭 뷰가 지정돼지 않았을 경우가 없을 거 같긴 하지만 막아둠
			if (getView() != null) {
				getView().selectTab(this);
			}
		});

		// setId(/* "@tab::" + */ title);
		setPadding(DEFAULT_TAB_PADDING);
		setSpacing(DEFAULT_TAB_SPACING);
		setMinWidth(DEFAULT_TAB_WIDTH);
		// setMinHeight(DEFAULT_TAB_HEIGHT);
		// setMaxWidth(Double.MAX_VALUE);
		// setPrefHeight(DEFAULT_TAB_HEIGHT);
		setAlignment(DEFAULT_TAB_ALIGNMENT);
		getItems().setAll(label, button);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setView(TabView parent) {
		this.parent = parent;
	}

	public void setText(String text) {
		textProperty().set(text);
	}

	public void setClosable(boolean closable) {
		closableProperty().set(closable);
	}

	public void setContent(Node content) {
		contentProperty().set(content);
	}

	public TabView getView() {
		return parent;
	}

	public String getText() {
		return textProperty().get();
	}

	public Node getContent() {
		return contentProperty().get();
	}

	public boolean isClosable() {
		return closableProperty().get();
	}

	public boolean isSelected() {
		return parent.getSelectedTab().equals(this);
	}

	public StringProperty textProperty() {
		return text;
	}

	public ObjectProperty<Node> contentProperty() {
		return content;
	}

	public BooleanProperty closableProperty() {
		return closable;
	}
}