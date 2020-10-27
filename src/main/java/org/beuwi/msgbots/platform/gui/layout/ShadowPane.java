package org.beuwi.msgbots.platform.gui.layout;

public class ShadowPane extends StackPane {

    private static final String DEFAULT_STYLE_CLASS = "shadow-pane";

    // private final ObjectProperty<Node> content = new SimpleObjectProperty<>();

    // private static final int DEFAULT_PADDING_VALUE = 5;

	public ShadowPane() {

		/* getContentProperty().addListener((observable, oldValue, newValue) ->
		{
			if (getContent() != null)
			{
				Node content = getContent();

				super.setContent(content);
			}
		}); */

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	/* @Override
	public void setContent(Node content)
    {
		this.content.set(content);
    }

    @Override
    public Node getContent()
	{
		return content.get();
	}

	public ObjectProperty<Node> getContentProperty()
	{
		return content;
	} */
}