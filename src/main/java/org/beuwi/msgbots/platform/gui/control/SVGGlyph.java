package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.setting.GlobalSettings;

public class SVGGlyph extends com.jfoenix.svg.SVGGlyph {
	private static final String DEFAULT_STYLE_CLASS = "icon";

	// private static final Color DEFAULT_HOVER_COLOR;

	private Color fill;

	public SVGGlyph() {
		this(null);
	}

	public SVGGlyph(String path) {
		this(path, Color.valueOf("#CCCCCC"));
	}

	public SVGGlyph(String path, Color fill) {
		this(path, fill, 12, 12);
	}

	public SVGGlyph(String path, double width, double height) {
		this(path, Color.valueOf("#CCCCCC"), width, height);
	}

	public SVGGlyph(String path, Color fill, double width, double height) {
		super(path);

		this.fill = fill;

		parentProperty().addListener(change -> {
			if (getParent() != null) {
				applyStyles(getParent());
			}
		});

        setFill(fill);
        setSize(width, height);

        getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	// Parent
	public void applyStyles(Node node) {
		// Default Color
		final ThemeType theme = ThemeType.parse(GlobalSettings.getString("program:color_theme"));
		final Color color = theme.equals(ThemeType.DARK) ? Color.WHITE : Color.BLACK;

		if (node instanceof Button) {
			node.hoverProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					setFill(color);
				}
				else {
					setFill(fill);
				}
			});

			node.focusedProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue) {
					setFill(color);
				}
				else {
					setFill(fill);
				}
			});
		}
	}

	public SVGGlyph setFill(String color) {
		setFill(Color.valueOf(color));
		return this;
	}

	public SVGGlyph setSize(int width, int height) {
		setSize(Double.valueOf(width), Double.valueOf(height));
		return this;
	}
}