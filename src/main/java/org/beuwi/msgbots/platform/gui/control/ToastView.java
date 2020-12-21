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

	public Toast getToast(int index) {
		return (Toast) getItems().get(index);
	}

	public Toast getToast(String id) {
		return (Toast) getItems().get(findItem(id));
	}
}