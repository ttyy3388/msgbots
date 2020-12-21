package org.beuwi.msgbots.platform.gui.control;

import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.beuwi.msgbots.platform.app.action.OpenBrowserAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class Page extends StackPanel {
    private static final String DEFAULT_STYLE_CLASS = "page";

    private final Actions actions = new Actions();
    public class Actions {
        public void execute(String action)
        {
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

    private final WebView view = new WebView();
    private final WebEngine engine;
    private final Worker worker;

    public Page() {
        this(null);
    }

    public Page(String name) {
        engine = view.getEngine();
        worker = engine.getLoadWorker();

        engine.load(ResourceUtils.getHtml(name));
        // engine.setUserAgent("");
        engine.setJavaScriptEnabled(true);
        engine.setUserStyleSheetLocation(ResourceUtils.getStyle("page"));;

        worker.stateProperty().addListener(change -> {
            Worker.State state = worker.getState();

            if (state.equals(Worker.State.SUCCEEDED)) {
                JSObject object = (JSObject) engine.executeScript("window");

                object.setMember("program", actions);
            }
        });

        getItems().setAll(view);
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    }
}