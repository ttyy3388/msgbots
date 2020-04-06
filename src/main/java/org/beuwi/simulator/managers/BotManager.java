package org.beuwi.simulator.managers;

import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.control.CheckBox;

import java.util.HashMap;

public class BotManager
{
	public static HashMap<String, Object> data = new HashMap<>();

	public static void setPower(String name, boolean power)
	{
		data.put("@switch" + name, power);
	}

	public static void setCompiled(String name, boolean compiled)
	{
		data.put("@check" + name, compiled);
	}

	public static boolean getPower(String name)
	{
		return ((JFXToggleButton) data.get("@switch" + name)).isSelected();
	}

	public static boolean isCompiled(String name)
	{
		return ((CheckBox) data.get("@check" + name)).isSelected();
	}
}