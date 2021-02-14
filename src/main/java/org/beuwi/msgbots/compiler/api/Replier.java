package org.beuwi.msgbots.compiler.api;

import javafx.application.Platform;
import org.beuwi.msgbots.platform.app.view.actions.SendChatMessageAction;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class Replier {
	@JSStaticFunction
	public static Boolean reply(String message) {
		Platform.runLater(() -> {
			SendChatMessageAction.execute(message, true);
		});

		return true;
	}

	@JSStaticFunction
	public static Boolean reply(String room, String message, Boolean hideToast) {
        Platform.runLater(() -> {
            SendChatMessageAction.execute(message, true);
        });

		return true;
	}
}