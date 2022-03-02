package org.beuwi.msgbots.view.gui.control;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.base.type.LogType;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.gui.layout.HBox;
import org.beuwi.msgbots.utils.ResourceUtils;
import org.json.simple.JSONObject;

public class LogItem extends HBox {
	// private static final int DEFAULT_WIDTH = 250;
	private static final int DEFAULT_HEIGHT = 30;

	@FXML private ImageView imvIcon;
	@FXML private Label lblDate;
	// @FXML private Label lblTitle;
	@FXML private Label lblText;

	private final FormLoader loader;
	// private final ContextMenu menu;

	private LogView parent;

	// JSONOjbect, JObejct 호환
	public <JSON extends JSONObject> LogItem(JSON object) {
		this(
			String.valueOf(object.get("type")),
			String.valueOf(object.get("date")),
			String.valueOf(object.get("data"))
		);
	}

	public LogItem(String type, String date, String data) {
	    this(LogType.convert(type), date, data);
    }

	public LogItem(LogType type, String date, String data) {
		loader = new FormLoader();
		loader.setName("log-item-frame");
		loader.setRoot(this);
		loader.setController(this);
		loader.load();

		lblDate.setText(date);
		// lblDate.setWrapText(true);

		// lblTitle.setText(title);
		// lblTitle.setWrapText(true);
		lblText.setText(data);
		// lblText.setWrapText(true);

		String string = type.toString();

		imvIcon.setImage(ResourceUtils.getImage(string));

		setMinHeight(DEFAULT_HEIGHT);
		setPseudoClass(string, true);
		addStyleClass("log-item");
	}

	public void setView(LogView parent) {
		this.parent = parent;
	}

	public LogView getView() {
		return parent;
	}
}