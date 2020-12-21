package org.beuwi.msgbots.platform.util;

import javafx.scene.paint.Color;
import org.beuwi.msgbots.platform.gui.control.SVGGlyph;

import java.util.HashMap;

public class AllSVGIcons {
	public static SVGGlyph get(String key) {
		return new Glyphs().get(key);
	}

	private final static class Glyphs extends HashMap<String, SVGGlyph> {{
		put("Dialog.Minimize", new SVGGlyph("M 0 0 L 1 0 L 1 1 L 0 1 Z", Color.valueOf("#8C8C8C"), 10, 1));
		put("Dialog.Maximize", new SVGGlyph("M 0 0 L 60 0 L 60 60 L 0 60 L 5 55 L 55 55 L 55 5 L 5 5 L 5 55 L 0 60 Z", Color.valueOf("#8C8C8C"), 10, 10));
		put("Dialog.Restore", new SVGGlyph("M 0 60 L 0 12 L 12 12 L 12 0 L 60 0 L 60 48 L 48 48 L 48 42 L 54 42 L 54 6 L 18 6 L 18 12 L 48 12 L 48 60 L 0 60 L 6 54 L 42 54 L 42 18 L 6 18 L 6 54 Z", Color.valueOf("#8C8C8C"), 10, 10));
		put("Dialog.Close", new SVGGlyph("M 0 3 L 3 0 L 30 27 L 57 0 L 60 3 L 33 30 L 60 57 L 57 60 L 30 33 L 3 60 L 0 57 L 27 30 Z", Color.valueOf("#8C8C8C"), 10, 10));

		put("Box.Close", new SVGGlyph("M357,35.7 321.3,0 178.5,142.8 35.7,0 0,35.7 142.8,178.5 0,321.3 35.7,357 178.5,214.2 321.3,357 357,321.3 214.2,178.5", 10, 10));
		put("Tab.Close", new SVGGlyph("M12.3,13L8,8.7L3.7,13L3,12.3L7.3,8L3,3.7L3.7,3L8,7.3L12.3,3L13,3.7L8.7,8l4.3,4.3L12.3,13z", 8, 8));

		put("Editor.Split", new SVGGlyph("M28.4,0H1.6C0.7,0,0,0.7,0,1.6v26.9C0,29.3,0.7,30,1.6,30h26.9c0.9,0,1.6-0.7,1.6-1.6V1.6C30,0.7,29.3,0,28.4,0z M2.8,28.3c-0.6,0-1-0.4-1-1V2.8c0-0.6,0.4-1,1-1H14v26.5H2.8z M28.3,27.3c0,0.6-0.4,1-1,1H16V1.8h11.3c0.6,0,1,0.4,1,1V27.3z", 13, 13));
		put("Editor.More", new SVGGlyph("M2,0C0.9,0,0,0.9,0,2s0.9,2,2,2s2-0.9,2-2S3.1,0,2,0z M14,0c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S15.1,0,14,0z M8,0 C6.9,0,6,0.9,6,2s0.9,2,2,2s2-0.9,2-2S9.1,0,8,0z", 13, 2));

		// put("Button.More", new SVGGlyph("M2,0C0.9,0,0,0.9,0,2s0.9,2,2,2s2-0.9,2-2S3.1,0,2,0z M14,0c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S15.1,0,14,0z M8,0 C6.9,0,6,0.9,6,2s0.9,2,2,2s2-0.9,2-2S9.1,0,8,0z", 13, 2));

		put("CheckBox.Mark", new SVGGlyph());

		put("Bot.Compile", new SVGGlyph("M5.56253 2.51577C3.46348 3.4501 2 5.55414 2 7.99999C2 11.3137 4.68629 14 8 14C11.3137 14 14 11.3137 14 7.99999C14 5.32519 12.2497 3.05919 9.83199 2.28482L9.52968 3.23832C11.5429 3.88454 13 5.7721 13 7.99999C13 10.7614 10.7614 13 8 13C5.23858 13 3 10.7614 3 7.99999C3 6.31104 3.83742 4.81767 5.11969 3.91245L5.56253 2.51577Z", 15, 15));
	}}
}