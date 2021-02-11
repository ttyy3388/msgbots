package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.layout.VBox;

public class OptionView extends VBox {
	private static final String DEFAULT_STYLE_CLASS = "pref-view";

	private static final int DEFAULT_SPACING_VALUE = 10;
	private static final int DEFAULT_HEADER_HEIGHT = 50;

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
		contentArea.setSpacing(30.0);
		// contentArea.getStyleClass().add("content");

		titleProperty().addListener(change -> {
			titleLabel.setText(getTitle());
		});
		getItems().addListener((ListChangeListener<Node>) change -> {
			contentArea.getChildren().setAll(getItems());
		});

		setSpacing(DEFAULT_SPACING_VALUE);
		getChildren().setAll(
			titleLabel,
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
