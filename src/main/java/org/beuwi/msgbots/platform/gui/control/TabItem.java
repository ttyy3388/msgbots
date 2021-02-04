package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.control.base.TabItemBase;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.util.AllSVGIcons;

public class TabItem extends TabItemBase {
	private static final String DEFAULT_STYLE_CLASS = "tab-item";

	// Padding(30) | Label(50) | Space(10) | Button(10)
	private static final Insets DEFAULT_ITEM_PADDING = new Insets(0, 0, 0, 30);
	// private static final int DEFAULT_ITEM_WIDTH = 80;
	private static final int DEFAULT_ITEM_HEIGHT = 30;

	// private static final String HEADER_STYLE_CLASS = "tab-header";
	private static final String LABEL_STYLE_CLASS = "tab-label";
	private static final String BUTTON_STYLE_CLASS = "tab-button";

	private static final Pos DEFAULT_HEADER_ALIGNMENT = Pos.CENTER;
	private static final int DEFAULT_HEADER_SPACING = 10;

	private static final int DEFAULT_BUTTON_SIZE = 30;
	private static final int DEFAULT_TITLE_SIZE = 50;

	private final HBox header = new HBox();
	private final Label label = new Label();

	// Action Button ( Default : Close )
	private final Button button = new Button();

	private final ContextMenu menu;

	{
		HBox.setHgrow(label, Priority.ALWAYS);
	}

	public TabItem() {
		this(null);
	}
	public TabItem(String title) {
		this(title, new Pane());
	}
	public TabItem(String title, Node content) {
		if (title != null) {
			setId(title);
			setText(title);
		}

		if (content != null) {
			setContent(content);
		}
		setHeader(header);

		textProperty().addListener(change -> {
			label.setText(getText());
		});
		closableProperty().addListener(change -> {
			button.setVisible(isClosable());
			button.setDisable(!isClosable());
		});
		/* prefWidthProperty().addListener(change -> {
			header.setPrefWidth(getPrefWidth());
		}); */

		menu = new ContextMenu(
			new MenuItem("Close", "Ctrl + W", event -> getView().closeTab(this)),
			new MenuItem("Close Others", event -> getView().closeOtherTabs(this)),
			new MenuItem("Close All Tabs", event -> getView().closeAllTabs()),
			new Separator(),
			new MenuItem("Select Next Tab", "Ctrl + Tab", event -> getView().selectNextTab(this)),
			new MenuItem("Select Previous Tab", "Ctrl + Shift + Tab", event ->  getView().selectPrevTab(this))
		);
		menu.setNode(this);

		// label.setText(control.getText());
		label.setMinWidth(DEFAULT_TITLE_SIZE);
		label.setAlignment(DEFAULT_HEADER_ALIGNMENT);
		label.getStyleClass().add(LABEL_STYLE_CLASS);

		// header.setSpacing(DEFAULT_HEADER_SPACING);
		// header.setPrefWidth(DEFAULT_HEADER_WIDTH);
		header.getChildren().setAll(label, button);
		// header.getStyleClass().add(HEADER_STYLE_CLASS);

		// Default action : close
		button.setOnAction(event -> {
			if (getView() != null) {
				getView().closeTab(this);
			}
		});
		button.setGraphic(AllSVGIcons.get("Tab.Close"));
		button.setPrefWidth(DEFAULT_BUTTON_SIZE);
		button.getStyleClass().add(BUTTON_STYLE_CLASS);

		addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			// 좌측 마우스 클릭 시
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				if (getView() != null) {
					getView().selectTab(this);
				}
			}
		});

		setPadding(DEFAULT_ITEM_PADDING);
		// setMinWidth(DEFAULT_ITEM_WIDTH); // 너비 최소 지정
		// setPrefWidth(DEFAULT_ITEM_WIDTH);
		// setMaxWidth(DEFAULT_ITEM_WIDTH);
		setMinHeight(DEFAULT_ITEM_HEIGHT); // 높이 강제 지정
		setPrefHeight(DEFAULT_ITEM_HEIGHT); // 높이 강제 지정
		setMaxHeight(DEFAULT_ITEM_HEIGHT); // 높이 강제 지정
	}

	// Action button
	public Button getButton() {
		return button;
	}

	public void setText(String text) {
		label.setText(text);
	}
	public String getText() {
		return label.getText();
	}
	public StringProperty textProperty() {
		return label.textProperty();
	}

	private final BooleanProperty closableProperty = new SimpleBooleanProperty(true);
	public void setClosable(boolean closable) {
		this.closableProperty.set(closable);
	}
	public boolean isClosable() {
		return closableProperty.get();
	}
	public BooleanProperty closableProperty() {
		return closableProperty;
	}
}
