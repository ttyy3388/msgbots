package org.beuwi.msgbots.platform.win;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.MenuItem;

public class WindowController 
{
    private BorderPane brpRootPane;
    private AnchorPane anpTitleBar;
    private HBox hbxTitleBar;
    private ImageView imvWinIcon;
    private Label lblWinTitle;
    private Button btnWinMaximize;
    private Button btnWinMinimize;
    private Button btnWinClose;

    // Window Icon Menu
	private final ContextMenu menu;

	public WindowController()
	{
		this.menu = new ContextMenu
		(
			new MenuItem("Restore"),
			new MenuItem("Minimize"),
			new MenuItem("Maximize"),
			new SeparatorMenuItem(),
			new MenuItem("Close")
		);

        menu.setNode(imvWinIcon);
	}
}