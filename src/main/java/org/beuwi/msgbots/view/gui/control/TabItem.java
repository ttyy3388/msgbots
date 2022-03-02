package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.view.gui.control.base.TabItemBase;
import org.beuwi.msgbots.view.gui.layout.HBox;
import org.beuwi.msgbots.view.util.AllSVGIcons;
import org.beuwi.msgbots.view.util.StdActions;

import java.util.List;

public class TabItem extends TabItemBase {
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
		addChangeListener("text", change -> {
			label.setText(getText());
		});

		addChangeListener("closable", change -> {
			// button.setVisible(isClosable());
			// button.setDisable(!isClosable());
			Insets padding = this.getPadding();
			List<Node> children = header.getChildren();

			if (isClosable()) {
				// 이미 있을 경우를 체크해서 없는 경우에만 추가
				if (!children.contains(button)) {
					children.add(button);

					// 우측의 패딩을 제거
					setPadding(new Insets(
						padding.getTop(),
						0,
						padding.getBottom(),
						padding.getLeft())
					);
				}
			}
			else {
				// 있는 경우에만 제거
				if (children.contains(button)) {
					children.remove(button);

					// 좌측과 우측의 패딩을 맞춤
					setPadding(new Insets(
						padding.getTop(),
						padding.getLeft(),
						padding.getBottom(),
						padding.getLeft())
					);
				}
			}
		});

		addChangeListener("text", change -> {
			this.setId(getText());
		});

		if (title != null) {
			setId(title);
			setText(title);
		}
		if (content != null) {
			setContent(content);
		}

		setHeader(header);

		menu = new ContextMenu(
			StdActions.CLOSE_TAB.handler(event -> {
				getView().closeTab(this);
			}).toMenuItem().enable(getFXProperty("closable"))
		);
		menu.setNode(this);

		// label.setText(control.getText());
		label.setMinWidth(30);
		label.setAlignment(Pos.CENTER);
		label.addStyleClass("text-label");

		// header.setSpacing(10);
		// header.setPrefWidth(80);
		header.initChildren(label, button);
		header.addStyleClass("header");

		button.setOnAction(event -> {
			TabView view = (TabView) getView();
			// [getView]가 [null]일 경우는 없을 거 같지만 대비
			if (view != null) {
				view.closeTab(this);
			}
		});
		button.setGraphic(AllSVGIcons.get("Tab.Close"));
		button.setPrefWidth(30);
		button.getStyleClass().add("close-button");

		// 탭 헤더 관련 설정

		setPadding(new Insets(0, 0, 0, 30)); // Padding(30) | Label(50) | Button(30)
		// setMinWidth(80);
		// setPrefWidth(80);
		// setMaxWidth(80);
		setMinHeight(30);
		setPrefHeight(30);
		setMaxHeight(30);
		addStyleClass("tab-item");
	}

	// Action button
	public Button getButton() {
		return button;
	}

	public final StringProperty textProperty() {
		return label.textProperty();
	}
	public void setText(String text) {
		label.setText(text);
	}
	public String getText() {
		return label.getText();
	}
}