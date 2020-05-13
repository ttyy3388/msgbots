package org.beuwi.simulator.managers;

import javafx.scene.control.CheckBox;
import org.beuwi.simulator.platform.ui.components.IToggleButton;

import java.util.HashMap;

public class BotManager
{
    public static HashMap<String, Object> data = new HashMap<>();

    public static void setPower(String name, boolean power)
    {
        ((IToggleButton) data.get("@switch::" + name)).setSelected(power);
    }

    public static void setCompiled(String name, boolean compiled)
    {
        ((CheckBox) data.get("@check::" + name)).setSelected(compiled);
    }

    public static boolean getPower(String name)
    {
        return ((IToggleButton) data.get("@switch::" + name)).isSelected();
    }

    public static boolean isCompiled(String name)
    {
        return ((CheckBox) data.get("@check::" + name)).isSelected();
    }
}