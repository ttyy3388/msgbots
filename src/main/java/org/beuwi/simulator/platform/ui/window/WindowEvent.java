package org.beuwi.simulator.platform.ui.window;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WindowEvent
{
	// Maximize
	// Minimize
	// Close
	// Restore
	// Movable
	// Resizable

	final Stage stage;

	public WindowEvent(Stage stage)
	{
		this.stage = stage;
	}

	public void setWindowClose()
	{
		System.exit(0);
	}

	// Node : Title Bar
	public void setWindowMovable(Node node)
	{
		MoveListener listener = new MoveListener(stage, node);

		node.addEventHandler(MouseEvent.MOUSE_PRESSED, listener);
		node.addEventHandler(MouseEvent.MOUSE_DRAGGED, listener);
	}

	public void setWindowResizable()
	{
		ResizeListener listener = new ResizeListener(stage);

		stage.addEventHandler(MouseEvent.MOUSE_MOVED, listener);
		stage.addEventHandler(MouseEvent.MOUSE_PRESSED, listener);
		stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, listener);
	}

	private static class MoveListener implements EventHandler<MouseEvent>
	{
		final Stage stage;
		final Node node;

		double initX = 0;
		double initY = 0;

		public MoveListener(Stage stage, Node node)
		{
			this.stage = stage;
			this.node = node;
		}

		@Override
		public void handle(MouseEvent event)
		{
			EventType<? extends MouseEvent> type = event.getEventType();

			if (MouseEvent.MOUSE_PRESSED.equals(type))
			{
				if (event.isPrimaryButtonDown())
				{
					initX = event.getSceneX();
					initY = event.getSceneY();

					event.consume();
				}
			};

			if (MouseEvent.MOUSE_DRAGGED.equals(type))
			{
				if (event.isPrimaryButtonDown())
				{
					stage.setX(event.getScreenX() - initX);
					stage.setY(event.getScreenY() - initY);

					event.consume();
				}
			};
		}
	}

	private static class ResizeListener implements EventHandler<MouseEvent>
	{
		private Stage stage;

		private Cursor cursor = Cursor.DEFAULT;

		double startX = 0;
		double startY = 0;

		double initX = 0;
		double initY = 0;

		double minW = 0;
		double minH = 0;

		public ResizeListener(Stage stage)
		{
			this.stage = stage;

			minW = stage.getMinWidth();
			minH = stage.getMinHeight();
		}

		@Override
		public void handle(MouseEvent event)
		{
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

			if (stage.isMaximized())
			{
				return ;
			}

			if (MouseEvent.MOUSE_PRESSED.equals(type))
			{
				initX = sceneX;
				initY = sceneY;

				startX = stageW - sceneX - 10;
				startY = stageH - sceneY - 10;
			}

			if (MouseEvent.MOUSE_DRAGGED.equals(type))
			{
				System.out.println(screenX + ":" + stageX);
				if (!event.isPrimaryButtonDown() || Cursor.DEFAULT.equals(cursor))
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
							stage.setHeight(sceneY + startY + 10);
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
							stage.setWidth(sceneX + startX + 10);
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
		}
	}
}