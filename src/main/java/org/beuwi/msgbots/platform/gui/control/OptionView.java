package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.layout.VBox;

import java.util.List;

public class OptionView extends VBox {
	private static final String DEFAULT_STYLE_CLASS = "option-view";

	private static final Insets DEFAULT_PADDING_VALUE = new Insets(0, 15, 0, 15);

	private static final int DEFAULT_SPACING_VALUE = 5;
	private static final int DEFAULT_HEADER_HEIGHT = 50;

	private static final int CONTENT_SPACING_VALUE = 30;

	private final ObservableList<Node> items = FXCollections.observableArrayList();

	private final Label titleLabel = new Label();

	// "Content"안에 "OptionItem"들이 들어감
	private final VBox contentArea = new VBox();

	{
		VBox.setVgrow(contentArea, Priority.ALWAYS);
	}

	public OptionView() {
		titleLabel.setMinHeight(DEFAULT_HEADER_HEIGHT);
		titleLabel.getStyleClass().add("h2");

		contentArea.getChildren().setAll(getChildren());
		contentArea.setSpacing(CONTENT_SPACING_VALUE);
		// contentArea.getStyleClass().add("content");

		titleProperty().addListener(change -> {
			String title = getTitle();
			List<Node> list = getChildren();
			// 텍스트가 없는 경우 목록에서 제거함(빈공간이 남기때문에 제거)
			if (title == null) {
				// 목록에 추가된 상태라면
				if (list.contains(titleLabel)) {
					list.remove(titleLabel);
				}
			}
			else {
				// 목록에 없다면 추가
				if (!list.contains(titleLabel)) {
					list.add(0, titleLabel);
				}
				titleLabel.setText(getTitle());
			}
		});

		getItems().addListener((ListChangeListener<Node>) change -> {
			contentArea.getChildren().setAll(getItems());
		});

		setPadding(DEFAULT_PADDING_VALUE);
		// setSpacing(DEFAULT_SPACING_VALUE);
		getChildren().setAll(
			// titleLabel, // 제목이 입력돼야 추가되도록 변경함
			contentArea
		);
		getStyleClass().addAll(DEFAULT_STYLE_CLASS);
	}

	private final StringProperty titleProperty = new SimpleStringProperty();
	public void setTitle(String title) {
		this.titleProperty.set(title);
	}
	public String getTitle() {
		return titleProperty.get();
	}
	public StringProperty titleProperty() {
		return titleProperty;
	}


	/* private final ObjectProperty<PrefType> typeProperty = new SimpleObjectProperty();
	public void setType(PrefType type) {
		typeProperty.set(type);
	}
	public PrefType getType() {
		return typeProperty.get();
	}
	public ObjectProperty<PrefType> typeProperty() {
		return typeProperty;
	} */

	public ObservableList<Node> getItems() {
		return items;
	}
}
