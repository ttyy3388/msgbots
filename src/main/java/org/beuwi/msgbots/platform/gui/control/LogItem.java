package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.enums.LogType;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.VBox;

import org.json.simple.JSONObject;

// Log Item
public class LogItem extends VBox<Label> {
	private static final String DEFAULT_STYLE_CLASS = "log-item";

	private static final Insets DEFAULT_PADDING_INSETS = new Insets(10);
	private static final double DEFAULT_SPACING_VALUE = 10;

	private static final double DEFAULT_PREF_WIDTH = 200;
	private static final double DEFAULT_MIN_HEIGHT = 80;
	private static final double DEFAULT_PREF_HEIGHT = 80;

	/* private final ObjectProperty<LogType> type = new SimpleObjectProperty();
	private final StringProperty data = new SimpleStringProperty();
	private final StringProperty date = new SimpleStringProperty(); */

	// Data Label
	private final Label headerLabel = new Label();

	// Date Label
	private final Label footerLabel = new Label();

	{
		VBox.setVgrow(headerLabel, Priority.ALWAYS);
	}

	private LogView parent;

	public LogItem(JSONObject object) {
		this(
			String.valueOf(object.get("type")),
			String.valueOf(object.get("data")),
			String.valueOf(object.get("date"))
		);
	}

	public LogItem(String type, String data, String date) {
		this(LogType.convert(type), data, date);
	}

	public LogItem(LogType type, String data, String date) {
		/* colorBar.setColor(
			switch (type) {
				// case EVENT -> "#186DE6";
				case EVENT -> "#007ACC";
				case ERROR -> "#FF3333";
				case DEBUG -> "#00FF00";
			}
		); */
		getStyleClass().add(type.toString());

		headerLabel.setText(data);
		headerLabel.setWrapText(true);
		headerLabel.setAlignment(Pos.TOP_LEFT);

		footerLabel.setText(date);

		// content.setFittable(true);
		setSpacing(DEFAULT_SPACING_VALUE);
		setPadding(DEFAULT_PADDING_INSETS);
		getChildren().setAll(
			headerLabel, footerLabel
		);

		// setFittable(true);
		setPrefWidth(DEFAULT_PREF_WIDTH);
		// setMinHeight(DEFAULT_MIN_HEIGHT);
		setPrefHeight(DEFAULT_PREF_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	/* public void setType(LogType type) {
		this.type.set(type);
	}

	public void setText(String data) {
		this.data.set(data);
	}

	public void setDate(String time) {
		this.date.set(time);
	} */

	public void setView(LogView parent) {
		this.parent = parent;
	}

	public LogView getView() {
		return parent;
	}

	/* public String getText() {
		return data.get();
	}

	public String getDate() {
		return date.get();
	}

	public LogType getType() {
		return type.get();
	}

	public ObjectProperty<LogType> typeProperty() {
		return type;
	}

	public StringProperty textProperty() {
		return data;
	}

	public StringProperty dateProperty() {
		return date;
	} */
}