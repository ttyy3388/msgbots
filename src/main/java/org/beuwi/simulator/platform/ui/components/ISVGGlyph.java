package org.beuwi.simulator.platform.ui.components;

import com.jfoenix.svg.SVGGlyph;
import javafx.scene.paint.Color;

public class ISVGGlyph extends SVGGlyph
{
    public ISVGGlyph()
    {

    }

    public ISVGGlyph(String path)
    {
        super(path);
    }

    public ISVGGlyph(String path, Color fill)
    {
        super(path, fill);
    }

    public ISVGGlyph(String path, Color fill, int width, int height)
    {
        super(path, fill);
        this.setSize(width, height);
    }
}