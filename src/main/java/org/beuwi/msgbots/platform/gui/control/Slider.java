package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.layout.HBox;

public class Slider extends HBox {
	private final javafx.scene.control.Slider slider = new javafx.scene.control.Slider();
	private final Label label = new Label();

	{
		HBox.setHgrow(slider, Priority.ALWAYS);
	}

	public Slider() {
		slider.setSnapToTicks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setBlockIncrement(1);
		slider.setShowTickMarks(false);
		slider.setShowTickLabels(false);

		setSpacing(10);
		// setText(Math.floor(getValue()));
		valueProperty().addListener(change -> {
			setValue(Math.round(getValue()));
		});

		getChildren().setAll(slider, label);
		// getStyleClass().setAll("slider");
	}

	public void setMin(double value) {
		slider.setMin(value);
	}

	public void setMax(double value) {
		slider.setMax(value);
	}

	public void setValue(int value) {
		slider.setValue(value);
		label.setText(value);
	}

	public double getMin() {
		return slider.getMin();
	}

	public double getMax() {
		return slider.getMax();
	}

	public int getValue() {
		return (int) slider.getValue();
	}

	public DoubleProperty valueProperty() {
		return slider.valueProperty();
	}
}