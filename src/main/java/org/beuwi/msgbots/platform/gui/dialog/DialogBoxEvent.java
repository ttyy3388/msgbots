package org.beuwi.msgbots.platform.gui.dialog;

public class DialogBoxEvent
{
	/* private BooleanProperty minimized = new SimpleBooleanProperty(false);
	private BooleanProperty closed    = new SimpleBooleanProperty(false);

	final Stage stage;

	public DialogBoxEvent(Stage stage)
	{
		this.stage = stage;
	}

	public BooleanProperty minimizedProperty()
	{
		return minimized;
	}

	public BooleanProperty closedProperty()
	{
		return closed;
	}

	public boolean isMinimized()
	{
		return minimized.get();
	}

	public boolean isClosed()
	{
		return closed.get();
	}

	private void minimize()
	{
		stage.setIconified(true);
	}

	private void close()
	{
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void setClosed()
	{
		Platform.runLater(() ->
		{
			close();
		});

		closed.set(true);
	}

	public void setMinimized()
	{
		Platform.runLater(() ->
		{
			minimize();
		});

		minimized.set(true);
	} */
}
