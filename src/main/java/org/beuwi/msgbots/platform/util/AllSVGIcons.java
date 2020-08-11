package org.beuwi.msgbots.platform.util;

import javafx.scene.paint.Color;
import org.beuwi.msgbots.platform.gui.control.SVGGlyph;

import java.util.HashMap;

public class AllSVGIcons
{
    public static SVGGlyph get(String key)
    {
        return new Glyphs().get(key);
    }

    private final static class Glyphs extends HashMap<String, SVGGlyph>
    {
        {
            put("Window.Minimize", new SVGGlyph("M3,8h10v1H3V8z", Color.valueOf("#8C8C8C"), 10, 1));
            put("Window.Maximize", new SVGGlyph("M3,3 L13,3 L13,13 L3,13 L3,3 Z M4,4 L4,12 L12,12 L12,4 L4,4 Z", Color.valueOf("#8C8C8C"), 10, 10));
            put("Window.Close", new SVGGlyph("M12.3,13L8,8.7L3.7,13L3,12.3L7.3,8L3,3.7L3.7,3L8,7.3L12.3,3L13,3.7L8.7,8l4.3,4.3L12.3,13z", Color.valueOf("#8C8C8C"), 10, 10));

            put("Tab.Close", new SVGGlyph("M12.3,13L8,8.7L3.7,13L3,12.3L7.3,8L3,3.7L3.7,3L8,7.3L12.3,3L13,3.7L8.7,8l4.3,4.3L12.3,13z", 8, 8));

            put("Editor.Split", new SVGGlyph("M28.4,0H1.6C0.7,0,0,0.7,0,1.6v26.9C0,29.3,0.7,30,1.6,30h26.9c0.9,0,1.6-0.7,1.6-1.6V1.6C30,0.7,29.3,0,28.4,0z M2.8,28.3c-0.6,0-1-0.4-1-1V2.8c0-0.6,0.4-1,1-1H14v26.5H2.8z M28.3,27.3c0,0.6-0.4,1-1,1H16V1.8h11.3c0.6,0,1,0.4,1,1V27.3z", 13, 13));
            put("Editor.More", new SVGGlyph("M2,0C0.9,0,0,0.9,0,2s0.9,2,2,2s2-0.9,2-2S3.1,0,2,0z M14,0c-1.1,0-2,0.9-2,2s0.9,2,2,2s2-0.9,2-2S15.1,0,14,0z M8,0 C6.9,0,6,0.9,6,2s0.9,2,2,2s2-0.9,2-2S9.1,0,8,0z", 13, 2));
        }
    }
}