package org.beuwi.msgbots.view.gui.control;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.setting.GlobalSettings;
import org.beuwi.msgbots.shared.SharedValues;
import org.beuwi.msgbots.view.gui.layout.HBox;
import org.beuwi.msgbots.view.gui.layout.VBox;
import org.beuwi.msgbots.utils.ResourceUtils;

public class ChatItem extends HBox {
	private final String message;
	private final boolean isBot;

	private final Circle profile;
	private final ChatContent content;

	private ChatView parent;

	public ChatItem(String message) {
		this(message, false);
	}

	public ChatItem(String message, boolean isBot) {
		content = new ChatContent(message, isBot);
		profile = new Circle(35, 35, 20);

		this.message = message;
		this.isBot = isBot;

		profile.getStyleClass().add("profile");

		// 기본 값 : 프로그램 기본 이미지
		profile.setFill(new ImagePattern(ResourceUtils.getImage("profile")));

		if (!isBot) {
			Dfile dfile = SharedValues.getDfile("dfile.profileSender");
			if (dfile.isCreated()) {
				profile.setFill(new ImagePattern(new Image(dfile.toFile().toURI().toString())));
			}

			setAlignment(Pos.TOP_RIGHT);
			getChildren().setAll(content);
			pseudoClassStateChanged(PseudoClass.getPseudoClass("human"), true);

			if (GlobalSettings.getBoolean("debug.showSenderProfile")) {
				getChildren().add(profile);
			}
		}
		else {
			Dfile dfile = SharedValues.getDfile("dfile.profileBot");
			if (dfile.isCreated()) {
				profile.setFill(new ImagePattern(new Image(dfile.toFile().toURI().toString())));
			}

			setAlignment(Pos.TOP_LEFT);
			getChildren().setAll(content);
			pseudoClassStateChanged(PseudoClass.getPseudoClass("bot"), true);

			if (GlobalSettings.getBoolean("debug.showBotProfile")) {
				getChildren().add(0, profile);
			}
		}

		setSpacing(10);
		getStyleClass().add("chat-item");
	}

	public ChatView getView() {
		return parent;
	}

	public void setView(ChatView parent) {
		this.parent = parent;
	}

	private class ChatContent extends VBox {
		// 텍스트 필드로 바꾸면 드래그가 가능하긴 함
		private final VBox boxComment = new VBox(); // 원래는 [Label]로 생성했으나 전체보기 때문에 [VBox]로 변경
		private final Label lblComment = new Label();
		private final Label lblName = new Label();

		{
			VBox.setVgrow(boxComment, Priority.ALWAYS);
			VBox.setVgrow(lblComment, Priority.ALWAYS);
		}

		public ChatContent(String message, boolean isBot) {
			String name = null;
			if (!isBot) name = "SENDER";
			else name = "BOT";

			lblName.setText(name);
			lblName.getStyleClass().add("name-label");

			lblComment.setText(message);
			lblComment.setWrapText(true);
			// lblComment.setMaxWidth(250);
			lblComment.setMaxWidth(220);
			lblComment.getStyleClass().add("comment-label");

			boxComment.getChildren().setAll(lblComment);
			boxComment.getStyleClass().add("comment-box");

			// 1000 글자가 넘으면 메시지 자르고 전체보기 버튼 추가
			if (message.length() > 500) {
				TextArea textArea = new TextArea(message);
				textArea.setEditable(false);
				textArea.setWrapText(true);
				textArea.setPrefWidth(400);
				textArea.setPrefHeight(500);
				textArea.setMaxHeight(700);
				Button btnView = new Button("VIEW ALL");
				btnView.setPrefHeight(25);
				btnView.getStyleClass().add("view-button");
				boxComment.getChildren().add(new Separator());
				boxComment.getChildren().add(btnView);

				// 1000 글자 메시지 자르고 "..." 입력
				lblComment.setText(message.substring(0, 500) + "...");
			}

			boolean showName = false;
			Pos alignment = null;
			if (!isBot) {
				showName = GlobalSettings.getBoolean("debug.showSenderName");
				alignment = Pos.CENTER_RIGHT;
			}
			else {
				showName = GlobalSettings.getBoolean("debug.showBotName");
				alignment = Pos.CENTER_LEFT;
			}
			
			setSpacing(5);
			setAlignment(alignment);
			setFitChild(false);
			setFillWidth(false);
			getStyleClass().add("content");

			if (showName) getChildren().setAll(lblName, boxComment);
			else getChildren().setAll(boxComment);
		}
	}

	public boolean isBot() {
		return isBot;
	}
	public String getMessage() {
		return message;
	}
}