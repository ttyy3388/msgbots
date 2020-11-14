package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.platform.gui.enums.LogType;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.json.simple.JSONObject;

// Log Item
public class Log extends HBox
{
	private static final String DEFAULT_STYLE_CLASS = "log";
	private static final Insets DEFAULT_PADDING_INSETS = new Insets(15);
	private static final double DEFAULT_PREF_HEIGHT = 80;

	private final VBox<Label> content = new VBox();

	private final ImageView image = new ImageView();

    private final Label tlabel = new Label();
    private final Label dlabel = new Label();

	private final LogType type;

	{
		HBox.setHgrow(content, Priority.ALWAYS);
		VBox.setVgrow(tlabel, Priority.ALWAYS);
	}

	public Log(JSONObject object)
	{
		this(switch (String.valueOf(object.get("b")))
		{
			case "1" -> LogType.EVENT;
			case "2" -> LogType.DEBUG;
			case "3" -> LogType.ERROR;
			default  -> LogType.EVENT;
		},
		String.valueOf(object.get("a")),
		String.valueOf(object.get("c")));
	}

	public Log(LogType type, String text, String date)
	{
		this.type = type;

		image.setImage(ResourceUtils.getImage(type.toString()));
		image.setFitWidth(20);
		image.setFitHeight(20);

		tlabel.setText(text);
		dlabel.setText(date);

		tlabel.setWrapText(true);
		tlabel.setAlignment(Pos.TOP_LEFT);

		content.setItems(tlabel, dlabel);

		setItems(image, content);
		setSpacing(15);
		setPadding(DEFAULT_PADDING_INSETS);
		setFittable(true);
		setPrefHeight(DEFAULT_PREF_HEIGHT);
		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void setText(String text)
	{
		this.tlabel.setText(text);
	}

	public void setDate(String date)
	{
		this.dlabel.setText(date);
	}

	public String getText()
	{
		return tlabel.getText();
	}

	public String getDate()
	{
		return dlabel.getText();
	}

	public LogType getType()
	{
		return type;
	}
}
