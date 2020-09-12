package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public class Bot extends GridPane
{
    private static int DEFAULT_WIDTH = 30;

	final CheckBox     check  = new CheckBox();     // Compiled Check Box
    final Label        label  = new Label();        // Name Label
	final Button       button = new Button();       // Compile Button
    final ToggleButton power  = new ToggleButton(); // Power Switch

    {

    }

	public Bot(String name)
	{
        add(check,  0, 0, 1, 1);
        add(label,  0, 1, 3, 1);
        add(button, 0, 5, 1, 1);
        add(power,  0, 6, 1, 1);

	    setId(name);
	    setHeight(DEFAULT_WIDTH);
	}
}
