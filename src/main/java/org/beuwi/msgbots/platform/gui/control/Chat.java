package org.beuwi.msgbots.platform.gui.control;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.setting.GlobalSettings;

public class Chat extends HBox {
	private static final String DEFAULT_STYLE_CLASS = "chat";
	private static final int DEFAULT_GAP_VALUE = 10;

	private static final PseudoClass HUMAN_PSEUDO_CLASS = PseudoClass.getPseudoClass("human");
	private static final PseudoClass BOT_PSEUDO_CLASS = PseudoClass.getPseudoClass("bot");

	// private static final int DEFAULT_MIN_HEIGHT = 50;

	private final Circle profile;
	private final Content content;

	private final ContextMenu menu;

	private ChatView parent;

	public Chat(String message) {
		this(message, false);
	}

	public Chat(String message, boolean isBot) {
        content = new Content(message, isBot);
        profile = new Circle(35, 35, 20);

		menu = new ContextMenu
		(
			new Menu("Copy", event -> CopyStringAction.execute(message)),
			new Menu("Delete", event -> parent.getItems().remove(this))
		);

		menu.setNode(content);

		profile.getStyleClass().add("profile");

		if (!isBot) {
			getItems().setAll(content);
            // getItems().setAll(content, profile);
			setAlignment(Pos.TOP_RIGHT);
		}
		else {
			if (GlobalSettings.getBoolean("debug:show_bot_profile")) {
				getItems().setAll(profile, content);
			}
			else {
				getItems().setAll(content);
			}

			setAlignment(Pos.TOP_LEFT);
		}

		pseudoClassStateChanged(HUMAN_PSEUDO_CLASS, !isBot);
		pseudoClassStateChanged(BOT_PSEUDO_CLASS, isBot);

		/* getWidthProperty().addListener(change ->
		{
			content.setMaxWidth(getWidth() - 100);
		}); */

		setSpacing(DEFAULT_GAP_VALUE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public ChatView getView() {
		return parent;
	}

	public void setView(ChatView parent) {
		this.parent = parent;
	}

	private class Content extends VBox {
		private final Label comment = new Label();
		private final Label name = new Label();

		{
			VBox.setVgrow(comment, Priority.ALWAYS);
		}

		public Content(String message, boolean isbot) {
            name.setText("DEBUG SENDER");
            name.getStyleClass().add("name");

			/* if (message.length() > 1000)
			{

			}
			else */
			{
				// comment.setMaxWidth(220);
				comment.setText(message);
				comment.setWrapText(true);
				comment.setMaxWidth(200);
				comment.getStyleClass().add("comment");
			}

			if (!isbot) {
			    setAlignment(Pos.CENTER_RIGHT);
			}
			else {
				setAlignment(Pos.CENTER_LEFT);
			}

			setSpacing(5);
			setFittable(false);
			setFillWidth(false);

			if (!isbot) {
				if (GlobalSettings.getBoolean("debug:show_sender_name")) {
					getItems().setAll(name, comment);
				}
				else {
					getItems().setAll(comment);
				}
			}
			else {

				if (GlobalSettings.getBoolean("debug:show_bot_name")) {
					getItems().setAll(name, comment);
				}
				else {
					getItems().setAll(comment);
				}
			}
		}
	}
}