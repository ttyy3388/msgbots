package org.beuwi.simulator.platform.ui.components;

import com.jfoenix.svg.SVGGlyph;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ISVGGlyph extends SVGGlyph
{
	{
		this.getStyleClass().add("glyph");
	}

	public ISVGGlyph()
	{
		this(null);
	}

	public ISVGGlyph(String path)
	{
		this(path, Color.valueOf("#AAAAAA"));
	}

	public ISVGGlyph(String path, Paint fill)
	{
		this(path, fill, 12, 12);
	}

	public ISVGGlyph(String path, int width, int height)
	{
		this(path, Paint.valueOf("AAAAAA"), width, height);
	}

	public ISVGGlyph(String path, Paint fill, int width, int height)
	{
		super(path);

		this.setFill(fill);
		this.setSize(width, height);
	}

	public ISVGGlyph setFill(String color)
	{
		this.setFill(Color.valueOf(color));

		return this;
	}

	public ISVGGlyph setSize(int width, int height)
	{
		this.setSize(Double.valueOf(width), Double.valueOf(height));

		return this;
	}

	public ISVGGlyph setTranslateX(int translateX)
	{
		this.setTranslateX(Double.valueOf(translateX));

		return this;
	}

	public ISVGGlyph setTranslateY(int translateY)
	{
		this.setTranslateY(Double.valueOf(translateY));

		return this;
	}

	public ISVGGlyph setTranslateZ(int translateZ)
	{
		this.setTranslateZ(Double.valueOf(translateZ));

		return this;
	}
}