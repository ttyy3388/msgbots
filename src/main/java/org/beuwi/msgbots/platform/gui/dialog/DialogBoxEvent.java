package org.beuwi.msgbots.platform.gui.dialog;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DialogBoxEvent
{
	final Stage stage;

	public DialogBoxEvent(Stage stage)
	{
		this.stage = stage;
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

            if (MouseEvent.MOUSE_RELEASED.equals(type))
            {
                initX = -1;
                initY = -1;
            }
        }
    }
}
