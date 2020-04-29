package org.beuwi.simulator.platform.ui.components;

import javafx.scene.shape.SVGPath;

public class ISVGPath extends SVGPath
{
	final public static ISVGGlyph MINIMIZE = new ISVGGlyph("M3,8h10v1H3V8z", 10, 1);
	final public static ISVGGlyph MAXIMIZE = new ISVGGlyph("M3,3 L13,3 L13,13 L3,13 L3,3 Z M4,4 L4,12 L12,12 L12,4 L4,4 Z", 10, 10);
	final public static ISVGGlyph CLOSE    = new ISVGGlyph("M12.3,13L8,8.7L3.7,13L3,12.3L7.3,8L3,3.7L3.7,3L8,7.3L12.3,3L13,3.7L8.7,8l4.3,4.3L12.3,13z", 10, 10);

	final public static ISVGGlyph RELOAD = null;
	final public static ISVGGlyph REFRESH = null;

	final public static ISVGGlyph DEBUG = null;
	final public static ISVGGlyph EXPLORER = null;
	final public static ISVGGlyph SIMULATION = null;

	public ISVGPath(String path)
	{
		this.setContent(path);
	}
}