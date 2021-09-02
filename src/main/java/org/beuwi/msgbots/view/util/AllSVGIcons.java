package org.beuwi.msgbots.view.util;

import org.beuwi.msgbots.view.gui.control.SVGGlyph;

import java.util.HashMap;

// SVG
public class AllSVGIcons {
	// private static final Color color = SharedColors.getColor("icon-default");
	
	public static SVGGlyph get(String key) {
		return new SVGGlyphs().get(key);
	}
	public static SVGGlyph get(String key, double width, double height) {
		SVGGlyph glyph =  new SVGGlyphs().get(key);
		glyph.setSize(width, height);
		return glyph;
	}

	private final static class SVGGlyphs extends HashMap<String, SVGGlyph> {{
		put("Window.Minimize", new SVGGlyph("M 0 0 L 1 0 L 1 1 L 0 1 Z", 10, 1));
		put("Window.Maximize", new SVGGlyph("M 0 0 L 60 0 L 60 60 L 0 60 L 5 55 L 55 55 L 55 5 L 5 5 L 5 55 L 0 60 Z", 10, 10));
		put("Window.Restore", new SVGGlyph("M 0 60 L 0 12 L 12 12 L 12 0 L 60 0 L 60 48 L 48 48 L 48 42 L 54 42 L 54 6 L 18 6 L 18 12 L 48 12 L 48 60 L 0 60 L 6 54 L 42 54 L 42 18 L 6 18 L 6 54 Z", 10, 10));
		put("Window.Close", new SVGGlyph("M 0 3 L 3 0 L 30 27 L 57 0 L 60 3 L 33 30 L 60 57 L 57 60 L 30 33 L 3 60 L 0 57 L 27 30 Z", 10, 10));

		// put("Box.Close", new SVGGlyph("M357,35.7 321.3,0 178.5,142.8 35.7,0 0,35.7 142.8,178.5 0,321.3 35.7,357 178.5,214.2 321.3,357 357,321.3 214.2,178.5", 10, 10));
		put("Box.Close", new SVGGlyph("M 0 3 L 3 0 L 30 27 L 57 0 L 60 3 L 33 30 L 60 57 L 57 60 L 30 33 L 3 60 L 0 57 L 27 30 Z", 10, 10));

		put("Tab.Close", new SVGGlyph("M12.3,13L8,8.7L3.7,13L3,12.3L7.3,8L3,3.7L3.7,3L8,7.3L12.3,3L13,3.7L8.7,8l4.3,4.3L12.3,13z", 8, 8));
		put("Tab.More", new SVGGlyph("M161.11 568.289c-15.596-15.595-23.393-34.359-23.393-56.289s7.797-40.694 23.392-56.289c15.596-15.595 34.358-23.393 56.29-23.393s40.693 7.798 56.288 23.393C289.283 471.306 297.08 490.07 297.08 512s-7.797 40.694-23.393 56.289c-15.595 15.595-34.358 23.393-56.289 23.393s-40.693-7.799-56.289-23.393z m294.601 0c-15.595-15.595-23.393-34.359-23.393-56.289s7.798-40.694 23.393-56.289c15.595-15.595 34.359-23.393 56.289-23.393s40.694 7.798 56.289 23.393c15.595 15.595 23.393 34.359 23.393 56.289s-7.798 40.694-23.393 56.289c-15.595 15.595-34.359 23.393-56.289 23.393s-40.694-7.799-56.289-23.393z m294.602 0C734.717 552.694 726.92 533.93 726.92 512s7.797-40.694 23.393-56.289c15.595-15.595 34.358-23.393 56.289-23.393s40.693 7.798 56.289 23.393c15.595 15.595 23.392 34.359 23.392 56.289s-7.797 40.694-23.392 56.289c-15.596 15.595-34.359 23.393-56.29 23.393s-40.693-7.799-56.288-23.393z", 12, 2));

		put("Editor.Split", new SVGGlyph("M28.4,0H1.6C0.7,0,0,0.7,0,1.6v26.9C0,29.3,0.7,30,1.6,30h26.9c0.9,0,1.6-0.7,1.6-1.6V1.6C30,0.7,29.3,0,28.4,0z M2.8,28.3c-0.6,0-1-0.4-1-1V2.8c0-0.6,0.4-1,1-1H14v26.5H2.8z M28.3,27.3c0,0.6-0.4,1-1,1H16V1.8h11.3c0.6,0,1,0.4,1,1V27.3z", 13, 13));
		put("Editor.More", new SVGGlyph("M2,0C0.9,0,0,0.9,0,2s0.9,2,2,2s2-0.9,2-2S3.1,0,2,0z M14,0c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S15.1,0,14,0z M8,0 C6.9,0,6,0.9,6,2s0.9,2,2,2s2-0.9,2-2S9.1,0,8,0z", 13, 2));

		// put("Button.More", new SVGGlyph("M2,0C0.9,0,0,0.9,0,2s0.9,2,2,2s2-0.9,2-2S3.1,0,2,0z M14,0c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S15.1,0,14,0z M8,0 C6.9,0,6,0.9,6,2s0.9,2,2,2s2-0.9,2-2S9.1,0,8,0z", 13, 2));

		put("CheckBox.Mark", new SVGGlyph());

		put("Bot.Power", new SVGGlyph("M10.269,11.298V1.883C10.269,0.844,11.113,0,12.152,0s1.883,0.844,1.883,1.883v9.415c0,1.039-0.844,1.883-1.883,1.883S10.269,12.337,10.269,11.298z M19.616,2.761c-0.61-0.483-1.5-0.377-1.983,0.234c-0.483,0.612-0.378,1.5,0.234,1.984c2.24,1.767,3.524,4.413,3.524,7.261c0,5.094-4.145,9.239-9.238,9.239c-5.094,0-9.239-4.145-9.239-9.239c0-2.847,1.283-5.492,3.521-7.258c0.612-0.483,0.717-1.371,0.234-1.984c-0.483-0.612-1.37-0.716-1.984-0.234C1.764,5.069,0.089,8.523,0.089,12.24c0,6.652,5.412,12.063,12.063,12.063s12.063-5.412,12.063-12.063C24.215,8.521,22.538,5.067,19.616,2.761z", 12, 12));
		put("Bot.Reload", new SVGGlyph("M12.2,3.8C11.1,2.7,9.7,2,8,2C4.7,2,2,4.7,2,8s2.7,6,6,6c2.8,0,5.1-1.9,5.8-5.1h-1.6c-0.6,2.4-2.3,3.6-4.2,3.6c-2.5,0-4.5-2-4.5-4.5s2-4.5,4.5-4.5c1.2,0,2.4,0.5,3.2,1.3L8.7,7.1H14V2L12.2,3.8z", 15, 15));
		// put("Bot.Compile", new SVGGlyph("M5.56253 2.51577C3.46348 3.4501 2 5.55414 2 7.99999C2 11.3137 4.68629 14 8 14C11.3137 14 14 11.3137 14 7.99999C14 5.32519 12.2497 3.05919 9.83199 2.28482L9.52968 3.23832C11.5429 3.88454 13 5.7721 13 7.99999C13 10.7614 10.7614 13 8 13C5.23858 13 3 10.7614 3 7.99999C3 6.31104 3.83742 4.81767 5.11969 3.91245L5.56253 2.51577Z", 15, 15));
	}}
}