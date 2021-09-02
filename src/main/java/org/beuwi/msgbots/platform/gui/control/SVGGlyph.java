package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGGlyph extends Pane {
	// private final Paint color = SharedColors.getColor("icon-default");


	private final SVGPath shape = new SVGPath();

	public SVGGlyph() {
		this(null);
	}

	public SVGGlyph(String path) {
		this(path, 12, 12);
	}

	public SVGGlyph(String path, double width, double height) {
		pathProperty().addListener(change -> {
			String content = getPath();
			if (content != null && !content.isEmpty()) {
				shape.setContent(content);
			}
		});

		fillProperty().addListener(change -> {
			Paint fill = getFill();
			if (fill != null) {
				shape.setFill(fill);
			}
		});

		if (path != null) {
			setPath(path);
		}

		/* parentProperty().addListener(change -> {
			// 부모 이벤트를 받아서 아이콘 색 변경에 활용함
			Parent parent = getParent();
			if (parent != null) {
				if (parent instanceof ButtonBase) {
					parent.hoverProperty().addListener((observable, oldValue, newValue) -> {
						if (newValue) {
							setFill(SharedColors.getColor("icon-hover"));
						}
						else {
							setFill(color);
						}
					});

					parent.focusedProperty().addListener((observable, oldValue, newValue) -> {
						if (newValue) {
							setFill(SharedColors.getColor("icon-focused"));
						}
						else {
							// 저장된 색 적용
							setFill(color);
						}
					});
				}
			}
		}); */

		// setStyle("-fx-background-color: red;");
		// setFill(Color.BLACK); // Moved CSS
        setSize(width, height);
		setShape(shape);
        getStyleClass().add("icon");
	}

	public void setSize(double width, double height) {
		setPrefSize(width, height);
		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
	}

	private final ObjectProperty<Paint> fillProperty = new SimpleObjectProperty<>();
	public void setFill(Paint fill) {
		fillProperty.set(fill);
	}
	public Paint getFill() {
		return fillProperty.get();
	}
	public ObjectProperty<Paint> fillProperty() {
		return fillProperty;
	}

	private final StringProperty pathProperty = new SimpleStringProperty();
	public void setPath(String path) {
		pathProperty.set(path);
	}
	public String getPath() {
		return pathProperty.get();
	}
	public StringProperty pathProperty() {
		return pathProperty;
	}
}