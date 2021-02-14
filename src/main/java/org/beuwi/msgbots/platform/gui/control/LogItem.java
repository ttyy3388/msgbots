package org.beuwi.msgbots.platform.gui.control;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.enums.LogType;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.json.simple.JSONObject;

// Log Item
public class LogItem extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "log-item";

	// private static final Insets DEFAULT_PADDING_INSETS = new Insets(5);
	// private static final double DEFAULT_SPACING_VALUE = 10;

	private static final double DEFAULT_PREF_WIDTH = 200;
	private static final double DEFAULT_MIN_HEIGHT = 80;
	private static final double DEFAULT_PREF_HEIGHT = 80;

	/* private final ObjectProperty<LogType> type = new SimpleObjectProperty();
	private final StringProperty data = new SimpleStringProperty();
	private final StringProperty date = new SimpleStringProperty(); */

	@FXML private HBox hbxBoxRoot;
	@FXML private ImageView imvBoxIcon;
	@FXML private Label lblBoxText;
	@FXML private Label lblBoxDate;

	private final FormLoader loader;
	private final ContextMenu menu;

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
		loader = new FormLoader("frame", "log-item-frame", this);
		menu = new ContextMenu(
				new MenuItem("Copy Text", event -> CopyStringAction.execute("[" + date + "] " + data))
		);
		menu.setNode(this);

		imvBoxIcon.setImage(ResourceUtils.getImage(type.toString()));

		lblBoxText.setText(data);
		lblBoxText.setWrapText(true);
		lblBoxText.setAlignment(Pos.TOP_LEFT);

		lblBoxDate.setText(date);

		getChildren().setAll(hbxBoxRoot);
		getStyleClass().add(type.toString());

		// setFittable(true);
		// setSpacing(DEFAULT_SPACING_VALUE);
		// setPadding(DEFAULT_PADDING_INSETS);
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