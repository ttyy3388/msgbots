package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.File;

public class ChatItem extends HBox {
	private static final String DEFAULT_STYLE_CLASS = "chat-item";
	private static final int DEFAULT_GAP_VALUE = 10;

	// private static final PseudoClass HUMAN_PSEUDO_CLASS = PseudoClass.getPseudoClass("human");
	// private static final PseudoClass BOT_PSEUDO_CLASS = PseudoClass.getPseudoClass("bot");

	private static final String HUMAN_STYLE_CLASS = "human";
	private static final String BOT_STYLE_CLASS = "bot";

	// private static final int DEFAULT_MIN_HEIGHT = 50;

	private final String message;

	private final Circle profileCircle;
	private final ChatContent chatContent;

	private ChatView parent;

	public ChatItem(String message) {
		this(message, false);
	}

	public ChatItem(String message, boolean isBot) {
		this.message = message;

		chatContent = new ChatContent(message, isBot);
		profileCircle = new Circle(35, 35, 20);

		profileCircle.getStyleClass().add("profile");

		if (!isBot) {
			// Sender Profile
			File imageFile = SharedValues.getFile("PROFILE_SENDER_FILE");
			// 유저가 이미지를 변경한 적이 있다면
			if (imageFile.exists()) {
				profileCircle.setFill(new ImagePattern(new Image(imageFile.toURI().toString())));
			}
			// 없다면 기본 이미지 로드
			else {
				profileCircle.setFill(new ImagePattern(ResourceUtils.getImage("profile")));
			}

			if (GlobalSettings.getBoolean("debug:show_sender_profile")) {
				getChildren().setAll(chatContent, profileCircle);
			}
			else {
				getChildren().setAll(chatContent);
			}

			setAlignment(Pos.TOP_RIGHT);
			getStyleClass().add(HUMAN_STYLE_CLASS);
		}
		else {
			// Bot Profile
			File imageFile = SharedValues.getFile("PROFILE_BOT_FILE");
			// 유저가 이미지를 변경한 적이 있다면
			if (imageFile.exists()) {
				profileCircle.setFill(new ImagePattern(new Image(imageFile.toURI().toString())));
			}
			// 없다면 기본 이미지 로드
			else {
				profileCircle.setFill(new ImagePattern(ResourceUtils.getImage("profile")));
			}
			if (GlobalSettings.getBoolean("debug:show_bot_profile")) {
				getChildren().setAll(profileCircle, chatContent);
			}
			else {
				getChildren().setAll(chatContent);
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

	private class ChatContent extends VBox {
		// 텍스트 필드로 바꾸면 드래그가 가능하긴 함
		private final Label commentLabel = new Label();
		private final Label nameLabel = new Label();

		private final ContextMenu contextMenu;

		{
			VBox.setVgrow(commentLabel, Priority.ALWAYS);
		}

		public ChatContent(String message, boolean isBot) {
			String name = null;
			if (!isBot) {
				name = GlobalSettings.getString("debug:sender_name");
			}
			else {
				name = GlobalSettings.getString("debug:bot_name");
			}
			nameLabel.setText(name);
			nameLabel.getStyleClass().add("name");

			contextMenu = new ContextMenu(
				new MenuItem("Copy", event -> CopyStringAction.execute(message)),
				new MenuItem("Delete", event -> parent.getItems().remove(this))
			);
			contextMenu.setNode(commentLabel);

			/* if (message.length() > 1000) {

			}
			else */ {
				// comment.setMaxWidth(220);
				commentLabel.setText(message);
				commentLabel.setWrapText(true);
				commentLabel.setMaxWidth(250);
				commentLabel.getStyleClass().add("comment");
			}

			if (!isBot) {
				if (GlobalSettings.getBoolean("debug:show_sender_name")) {
					getChildren().setAll(nameLabel, commentLabel);
				}
				else {
					getChildren().setAll(commentLabel);
				}

				setAlignment(Pos.CENTER_RIGHT);
			}
			else {
				if (GlobalSettings.getBoolean("debug:show_bot_name")) {
					getChildren().setAll(nameLabel, commentLabel);
				}
				else {
					getChildren().setAll(commentLabel);
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