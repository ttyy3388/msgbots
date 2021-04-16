package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.skin.SplitPaneSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class ResizePane extends AnchorPane {

	private final ObjectProperty<Side> typeProperty = new SimpleObjectProperty(null);

	private final Pane resizebar = new Pane();

	private Stage stage;
	private Scene scene;

	private double startX;
	private double startY;
	// private double startW;
	// private double startH;

	public ResizePane() {
        typeProperty().addListener(change -> {
            Side type = getType();
            if (type == null) {
                return;
            }

            switch (getType()) {
                case TOP :
                    resizebar.setCursor(Cursor.N_RESIZE);
                    resizebar.setPrefHeight(5.0);
                    setLeftAnchor(resizebar, 0.0);
                    setRightAnchor(resizebar, 0.0);
                    setTopAnchor(resizebar, 0.0); break;
                case BOTTOM :
                    resizebar.setCursor(Cursor.S_RESIZE);
                    resizebar.setPrefHeight(5.0);
                    setLeftAnchor(resizebar, 0.0);
                    setRightAnchor(resizebar, 0.0);
                    setBottomAnchor(resizebar, 0.0); break;
                case LEFT :
                    resizebar.setCursor(Cursor.E_RESIZE);
                    resizebar.setPrefWidth(5.0);
                    setTopAnchor(resizebar, 0.0);
                    setBottomAnchor(resizebar, 0.0);
					setLeftAnchor(resizebar, 0.0); break;
                case RIGHT :
                    resizebar.setCursor(Cursor.W_RESIZE);
                    resizebar.setPrefWidth(5.0);
                    setTopAnchor(resizebar, 0.0);
                    setBottomAnchor(resizebar, 0.0);
					setRightAnchor(resizebar, 0.0); break;
            }
        });

		getChildren().addListener((ListChangeListener<Node>) change -> {
			List<Node> list = getChildren();
			int size = list.size();

			if (size > 0) {
				Node added = list.get(size - 1); // Last Item
				// 추가된 아이템이 리사이즈 바 라면
				if (added.equals(resizebar)) {
					// 무한 반복되므로 제외함
					return ;
				}
				// 리사이즈 바가 이미 추가되어 있다면
				if (list.contains(resizebar)) {
					// 리사이즈 바는 마지막에 추가하도록 제거함
					// 즉 중간에 끼어있을 경우에만 감지됨
					list.remove(resizebar);
				}
				else {
					list.add(resizebar);
				}
			}
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

		resizebar.setOnMousePressed(event -> {
			startX = event.getSceneX();
			startY = event.getSceneY();
			// startW = this.getWidth();
			// startH = this.getHeight();
        });
		resizebar.setOnMouseDragged(event -> {
			if (event.isConsumed() ||
				stage == null ||
				scene == null) {
				return ;
			}
			// SceneY를 컨트롤 높이와 부수적인 요소(*)의 합으로 봄
			// (*) : 예를 들어 "ToolArea"의 경우 상태 바의 높이까지 포함해서 계산해야 하는데 SceneY을 통해 계산하면 바로 나오기 때문
			double sceneX =  event.getSceneX();
			double sceneY =  event.getSceneY();

			double deltaX = startX - sceneX;
			double deltaY = startY - sceneY;

			double minW = getMinWidth();
			double minH = getMinHeight();
			double prefW = getPrefWidth();
			double prefH = getPrefHeight();

			// 아래 체크 방법 폐기
			// 모니터 밖이 아니라 그냥 리사이즈 바 기준으로 프로그램 내부 감지하면 될듯
			// 화면 밖에 나가있는지 체크
			/* scene.getX() : 모니터 좌측부터 프로그램 좌측(시작)까지의 길이
			 * 따라서 위 보다 값이 낮다면 화면 좌측에 있는거임
			 */
			/* if (scene.getX() > event.getSceneX()) {
				return ;
			} */
			/* scene.getY() : 모니터 상단부터 프로그램 상단(시작)까지의 높이
			 * 따라서 위 보다 값이 낮다면 화면 상단에 있는거임
			 */
			/* if (scene.getY() > event.getScreenY()) {
				return ;
			} */
			/* scene.getX() : 모니터 좌측부터 프로그램 좌측(시작)까지의 길이
			 * scene.getWidth() : 프로그램 길이
			 * scene.getX() + scene.getWidth() : 모니터 좌측부터 프로그램 우측 까지의 길이
			 * 따라서 위 보다 값이 높다면 화면 우측에 있는거임
			 */
			/* if ((scene.getX() + scene.getWidth()) < event.getSceneX()) {
				return ;
			} */
			/* scene.getY() : 모니터 상단부터 프로그램 상단(시작)까지의 높이
			 * scene.getHeight() : 프로그램 높이
			 * scene.getY() + scene.getHeight() : 모니터 상단부터 프로그램 밖 까지의 높이
			 * 따라서 위 보다 값이 높다면 화면 하단에 있는거임
			 */
			/* if ((scene.getY() + scene.getHeight()) < event.getScreenY()) {
				return ;
			} */

			// 문제점 1 : 해당 컴포넌트의 원래 높이의 이하로 조절 시 확 내려가버림 : JavaFX GridPane 버그 같음 -> 다른 패널로 교채해서 해결
			// 문제점 2 : 화면 밖에 나갔다가 들어오면 리사이즈바를 기준으로 또 갱신해야됨

			Side type = getType();
			if (type == null) {
				return;
			}

			// 그냥 getLayoutX()를 호출하면 직계부모의 상대 위치만 가져오므로 의미없는 값을 반환함
			// 따라서 씬의 상대 위치를 가져오기 위해서 "sceneToLocal"함수를 이용
			// 또한 왜인지는 모르겠으나 (-)음수 값을 반환하므로 부호를 변환시켜 (+)양수로 계산
			Bounds bounds = resizebar.sceneToLocal(
					resizebar.getLayoutBounds());
			double layoutX = -(bounds.getMinX());
			double layoutY = -(bounds.getMinY());

			System.out.println(
				"[StartY]:" + startY + " " +
				"[SceneX]:" + sceneX + " " +
				"[SceneY]:" + sceneY + " " +
				// "[LayoutX]:" + layoutX + " " +
				// "[LayoutY]:" + layoutY + " " +
				// "[StageX]:" + stage.getX() + " " +
				// "[StageY]:" + stage.getY() + " " +
				// "[SngetX]:" + scene.getX() + " " +
				// "[SngetY]:" + scene.getY() + " " +
				// "[ScreenX]:" + event.getScreenX() + " " +
				// "[ScreenY]:" + event.getScreenY() + " " +
				"[eventX]:" + event.getX() + " " +
				"[eventY]:" + event.getY() + " " +
				"[DeltaX]:" + deltaX + " " +
				"[DeltaY]:" + deltaY);
			/* // 드래그하고 있는 마우스의 노드 : getTarget();
			// 드래그하고 있는 마우스의 위치에 있는 노드 : getPickResult();
			Node pickResult = event.getPickResult().getIntersectedNode();
			if (pickResult != resizebar) {
				return ;
			} */
			/*
				System.out.println(
					"[Width]:" + getWidth() +
					"[PrefW]:" + getPrefWidth() +
					"[MinW]:" + getMinWidth() +
					"[MaxW]:" + getMaxHeight() +
					"[SceneX]:" + sceneX); */

			// LEFT OR RIGHT
			if (type.isVertical()) {
				// 해당 범위 안의 드래그만 반영되되도록(오차범위 +-10)
				/* if (!((sceneX + 10) >= layoutX) ||
					!((sceneX - 10) <= layoutX)) {
					return ;
				} */

				// To Left
				if (type.equals(Side.LEFT)) {
					double value = prefW + deltaX;
					if (minW < value)
						setPrefWidth(value);
				}
				// To Right
				else {
					double value = prefW - deltaX;
					if (minW < value)
						setPrefWidth(value);
				}
			}
			// LEFT OR RIGHT
			else {
				// 해당 범위 안의 드래그만 반영되되도록(오차범위 +-10)
				/* if (!((sceneY + 10) >= layoutY) ||
					!((sceneY - 10) <= layoutY)) {
					return ;
				} */

				// To Top
				if (type.equals(Side.TOP)) {
					double value = prefH + deltaY;
					if (minH < value)
						setPrefHeight(value);
				}
				// To Bottom
				else {
					double value = prefH - deltaY;
					if (minH < value)
						setPrefHeight(value);
				}
			}

			// 마지막을 값을 업데이트해야 정상적으로 올라감
			// 안하면 (+17, -21)씩 올라가니 확 올라가고 확 내려감
			// 즉 값을 업데이트 해서 (+1, +2)씩 올라가게 만듦
			startX = sceneX;
			startY = sceneY;
		});
        resizebar.getStyleClass().add("resize-bar");
	}

	// 리사이즈 바가 어디에 위치할 건지에 대한 타입임 즉 방향성을 띔
	// EX : TOP(위쪽으로 리사이즈) RIGHT(우측으로 리사이즈)
	public ObjectProperty<Side> typeProperty() {
		return typeProperty;
	}
	public Side getType() {
		return typeProperty.get();
	}
	public void setType(Side type) {
		typeProperty.set(type);
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
