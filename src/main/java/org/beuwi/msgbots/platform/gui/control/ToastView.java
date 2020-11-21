package org.beuwi.msgbots.platform.gui.control;

public class ToastView extends VBox<Toast>
{
	private static final String DEFAULT_STYLE_CLASS = "toast-view";

	private static final int DEFAULT_PREF_WIDTH = 500;
	private static final int DEFAULT_GAP_VALUE = 10;

	public ToastView()
	{
		/* getItemsProperty().addListener((ListChangeListener<Node>) change ->
		{
			for (Toast toast : getToasts())
			{
				toast.setView(this);
			}
		}); */

		setSpacing(DEFAULT_GAP_VALUE);
		setPrefWidth(DEFAULT_PREF_WIDTH);
	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	@Override
	public Toast getItem(int index)
	{
		return getItem(index);
	}

	@Override
	public Toast getItem(String id)
	{
		return getItem(id);
	}
}