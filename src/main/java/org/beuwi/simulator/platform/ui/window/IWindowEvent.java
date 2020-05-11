package org.beuwi.simulator.platform.ui.window;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.List;

public class IWindowEvent
{
	private BooleanProperty minimized = new SimpleBooleanProperty(false);
	private BooleanProperty maximized = new SimpleBooleanProperty(false);
	private BooleanProperty closed    = new SimpleBooleanProperty(false);

	// Saved Bounds Box
	private BoundingBox original;
	private Rectangle2D bounds;

	final IWindowView main;
	final Stage stage;

	public IWindowEvent(IWindowView main)
	{
		this.main  = main;
		this.stage = main.getStage();
	}

	public BooleanProperty minimizedProperty()
	{
		return minimized;
	}

	public BooleanProperty maximizedProperty()
	{
		return maximized;
	}

	public BooleanProperty closedProperty()
	{
		return closed;
	}

	public boolean isMinimized()
	{
		return minimized.get();
	}

	public boolean isMaximized()
	{
		return maximized.get();
	}

	public boolean isClosed()
	{
		return closed.get();
	}

	private void minimize()
	{
		stage.setIconified(true);
	}

	private void maximize()
	{
		original = new BoundingBox
		(
			stage.getX(),
			stage.getY(),
			stage.getWidth(),
			stage.getHeight()
		);

		List<Screen> screens = Screen.getScreensForRectangle
		(
			stage.getX(),
			stage.getY(),
			stage.getWidth(),
			stage.getHeight()
		);

		Screen screen = screens.get(0);

		bounds = screen.getVisualBounds();

		stage.setX(bounds.getMinX());
		stage.setY(bounds.getMinY());
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
	}

	private void restore()
	{
		stage.setX(original.getMinX());
		stage.setY(original.getMinY());
		stage.setWidth(original.getWidth());
		stage.setHeight(original.getHeight());

		original = null;
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
	}

	public void setMaximized()
	{
		setMaximized(!isMaximized());
	}

	private void setMaximized(boolean maximized)
	{
		Platform.runLater(() ->
		{
			if (maximized)
			{
				maximize();
			}
			else
			{
				restore();
			}
		});

		this.maximized.set(maximized);
	}

	// Title Bar
	public void setMovable(AnchorPane title)
	{
        MoveListener listener = new MoveListener();

		// title.addEventHandler(MouseEvent.MOUSE_ENTERED, listener);
		title.addEventHandler(MouseEvent.MOUSE_PRESSED, listener);
		title.addEventHandler(MouseEvent.MOUSE_DRAGGED, listener);
		title.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
		title.addEventHandler(MouseEvent.MOUSE_RELEASED, listener);
		// title.addEventHandler(MouseEvent.MOUSE_EXITED, listener);
	}

	// Root Pane
	public void setResizable(BorderPane root)
	{
		ResizeListener listener = new ResizeListener();

		stage.addEventHandler(MouseEvent.MOUSE_MOVED, listener);
		stage.addEventHandler(MouseEvent.MOUSE_PRESSED, listener);
		stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, listener);
		stage.addEventHandler(MouseEvent.MOUSE_EXITED, listener);
	}

	private class MoveListener implements EventHandler<MouseEvent>
	{
		double initX;
		double initY;

		public MoveListener()
		{

		}

		@Override
		public void handle(MouseEvent event)
		{
			EventType<? extends MouseEvent> type = event.getEventType();

			if (MouseEvent.MOUSE_PRESSED.equals(type))
			{
				if (event.isPrimaryButtonDown())
				{
					initX = event.getScreenX();
					initY = event.getScreenY();

					event.consume();
				}
				else
				{
					initX = -1;
					initY = -1;
				}
			};

			if (MouseEvent.MOUSE_DRAGGED.equals(type))
			{
				if (!event.isPrimaryButtonDown())
				{
					return ;
				}

				if (event.isStillSincePress())
				{
					return ;
				}

				if (isMaximized())
				{
					stage.setX(event.getScreenX() - original.getWidth() / 2);
					stage.setY(0);
					stage.setWidth(original.getWidth());
					stage.setHeight(original.getHeight());

					// Left Screen
					if (stage.getX() < bounds.getMinX())
					{
						stage.setX(0);
					}
					// Right Screen
					if ((stage.getX() + original.getWidth()) > bounds.getMaxX())
					{
						stage.setX(bounds.getMaxX() - original.getWidth());
					}

					maximized.set(false);
				}

				double newX = event.getScreenX();
				double newY = event.getScreenY();

				double deltaX = newX - initX;
				double deltaY = newY - initY;

				initX = newX;
				initY = newY;

				stage.setX(stage.getX() + deltaX);
				stage.setY(stage.getY() + deltaY);

				event.consume();
			};

			if (MouseEvent.MOUSE_CLICKED.equals(type))
			{
				if (event.getClickCount() > 1)
				{
					setMaximized();
				}
			}

			if (MouseEvent.MOUSE_RELEASED.equals(type))
			{
				initX = -1;
				initY = -1;
			}
		}
	}

	public class ResizeListener implements EventHandler<MouseEvent>
	{
		private Cursor cursor = Cursor.DEFAULT;

		double startX = -1;
		double startY = -1;

		double initX = -1;
		double initY = -1;

		final double minW;
		final double minH;

		public ResizeListener()
		{
			minW = stage.getMinWidth();
			minH = stage.getMinHeight();
		}

		@Override
		public void handle(MouseEvent event)
		{
			if (isMaximized())
			{
				return ;
			}

			EventType<? extends MouseEvent> type = event.getEventType();

			double stageW = stage.getWidth();
			double stageH = stage.getHeight();

			double screenX = event.getScreenX();
			double screenY = event.getScreenY();

			double sceneX = event.getSceneX();
			double sceneY = event.getSceneY();

			double stageX = stage.getX();
			double stageY = stage.getY();

			double eventX = event.getX();
			double eventY = event.getY();

			if (MouseEvent.MOUSE_PRESSED.equals(type))
			{
				if (event.isPrimaryButtonDown())
				{
					initX = sceneX;
					initY = sceneY;

					startX = stageW - sceneX;
					startY = stageH - sceneY;
				}
			}

			if (MouseEvent.MOUSE_DRAGGED.equals(type))
			{
				if (!event.isPrimaryButtonDown())
				{
					return ;
				}

				if (initX == -1 || initY == -1)
				{
					return ;
				}

				if (Cursor.DEFAULT.equals(cursor))
				{
					return ;
				}

				if (!Cursor.W_RESIZE.equals(cursor) && !Cursor.E_RESIZE.equals(cursor))
				{
					if (Cursor.NW_RESIZE.equals(cursor) || Cursor.N_RESIZE.equals(cursor) || Cursor.NE_RESIZE.equals(cursor))
					{
						if (stageH > minH || sceneY < 0)
						{
							stage.setHeight(stageY - screenY + stageH + initY);
							stage.setY(screenY - initY);
						}
					}
					else
					{
						if (stageH > minH || sceneY + startY - stageH > 0)
						{
							stage.setHeight(sceneY + startY);
						}
					}
				}

				if (!Cursor.N_RESIZE.equals(cursor) && !Cursor.S_RESIZE.equals(cursor))
				{
					if (Cursor.NW_RESIZE.equals(cursor) || Cursor.W_RESIZE.equals(cursor) || Cursor.SW_RESIZE.equals(cursor))
					{
						if (stageW > minW || sceneX < 0)
						{
							stage.setWidth(stageX - screenX + stageW + initX);
							stage.setX(screenX - initX);
						}
					}
					else
					{
						if (stageW > minW || sceneX + startX - stageW > 0)
						{
							stage.setWidth(sceneX + startX);
						}
					}
				}
			}

			if (MouseEvent.MOUSE_MOVED.equals(type))
			{
				// Top Edge
				if (eventY >= 0 && eventY <= 5)
				{
					// Top Right
					if (eventX >= stageW - 10 && eventX <= stageW)
					{
						cursor = Cursor.NE_RESIZE;
					}
					// Top Left
					else if (eventX >= 0 && eventX <= 10)
					{
						cursor = Cursor.NW_RESIZE;
					}
					// Top
					else
					{
						cursor = Cursor.N_RESIZE;
					}
				}
				// Right Edge
				else if (eventX >= stageW - 5 && eventX <= stageW)
				{
					// Right Top
					if (eventY >= 0 && eventY <= 10)
					{
						cursor = Cursor.NE_RESIZE;
					}
					// Right Bottom
					else if (eventY >= stageH - 10 && eventY <= stageH)
					{
						cursor = Cursor.SE_RESIZE;
					}
					// Right
					else
					{
						cursor = Cursor.E_RESIZE;
					}
				}
				// Bottom
				else if (eventY >= stageH - 5 && eventY <= stageH)
				{
					// Bottom Right
					if (eventX >= stageW - 10 && eventX <= stageW)
					{
						cursor = Cursor.SE_RESIZE;
					}
					// Bottom Left
					else if (eventX >= 0 && eventX <= 10)
					{
						cursor = Cursor.SW_RESIZE;
					}
					// Bottom
					else
					{
						cursor = Cursor.S_RESIZE;
					}
				}
				// Left Edge
				else if (eventX >= 0 && eventX <= 5)
				{
					// Left Top
					if (eventY >= 0 && eventY <= 10)
					{
						cursor = Cursor.NW_RESIZE;
					}
					// Left Bottom
					else if (eventY >= stageH - 10 && eventY <= stageH)
					{
						cursor = Cursor.SW_RESIZE;
					}
					// Left
					else
					{
						cursor = Cursor.W_RESIZE;
					}
				}
				else
				{
					cursor = Cursor.DEFAULT;
				}

				stage.getScene().setCursor(cursor);
			}

			if (MouseEvent.MOUSE_EXITED.equals(type))
			{
				initX = -1;
				initY = -1;

				startX = -1;
				startY = -1;
			}
		}
	}
}