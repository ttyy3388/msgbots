package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.layout.Priority;

import org.beuwi.msgbots.view.gui.layout.VBox;

import java.util.List;

public class ConfigView extends VBox {
	// private static final int DEFAULT_SPACING_VALUE = 5;
	// private static final int DEFAULT_HEADER_HEIGHT = 50;

	private final ObservableList<ConfigItem> items = FXCollections.observableArrayList();

	private final Label lblTitle = new Label();

	// "Content"안에 "OptionItem"들이 들어감
	private final VBox vbxContent = new VBox();

	{
		VBox.setVgrow(vbxContent, Priority.ALWAYS);
	}

	public ConfigView() {
		lblTitle.setMinHeight(50);
		lblTitle.addStyleClass("h2");
		lblTitle.addStyleClass("bold");

		vbxContent.setSpacing(30);
		vbxContent.initChildren(getChildren());
		// vbxContent.addStyleClass("content");

		addChangeListener("title", change -> {
			String title = getTitle();
			List<Node> list = getChildren();
			// 텍스트가 없는 경우 목록에서 제거함(빈공간이 남기때문에 제거)
			if (title == null) {
				// 목록에 추가된 상태라면
				if (list.contains(lblTitle)) {
					list.remove(lblTitle);
				}
			}
			else {
				// 목록에 없다면 추가
				if (!list.contains(lblTitle)) {
					list.add(0, lblTitle);
				}
				lblTitle.setText(getTitle());
			}
		});

		getItems().addListener((ListChangeListener<Node>) change -> {
			vbxContent.initChildren(getItems());
		});

		setPadding(new Insets(0, 15, 0, 15));
		// setSpacing(DEFAULT_SPACING_VALUE);
		initChildren(
			// lblTitle, // 제목이 입력돼야 추가되도록 변경함
			vbxContent
		);
		addStyleClass("config-view");
	}

	private final StringProperty titleProperty = new SimpleStringProperty();
	public final StringProperty titleProperty() {
		return titleProperty;
	}
	public void setTitle(String title) {
		this.titleProperty.set(title);
	}
	public String getTitle() {
		return titleProperty.get();
	}

	/* private final ObjectProperty<PrefType> typeProperty = new SimpleObjectProperty();
	public final ObjectProperty<PrefType> typeProperty() {
		return typeProperty;
	}
	public void setType(PrefType type) {
		typeProperty.set(type);
	}
	public PrefType getType() {
		return typeProperty.get();
	} */

	public ObservableList<ConfigItem> getItems() {
		return items;
	}
}
