package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.beuwi.msgbots.platform.app.action.OpenBrowserAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class Document extends StackPanel
{
	private static final String DEFAULT_STYLE_CLASS = "document";

	private final StringProperty page = new SimpleStringProperty();

	private final WebView view = new WebView();
	private final WebEngine engine;
	private final Worker worker;

	private final Actions actions = new Actions();
	public class Actions
	{
		public void execute(String action)
		{
			System.out.println(action);

			String clazz = action.split("\\(")[0];
			String param = action.split("\\(")[1].split("\\)")[0];

			switch (clazz) {
				case "open.dialog.action" :
					switch (param) {
						case "create.bot.dialog": OpenDialogBoxAction.execute(new CreateBotDialog()); break;
						case "import.bot.dialog": OpenDialogBoxAction.execute(new ImportBotDialog()); break;
					}
					break;
				case "open.browser.action" : OpenBrowserAction.execute(param); break;
			}
		}
	}

	public Document()
	{
	    this(null);
	}

	public Document(String page)
    {
        engine = view.getEngine();
		worker = engine.getLoadWorker();

        // engine.setUserAgent("");
		engine.setJavaScriptEnabled(true);
		// engine.setUserStyleSheetLocation(ResourceUtils.getStyle("pages"));;

        if (page != null)
        {
            setPage(page);
        }

        worker.stateProperty().addListener(change ->
		{
			State state = worker.getState();

			if (state.equals(State.SUCCEEDED))
			{
				JSObject object = (JSObject) engine.executeScript("window");

				object.setMember("program", actions);
			}
		});

        /* engine.setOnAlert(event ->
		{
			Actions.execute(event.getData());
		}); */

        getPageProperty().addListener(change ->
		{
			engine.load(ResourceUtils.getPage(getPage()));
		});

        setItem(view);
        setStyleClass("document");
	}

	/* public void setLink(String link)
	{
		engine.load(link);
	} */

	public void setPage(String page)
	{
		this.page.set(page);
	}

	/* public void setContent(String html)
	{
		engine.loadContent(html);
	} */

	public String getPage()
	{
		return page.get();
	}

	/* public String getLink()
	{
		return engine.getLocation();
	} */

	public StringProperty getPageProperty()
	{
		return page;
	}
}