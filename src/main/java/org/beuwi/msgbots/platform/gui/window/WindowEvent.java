package org.beuwi.msgbots.platform.gui.window;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowEvent {
	final Stage stage;

	public WindowEvent(Stage stage) {
		this.stage = stage;
	}

	/* public void setResizable(Pane pane) {
        ResizeListener listener = new ResizeListener();

        // pane.addEventHandler(MouseEvent.MOUSE_ENTERED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_RELEASED, listener);
        // titlebar.addEventHandler(MouseEvent.MOUSE_EXITED, listener);
    } */

    public void setMovable(Pane pane) {
        MoveListener listener = new MoveListener();

        // pane.addEventHandler(MouseEvent.MOUSE_ENTERED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
        pane.addEventHandler(MouseEvent.MOUSE_RELEASED, listener);
        // titlebar.addEventHandler(MouseEvent.MOUSE_EXITED, listener);
    }

    private class MoveListener implements EventHandler<MouseEvent> {
        double initX;
        double initY;

        public MoveListener() {
        }

        @Override
        public void handle(MouseEvent event) {
            EventType<? extends MouseEvent> type = event.getEventType();

            if (MouseEvent.MOUSE_PRESSED.equals(type)) {
                if (event.isPrimaryButtonDown()) {
                    initX = event.getScreenX();
                    initY = event.getScreenY();
                    event.consume();
                }
                else {
                    initX = -1;
                    initY = -1;
                }
            };

            if (MouseEvent.MOUSE_DRAGGED.equals(type)) {
                if (!event.isPrimaryButtonDown()) {
                    return ;
                }
                if (event.isStillSincePress()) {
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

            if (MouseEvent.MOUSE_RELEASED.equals(type)) {
                initX = -1;
                initY = -1;
            }
        }
    }
}
