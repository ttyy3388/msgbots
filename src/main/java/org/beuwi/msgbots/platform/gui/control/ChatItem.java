package org.beuwi.msgbots.platform.gui.control;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.VBox;
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
		profile.setFill(new ImagePattern(ResourceUtils.getImage("profile")));

		if (!isBot) {
			setAlignment(Pos.TOP_RIGHT);
			getChildren().setAll(content /*, profile */);
			pseudoClassStateChanged(PseudoClass.getPseudoClass("human"), true);
		}
		else {
			setAlignment(Pos.TOP_LEFT);
			getChildren().setAll(profile, content);
			pseudoClassStateChanged(PseudoClass.getPseudoClass("bot"), true);
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
				TextArea textarea = new TextArea(message);
				textarea.setEditable(false);
				textarea.setWrapText(true);
				textarea.setPrefWidth(400);
				textarea.setPrefHeight(500);
				textarea.setMaxHeight(700);
				Button btnView = new Button("VIEW ALL");
				btnView.setPrefHeight(25);
				btnView.getStyleClass().add("view-button");
				boxComment.getChildren().add(new Separator());
				boxComment.getChildren().add(btnView);

				// 1000 글자 메시지 자르고 "..." 입력
				lblComment.setText(message.substring(0, 500) + "...");
			}

			Pos alignment = null;
			if (!isBot) {
				alignment = Pos.CENTER_RIGHT;
			}
			else {
				alignment = Pos.CENTER_LEFT;
			}
			
			setSpacing(5);
			setAlignment(alignment);
			setFitChild(false);
			setFillWidth(false);
			getStyleClass().add("content");
			getChildren().setAll(lblName, boxComment);
		}
	}

	public boolean isBot() {
		return isBot;
	}
	public String getMessage() {
		return message;
	}
}