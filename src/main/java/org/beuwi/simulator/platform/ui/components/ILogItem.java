package org.beuwi.simulator.platform.ui.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.utils.ResourceUtils;

import static org.beuwi.simulator.platform.ui.components.ILogType.DEBUG;
import static org.beuwi.simulator.platform.ui.components.ILogType.ERROR;
import static org.beuwi.simulator.platform.ui.components.ILogType.EVENT;

public class ILogItem extends AnchorPane
{
	{
		getStyleClass().add("ifx-log-item");
	}

	private IListView view;

	public ILogItem(String text, String date, int type)
	{
		String itemType = switch (type)
		{
			case ERROR -> "error";
			case EVENT -> "event";
			case DEBUG -> "debug";
			default    -> null;
		};

		ImageView  image = new ImageView(ResourceUtils.getImage(itemType));
		Label 	   label = new Label(text);
		Label      idate = new Label(date);

		image.setFitWidth(20);
		image.setFitHeight(20);

		AnchorPane.setTopAnchor(image, 15.0);
		AnchorPane.setLeftAnchor(image, 20.0);

		AnchorPane.setTopAnchor(label, 15.0);
		AnchorPane.setLeftAnchor(label, 50.0);
		AnchorPane.setBottomAnchor(label, 50.0);
		AnchorPane.setRightAnchor(label, 20.0);

		AnchorPane.setLeftAnchor(idate, 50.0);
		AnchorPane.setBottomAnchor(idate, 15.0);

		IContextMenu menu = new IContextMenu
		(
			new IMenuItem("Copy", "Ctrl + C", event -> view.copy()),
			new SeparatorMenuItem(),
			new IMenuItem("Select All", "Ctrl + A", event -> view.selectAll())
		);

		menu.setNode(this);

		label.setWrapText(true);
		label.getStyleClass().add(itemType);
		idate.getStyleClass().add(itemType);

		setId(text);
		setCursor(Cursor.HAND);
		setMinWidth(90);
		getChildren().addAll(image, label, idate);
	}

	public IListView getView()
	{
		return view;
	}

	public void setView(IListView view)
	{
		this.view = view;
	}
}