package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;

import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.setting.GlobalSettings;

public class ChatItem extends HBox {
	private static final String DEFAULT_STYLE_CLASS = "chat-item";
	private static final int DEFAULT_GAP_VALUE = 10;

	// private static final PseudoClass HUMAN_PSEUDO_CLASS = PseudoClass.getPseudoClass("human");
	// private static final PseudoClass BOT_PSEUDO_CLASS = PseudoClass.getPseudoClass("bot");

	private static final String HUMAN_STYLE_CLASS = "human";
	private static final String BOT_STYLE_CLASS = "bot";

	// private static final int DEFAULT_MIN_HEIGHT = 50;

	private final String message;

	private final Circle profile;
	private final Content content;

	private ChatView parent;

	public ChatItem(String message) {
		this(message, false);
	}

	public ChatItem(String message, boolean isBot) {
		this.message = message;

        content = new Content(message, isBot);
        profile = new Circle(35, 35, 20);

		profile.getStyleClass().add("profile");

		if (!isBot) {
			// Sender Profile
			// profile.setFill(new ImagePattern(SharedValues.PROFILE_SENDER_IMAGE));

			if (GlobalSettings.getBoolean("debug:show_sender_profile")) {
				getChildren().setAll(content, profile);
			}
			else {
				getChildren().setAll(content);
			}

			setAlignment(Pos.TOP_RIGHT);
			getStyleClass().add(HUMAN_STYLE_CLASS);
		}
		else {
			// Bot Profile
			// profile.setFill(new ImagePattern(SharedValues.PROFILE_BOT_IMAGE));

			if (GlobalSettings.getBoolean("debug:show_bot_profile")) {
				getChildren().setAll(profile, content);
			}
			else {
				getChildren().setAll(content);
			}

			setAlignment(Pos.TOP_LEFT);
			getStyleClass().add(BOT_STYLE_CLASS);
		}

		// pseudoClassStateChanged(HUMAN_PSEUDO_CLASS, !isBot);
		// pseudoClassStateChanged(BOT_PSEUDO_CLASS, isBot);

		/* getWidthProperty().addListener(change -> {
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
		// 텍스트 필드로 바꾸면 드래그가 가능하긴 함
		private final Label comment = new Label();
		private final Label name = new Label();

		private final ContextMenu menu;

		{
			VBox.setVgrow(comment, Priority.ALWAYS);
		}

		public Content(String message, boolean isBot) {
            name.setText("DEBUG SENDER");
            name.getStyleClass().add("name");

			menu = new ContextMenu(
				new MenuItem("Copy", event -> CopyStringAction.execute(message)),
				new MenuItem("Delete", event -> parent.getItems().remove(this))
			);
			menu.setNode(comment);

			/* if (message.length() > 1000)
			{

			}
			else */
			{
				// comment.setMaxWidth(220);
				comment.setText(message);
				comment.setWrapText(true);
				comment.setMaxWidth(250);
				comment.getStyleClass().add("comment");
			}

			if (!isBot) {
				if (GlobalSettings.getBoolean("debug:show_sender_name")) {
					getChildren().setAll(name, comment);
				}
				else {
					getChildren().setAll(comment);
				}

				setAlignment(Pos.CENTER_RIGHT);
			}
			else {
				if (GlobalSettings.getBoolean("debug:show_bot_name")) {
					getChildren().setAll(name, comment);
				}
				else {
					getChildren().setAll(comment);
				}

				setAlignment(Pos.CENTER_LEFT);
			}

			setSpacing(5);
			setFittable(false);
			setFillWidth(false);
		}
	}

	public String getMessage() {
		return message;
	}
}