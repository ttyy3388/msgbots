package org.beuwi.simulator.platform.ui.active;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class IActivePane extends AnchorPane
{
	final ITabPane pane = new ITabPane();

	// Resize Bar
	final StackPane resize = new StackPane();

	final BooleanProperty hided = new SimpleBooleanProperty(false);

	public IActivePane()
	{
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setRightAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane, 0.0);
		AnchorPane.setLeftAnchor(pane, 0.0);

		AnchorPane.setTopAnchor(resize, 0.0);
		AnchorPane.setRightAnchor(resize, 0.0);
		AnchorPane.setBottomAnchor(resize, 0.0);

		pane.setTabSize(30, 35);

		resize.setPrefWidth(7);
		resize.setCursor(Cursor.E_RESIZE);
		resize.setOnMouseDragged(event -> resize(event));

		tab.setOnMouseClicked(event ->
		{
			IAct
		});

		getChildren().addAll(pane, resize);
	}

	public boolean isHided()
	{
		return hided.get();
	}

	public void hide()
	{

	}

	public void select(Tab tab)
	{

	}

	public void resize(MouseEvent event)
	{
		double size = event.getSceneX();

		if (isHided())
		{
			if (100 <= size)
			{
				hided.set(false);
			}
		}
		else
		{
			hided.set(true);
		}
	}
}
