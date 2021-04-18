package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.scene.text.TextAlignment;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.dialogs.ShowPageDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.YesOrNoDialog;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
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
	private final boolean isBot;

	private final Circle profileCircle;

	private final ChatContent chatContent;

	private ChatView parent;

	public ChatItem(String message) {
		this(message, false);
	}

	public ChatItem(String message, boolean isBot) {
		chatContent = new ChatContent(message, isBot);
		profileCircle = new Circle(35, 35, 20);

		this.message = message;
		this.isBot = isBot;

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
		private final VBox commentBox = new VBox(); // 원래는 [Label]로 생성했으나 전체보기 때문에 [VBox]로 변경
		private final Label commentLabel = new Label();
		private final Label nameLabel = new Label();

		private final ContextMenu contextMenu;

		{
			VBox.setVgrow(commentBox, Priority.ALWAYS);
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
			nameLabel.getStyleClass().add("name-label");

			contextMenu = new ContextMenu(
				new MenuItem("Copy", event -> CopyStringAction.execute(message)),
				new MenuItem("Delete", event -> parent.getItems().remove(this))
			);
			contextMenu.setNode(commentLabel);

			// comment.setMaxWidth(220);
			commentLabel.setText(message);
			commentLabel.setWrapText(true);
			commentLabel.setMaxWidth(250);
			commentLabel.getStyleClass().add("comment-label");

			commentBox.getChildren().setAll(commentLabel);
			commentBox.getStyleClass().add("comment-box");

			// 1000 글자가 넘으면 메시지 자르고 전체보기 버튼 추가
			if (message.length() > 1000) {
				TextArea textarea = new TextArea(message);
				textarea.setEditable(false);
				textarea.setWrapText(true);
				textarea.setPrefWidth(400);
				textarea.setPrefHeight(500);
				textarea.setMaxHeight(700);
				Button viewButton = new Button("VIEW ALL");
				viewButton.setPrefHeight(25);
				viewButton.getStyleClass().add("view-button");
				viewButton.setOnAction(event -> {
					OpenDialogBoxAction.execute(new YesOrNoDialog() {
						@Override
						protected void open() {
							setUseButton(true, false);
							getActionButton().setText("Copy");
							setContent(new StackPane(textarea));
							setTitle("View All");
						}

						@Override
						protected boolean action() {
							CopyStringAction.execute(textarea.getText());
							return true;
						}
					});
				});
				commentBox.getChildren().add(new Separator());
				commentBox.getChildren().add(viewButton);

				// 1000 글자 메시지 자르고 "..." 입력
				commentLabel.setText(message.substring(1000) + "...");
			}

			boolean showName = false;
			Pos alignment = null;

			if (!isBot) {
				showName = GlobalSettings.getBoolean("debug:show_sender_name");
				alignment = Pos.CENTER_RIGHT;
			}
			else {
				showName = GlobalSettings.getBoolean("debug:show_bot_name");
				alignment = Pos.CENTER_LEFT;
			}

			if (showName) {
				getChildren().setAll(nameLabel, commentBox);
			}
			else {
				getChildren().setAll(commentBox);
			}
			setAlignment(alignment);

			setSpacing(5);
			setFittable(false);
			setFillWidth(false);
		}
	}

	public String getMessage() {
		return message;
	}
}