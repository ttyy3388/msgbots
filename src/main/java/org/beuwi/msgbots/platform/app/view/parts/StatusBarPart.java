package org.beuwi.msgbots.platform.app.view.parts;

import javafx.fxml.FXMLLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class StatusBarPart extends View
{
	@Override
	public void init() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("StatusBarPart"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}
}