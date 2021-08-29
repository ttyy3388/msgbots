package org.beuwi.msgbots.program.gui.layout;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

// SplitPane 구현 후 쓰지 않을 예정임
public class ResizePane extends AnchorPane {
	private static final PseudoClass DRAG_PSEUDO = PseudoClass.getPseudoClass("drag");

	// 어느 방향으로 리사이즈를 할 건지
	private final ObjectProperty<Side> sideProperty = new SimpleObjectProperty(null);
	private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty(null);

	private final StackPane contentArea = new StackPane();
	private final Pane resizeBar = new Pane();

	private Stage stage;
	private Scene scene;

	private double startX;
	private double startY;
	// private double startW;
	// private double startH;

	public ResizePane() {
		contentProperty().addListener(change -> {
			Node content = getContent();
			if (content != null) {
				contentArea.getChildren().setAll(content);
			}
		});

        sideProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }

            switch (newValue) {
                case TOP :
                    resizeBar.setCursor(Cursor.N_RESIZE);
                    resizeBar.setPrefHeight(5.0);
					AnchorPane.setLeftAnchor(resizeBar, 0.0);
					AnchorPane.setRightAnchor(resizeBar, 0.0);
					AnchorPane.setTopAnchor(resizeBar, 0.0); break;
                case BOTTOM :
                    resizeBar.setCursor(Cursor.S_RESIZE);
                    resizeBar.setPrefHeight(5.0);
					AnchorPane.setLeftAnchor(resizeBar, 0.0);
					AnchorPane.setRightAnchor(resizeBar, 0.0);
					AnchorPane.setBottomAnchor(resizeBar, 0.0); break;
                case LEFT :
                    resizeBar.setCursor(Cursor.E_RESIZE);
                    resizeBar.setPrefWidth(5.0);
					AnchorPane.setTopAnchor(resizeBar, 0.0);
					AnchorPane.setBottomAnchor(resizeBar, 0.0);
					AnchorPane.setLeftAnchor(resizeBar, 0.0); break;
                case RIGHT :
                    resizeBar.setCursor(Cursor.W_RESIZE);
                    resizeBar.setPrefWidth(5.0);
					AnchorPane.setTopAnchor(resizeBar, 0.0);
					AnchorPane.setBottomAnchor(resizeBar, 0.0);
					AnchorPane.setRightAnchor(resizeBar, 0.0); break;
            }
            if (oldValue != null) {
				resizeBar.pseudoClassStateChanged(PseudoClass.getPseudoClass(oldValue.toString().toLowerCase()), false);
			}
			resizeBar.pseudoClassStateChanged(PseudoClass.getPseudoClass(newValue.toString().toLowerCase()), true);
        });

		this.sceneProperty().addListener(c1 -> {
			Scene scene = getScene();
			if (scene != null) {
				this.scene = scene;
				scene.windowProperty().addListener(c2 -> {
					Window window = scene.getWindow();
					if (window != null) {
						this.stage = (Stage) window;
					}
				});
			}
		});

		resizeBar.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			if (event.isConsumed() ||
				/* stage == null || */
				scene == null) {
				return ;
			}
			Side side = getSide();
			if (side == null) {
				return;
			}

			double sceneW = scene.getWidth();
			double sceneH = scene.getHeight();
			double sceneX = event.getSceneX();
			double sceneY = event.getSceneY();

			switch (side) {
				case LEFT -> setPrefWidth(sceneW - sceneX);
				case RIGHT -> setPrefWidth(sceneX);
				case TOP -> setPrefHeight(sceneH - sceneY);
				case BOTTOM -> setPrefHeight(sceneY);
			}
		});

		// 드래그 시작하면
		resizeBar.addEventHandler(MouseEvent.MOUSE_DRAGGED, event ->{
			resizeBar.pseudoClassStateChanged(DRAG_PSEUDO, true);
		});
		// 마우스 버튼 놓으면
		resizeBar.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
			resizeBar.pseudoClassStateChanged(DRAG_PSEUDO, false);
		});

		AnchorPane.setFitAnchor(contentArea);
		resizeBar.getStyleClass().add("resize-bar");
        getChildren().setAll(contentArea, resizeBar);
	}

	public Pane getResizeBar() {
		return resizeBar;
	}

	// 리사이즈 바가 어디에 위치할 건지에 대한 타입임 즉 방향성을 띔
	// EX : TOP(위쪽으로 리사이즈) RIGHT(우측으로 리사이즈)
	public final ObjectProperty<Side> sideProperty() {
		return sideProperty;
	}
	public final Side getSide() {
		return sideProperty.get();
	}
	public final void setSide(Side side) {
		sideProperty.set(side);
	}

	public final ObjectProperty<Node> contentProperty() {
		return contentProperty;
	}
	public final Node getContent() {
		return contentProperty.get();
	}
	public final void setContent(Node content) {
		contentProperty.set(content);
	}

	/* private class ResizeBar extends Pane {
		public ResizeBar() {
		}
	}
	/* private class ResizeListener implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
		}
	} */
}
