package org.beuwi.msgbots.program.gui.control;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.program.app.view.actions.OpenTabToDialogAction;
import org.beuwi.msgbots.program.gui.control.base.TabItemBase;
import org.beuwi.msgbots.program.gui.layout.HBox;
import org.beuwi.msgbots.program.utils.AllSVGIcons;
import org.beuwi.msgbots.program.utils.GlobalActions;

import java.util.List;

public class TabItem extends TabItemBase<TabView> {
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
		textProperty().addListener(change -> {
			label.setText(getText());
		});
		closableProperty().addListener(change -> {
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
		textProperty().addListener(change -> {
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
			GlobalActions.CLOSE_TAB.custom(event -> {
				getView().closeTab(this);
			}).toMenu().enable(closableProperty()),
			GlobalActions.CLOSE_OTHER_TABS.custom(event -> {
				getView().closeOtherTabs(this);
			}).toMenu(),
			GlobalActions.CLOSE_ALL_TABS.custom(event -> {
				getView().closeAllTabs();
			}).toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.OPEN_TAB_TO_DIALOG.custom(event -> {
				OpenTabToDialogAction.getInstance().execute(this);
			}).toMenu()
		);
		menu.setNode(this);

		// label.setText(control.getText());
		label.setMinWidth(30);
		label.setAlignment(Pos.CENTER);
		label.getStyleClass().add("text-label");

		// header.setSpacing(10);
		// header.setPrefWidth(80);
		header.getChildren().setAll(label, button);
		header.getStyleClass().add("header");

		button.setOnAction(event -> {
			// [getView]가 [null]일 경우는 없을 거 같지만 대비
			if (getView() != null) {
				getView().closeTab(this);
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
		getStyleClass().add("tab-item");
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
}
