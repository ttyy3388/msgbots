package org.beuwi.msgbots.platform.gui.skins;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.control.ColorBar;
import org.beuwi.msgbots.platform.gui.control.ColorBox;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.Log;
import org.beuwi.msgbots.platform.gui.control.SkinBase;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class LogBoxSkin extends SkinBase<Log>
{
	private static final Insets DEFAULT_PADDING_INSETS = new Insets(5);
    private static final double DEFAULT_PREF_HEIGHT = 50;
    private static final double DEFAULT_SPACING_VALUE = 5;

	private final ColorBox root = new ColorBox();
	private final VBox<Label> content = new VBox();

	private final ColorBar bar = root.getColorBar();

	private final Label data = new Label();
	private final Label date = new Label();

	{
		VBox.setVgrow(data, Priority.ALWAYS);
	}

	public LogBoxSkin(Log control)
	{
		super(control);

		bar.setColor
		(
			switch (control.getType())
			{
				case EVENT -> "#186DE6";
				case ERROR -> "#FF0000";
				case DEBUG -> "#00FF00";
			}
		);

		data.setText(control.getText());
		date.setText(control.getDate());

		data.setWrapText(true);
		data.setAlignment(Pos.TOP_LEFT);

		root.setContent(content);
		// root.setFittable(true);
		root.setPrefHeight(DEFAULT_PREF_HEIGHT);

		content.setItems(data, date);
		// content.setFittable(true);
		content.setSpacing(DEFAULT_SPACING_VALUE);
		content.setPadding(DEFAULT_PADDING_INSETS);

		this.setItem(root);
	}
}
