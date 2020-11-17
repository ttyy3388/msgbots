package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Priority;

public class Slider extends HBox
{
	private final javafx.scene.control.Slider slider = new javafx.scene.control.Slider();
	private final Label label = new Label();

	{
		HBox.setHgrow(slider, Priority.ALWAYS);
	}

	public Slider()
	{
		slider.setSnapToTicks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setBlockIncrement(1);
		slider.setShowTickMarks(false);
		slider.setShowTickLabels(false);

		// setText(Math.floor(getValue()));
		getValueProperty().addListener(change ->
		{
			setValue(Math.round(getValue()));
		});

		setItems(slider, label);
		setSpacing(10);
		setStyleClass("slider");
	}

	public void setMin(double value)
	{
		slider.setMin(value);
	}

	public void setMax(double value)
	{
		slider.setMax(value);
	}

	public void setValue(int value)
	{
		slider.setValue(value);
		label.setText(value);
	}

	public double getMin()
	{
		return slider.getMin();
	}

	public double getMax()
	{
		return slider.getMax();
	}

	public int getValue()
	{
		return (int) slider.getValue();
	}

	public DoubleProperty getValueProperty()
	{
		return slider.valueProperty();
	}
}