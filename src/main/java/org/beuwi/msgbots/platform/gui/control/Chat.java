package org.beuwi.msgbots.platform.gui.control;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

import javax.swing.text.html.ImageView;

public class Chat extends HBox
{
	private static final String DEFAULT_STYLE_CLASS = "chat";
	private static final int DEFAULT_GAP_VALUE = 10;

	private static final PseudoClass HUMAN_PSEUDO_CLASS = PseudoClass.getPseudoClass("human");
	private static final PseudoClass BOT_PSEUDO_CLASS = PseudoClass.getPseudoClass("bot");

	// private static final int DEFAULT_MIN_HEIGHT = 50;

	private final Circle profile;
	private final Content content;

	private final ContextMenu menu;

	private ChatView parent;

	public Chat(String message)
	{
		this(message, false);
	}

	public Chat(String message, boolean isbot)
	{
        content = new Content(message, isbot);
        profile = new Circle(35, 35, 20);

		menu = new ContextMenu
		(
			new Menu("Copy"),
			new Menu("Delete")
		);

		menu.setNode(content);

		profile.getStyleClass().add("profile");

		if (!isbot)
		{
            setItems(content, profile);
			setAlignment(Pos.TOP_RIGHT);
		}
		else
		{
            setItems(profile, content);
			setAlignment(Pos.TOP_LEFT);
		}

		/* getWidthProperty().addListener(change ->
		{
			content.setMaxWidth(getWidth() - 100);
		}); */

		setSpacing(DEFAULT_GAP_VALUE);
		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public ChatView getView()
	{
		return parent;
	}

	public void setView(ChatView parent)
	{
		this.parent = parent;
	}

	private class Content extends VBox
	{
		private final Label comment = new Label();
		private final Label name = new Label();

		{
			VBox.setVgrow(comment, Priority.ALWAYS);
		}

		public Content(String message, boolean isbot)
		{
            name.setText("DEBUG SENDER");
            name.addStyleClass("name");

			// comment.setMaxWidth(220);
			comment.setText(message);
			comment.setWrapText(true);
			comment.setMaxWidth(250);
			comment.addStyleClass("comment");

			if (!isbot)
			{
			    setAlignment(Pos.CENTER_RIGHT);
			}
			else
			{
				setAlignment(Pos.CENTER_LEFT);
			}

			setItems(name, comment);
			setSpacing(5);
			setFittable(false);
			setFillWidth(false);
		}
	}
}