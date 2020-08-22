package org.beuwi.msgbots.platform.gui.area;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;
import org.beuwi.msgbots.platform.gui.control.TabPane.Type;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class SidePane extends AnchorPane
{
	private static final double DEFAULT_WIDTH = 250;

	private final TabPane pane = new TabPane();

	// Resize Bar
	private final Pane resize = new Pane();

	// Bots Tab
	private final Tab bots = new Tab();

	// Simulation(Debug) Tab
	private final Tab debug = new Tab();

	// Tab Header Area
	private final Node header;

	// Tab Content Area
	private final Node content;

	{
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0);

		AnchorPane.setTopAnchor(resize, 0.0);
		AnchorPane.setRightAnchor(resize, 0.0);
		AnchorPane.setBottomAnchor(resize, 0.0);
	}

	public SidePane()
	{
		pane.addTab(bots, debug);
		pane.setType(Type.SYSTEM);

		bots.setTitle("Bots");
		debug.setTitle("Debug");

		bots.setPinned(true);
		debug.setPinned(true);

		header = pane.getHeaderArea();
		content = pane.getContentArea();

		resize.setCursor(Cursor.E_RESIZE);
		resize.setPrefWidth(5.0);

		resize.setOnMouseDragged(event ->
		{
			resize(event);
		});

		setMinWidth(DEFAULT_WIDTH);
		getChildren().addAll(pane, resize);
		getStyleClass().add("active-area");
		getStylesheets().add(ResourceUtils.getStyle("active-area-part"));
	}

	public void resize(MouseEvent event)
	{
		pane.setPrefWidth(event.getSceneX());
	}
}