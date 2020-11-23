package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.scene.control.Skin;
import org.beuwi.msgbots.platform.gui.enums.LogType;

import org.beuwi.msgbots.platform.gui.skins.LogBoxSkin;
import org.json.simple.JSONObject;

// Log Item
public class Log extends Control
{
	private static final String DEFAULT_STYLE_CLASS = "log";

	private final ObjectProperty<LogType> type = new SimpleObjectProperty();
	private final StringProperty data = new SimpleStringProperty();
	private final StringProperty date = new SimpleStringProperty();

	public Log(JSONObject object)
	{
		this
		(
			String.valueOf(object.get("type")),
			String.valueOf(object.get("data")),
			String.valueOf(object.get("date"))
		);
	}

	public Log(String type, String data, String date)
	{
		this(LogType.convert(type), data, date);
	}

	public Log(LogType type, String data, String date)
	{
		setType(type);
		setText(data);
		setDate(date);

		addStyleClass("log");
	}

	public void setType(LogType type)
	{
		this.type.set(type);
	}

	public void setText(String data)
	{
		this.data.set(data);
	}

	public void setDate(String time)
	{
		this.date.set(time);
	}

	public String getText()
	{
		return data.get();
	}

	public String getDate()
	{
		return date.get();
	}

	public LogType getType()
	{
		return type.get();
	}

	@Override
	public Skin<?> setDefaultSkin()
	{
		return new LogBoxSkin(this);
	}

	/* public ObjectProperty<LogType> getTypeProperty()
	{
		return type;
	}

	public StringProperty getTextProperty()
	{
		return data;
	}

	public StringProperty getDateProperty()
	{
		return date;
	} */
}