package org.beuwi.msgbots.program.gui.skin;

import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.program.gui.layout.SplitPane;
import org.beuwi.msgbots.program.gui.layout.StackPane;

public class SplitPaneSkin extends javafx.scene.control.skin.SplitPaneSkin {
	private static final PseudoClass DRAG_PSEUDO_CLASS = PseudoClass.getPseudoClass("drag");
	private final StackPane leftPane = new StackPane();
	private final StackPane rightPane = new StackPane();
	private Node leftNode;
	private Node rightNode;
	private SplitPane control;
	private boolean horizontal;
	private double proportion = 0.5f;
	private double grabberWidth = 7;
	private Side side;
	private boolean toggleable;
	// private double toggleProp;
	private Pane grabberPane;
	private Pane dividerPane;
	// private final ToggleListener listener = new ToggleListener();;
	private double remindProp = -1;
	private boolean checkProportion = true;
	private double tryPropCount = 0;
	private boolean showDivider;
	private Pane emptyNode = new Pane(); // 토글할 때 임시로 삽입하는 노드, 크기가 없어야 함
	private boolean dragging;
	private boolean first = true;
	private double firstProp;
	private javafx.scene.control.SplitPane.Divider divider;
	public SplitPaneSkin(final SplitPane control) {
		super(control);
		this.control = control;

		// emptyNode.setMaxWidth(1);
		// emptyNode.setMaxHeight(1);
		control.getItems().setAll(
			leftPane,
			rightPane
		);
		SplitPane.setResizableWithParent(leftPane, false);
		SplitPane.setResizableWithParent(rightPane, false);
		SplitPane.setResizableWithParent(control, false);

		initialize();

		control.leftProperty().addListener(change -> initialize());
		control.rightProperty().addListener(change -> initialize());
		// control.proportionProperty().addListener(change -> initialize());
		control.sideProperty().addListener(change -> initialize());
		control.showDividerProperty().addListener(change -> initialize());
		control.getItems().addListener((ListChangeListener<? super Node>) change -> {
			// Max 2
			if (control.getItems().size() > 2) {
				throw new IllegalStateException(String.valueOf(3));
			}
		});
	}

	private boolean isFirstSide(Side side) {
		return side.equals(Side.TOP) || side.equals(Side.LEFT);
	}

	// Proportion을 설정해도, 너비 변경 시 JavaFX가 너비에 맞춰서 재조정함
	// 따라서 레이아웃을 적용한 후, 커스텀 비율을 적용함
	// 이러면 화면을 키우고, 줄이고, 노드를 키우든 줄이든 내가 설정한 비율에 맞춰 적용됨
	@Override
	protected void layoutChildren(final double x, final double y,
								  final double w, final double h) {
		// System.out.println("Layout Children : " + proportion);
		super.layoutChildren(x, y, w, h);
		// 사용자가 드래그 중일때는 커스텀 적용 X
		if (dragging) {
			return ;
		}
		double proportion = control.getProportion();
		/* if (control.getId() != null && control.getId().equals("TEST")) {
			System.out.println("Applied" + proportion);
		} */
		// Divider Proportion 적용
		if (isFirstSide(side)) {
			divider.setPosition(proportion);
		}
		else {
			divider.setPosition(1 - proportion);
		}
	}

	private void initialize() {
		horizontal = control.getOrientation().equals(Orientation.HORIZONTAL);
		leftNode = control.getLeft();
		rightNode = control.getRight();
		proportion = control.getProportion();
		side = control.getSide();
		toggleable = control.getToggleable();
		divider = control.getDivider();
		// 왼쪽 및 오른쪽 노드는 필수값
		if (leftNode == null || rightNode == null) {
			return ;
		}
		// 사이드 또한 필수값
		if (side == null) {
			return ;
		}
		leftPane.getChildren().setAll(leftNode);
		rightPane.getChildren().setAll(rightNode);

		// Divider Proportion 적용
		/* if (isFirstSide(side)) {
			control.getDivider().setPosition(proportion);
		}
		else {
			control.getDivider().setPosition(1 - proportion);
		} */

		// Divider 및 Grabber는 노드가 추가돼야 같이 추가되므로 getItems.addAll 이후에 추가
		if (dividerPane == null) {
			dividerPane = (Pane) control.lookup(".split-pane-divider");
			dividerPane.getStyleClass().add("divider");
			dividerPane.setPrefWidth(1);

			// Prop값이 변경되는 건 사용자가 직접 prop값을 변경할 때, 드래그할 때 두가지
			// 즉 사용자가 드래그를 해도 값을 지정한 것으로 봄
			dividerPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
				if (isFirstSide(side)) {
					control.setProportion(divider.getPosition());
					// System.out.println(control.getProportion());
				}
				else {
					control.setProportion(1 - divider.getPosition());
				}
				// System.out.println("Mouse Dragged : " + control.getDivider().getPosition());
			});
			// 드래그 시작(마우스 누르면)하면
			dividerPane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
				dragging = true;
				dividerPane.pseudoClassStateChanged(DRAG_PSEUDO_CLASS, true);
			});
			// 마우스 버튼 놓으면
			dividerPane.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
				dividerPane.pseudoClassStateChanged(DRAG_PSEUDO_CLASS, false);
				dragging = false;
			});
		}

		// 노드가 변경될 때마다 디비더가 바뀌는 것 같음
		// System.out.println(divider + " : " + grabber);
		// Divider를 대상으로 너비를 조정하면, 테두리도 넓어지므로 Grabber만 수정
		if (grabberPane == null) {
			grabberPane = (Pane) (horizontal ? control.lookup(".horizontal-grabber") : control.lookup(".vertical-grabber"));
			if (horizontal) grabberPane.setPrefWidth(grabberWidth);
			else grabberPane.setPrefHeight(grabberWidth);
		}

		// 배경색을 제거함
		showDivider = control.isShowDivider();
		if (!showDivider) {
			dividerPane.setPrefWidth(0); // 너비 제거
		}
		else {
			dividerPane.setPrefWidth(1); // 너비 삽입
		}

		/* if (toggleable) {
			dividerPane.addEventHandler(MouseEvent.ANY, listener);
		}
		else {
			dividerPane.removeEventHandler(MouseEvent.ANY, listener);
		} */
	}

	// show가 true면 추가하고, false면 숨김
	/* private void toggleNode(boolean show) {
		Side side = control.getSide();
		Pane targetPane = isFirstSide(side) ?  leftPane : rightPane;
		ObservableList<Node> list = targetPane.getChildren();
		Node targetNode = isFirstSide(side) ? leftNode : rightNode;

		if (!show && list.contains(targetNode)) {
			targetPane.getChildren().setAll(emptyNode);
			targetPane.setMaxWidth(0); // 그냥 targetPane을 없애버리면 divider도 사라지기 때문에 남기기 위해서 사이즈만 없앰
			targetPane.setMaxHeight(0);
		}
		else if (show && !list.contains(targetNode)){
			targetPane.getChildren().setAll(targetNode);
			// control.getDivider().setPosition(proportion);
			targetPane.setMaxWidth(-1); // 사이즈 롤백
			targetPane.setMaxHeight(-1);
		}
	} */

	/* private void toggleNode() {
		Side side = control.getSide();
		Pane targetPane = isFirstSide(side) ?  leftPane : rightPane;
		ObservableList<Node> list = targetPane.getChildren();
		Node targetNode = isFirstSide(side) ? leftNode : rightNode;

		if (list.contains(targetNode)) {
			targetPane.getChildren().setAll(emptyNode);
			targetPane.setMaxWidth(0); // 그냥 targetPane을 없애버리면 divider도 사라지기 때문에 남기기 위해서 사이즈만 없앰
			targetPane.setMaxHeight(0);
		}
		else {
			targetPane.getChildren().setAll(targetNode);
			// control.getDivider().setPosition(proportion);
			targetPane.setMaxWidth(-1); // 사이즈 롤백
			targetPane.setMaxHeight(-1);
		}
	} */
	// 토글 관련 기능은 비율이 아닌 사이즈로 계산함
	/* private class ToggleListener implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			Side side = control.getSide();
			EventType<? extends MouseEvent> type = event.getEventType();
			if (MouseEvent.MOUSE_CLICKED.equals(type)) {
				System.out.println(event.getClickCount());
				if (event.getClickCount() >= 2) {
					toggleNode();
				}
			}
			if (MouseEvent.MOUSE_PRESSED.equals(type)) {
			}
			if (MouseEvent.MOUSE_DRAGGED.equals(type)) {
				Pane targetPane = null;
				Node targetNode = null;
				if (isFirstSide(side)) {
					targetPane = leftPane;
					targetNode = leftNode;
				}
				else {
					targetPane = rightPane;
					targetNode = rightNode;
				}

				// 혹시 모를 대비
				if (targetPane == null || targetNode == null) {
					return ;
				}

			    Point2D point = targetPane.localToScene(0, 0); // 패널의 좌표를 가져옴
				// 바로 getSceneX를 안하고 패널의 좌표 값을 먼저 구하는 이유는
				// getSceneX는 말 그대로 "Scene을 기준"으로 좌표 계산이 이루어지므로, 메뉴바, 상태바 등 다른 뷰까지 포함되기 때문임
				// 따라서 해당 "패널를 기준"으로 좌표 값만 구하기 위해 아래와 같이 계산함
				double mouseValue = horizontal ? (event.getSceneX() - point.getX()) : (event.getSceneY() - point.getY());
				// 예를 들어 X가 0이 나와야 하나, getSceneX으로 하면 200값이 나올 수 있음
				// System.out.println("[Point: " + point + ", X: " + (event.getSceneX() - point.getX()) + ", Y: " + (event.getSceneY() - point.getY()) + "]");
				double minSize = horizontal ? targetNode.minWidth(-1) : targetNode.minHeight(-1); // 최소 너비를 가져옴
				double centerValue = minSize / 2; // 최소 너비의 중간을 기준으로 잡음 (중앙 이하로 줄어들면 숨기고, 아니면 나타내고)

				System.out.println("[Text Value: " + (control.getScene().getHeight() - point.getY() - event.getSceneY()) + ", Point X:" + point.getX() + ", Point Y:" + point.getY() + ", Mouse Value: " + mouseValue + ", Min Size: " + minSize + ", Center Value: " + centerValue + "]");
				// 마우스 값이 중앙 값보다 크면 나타냄
				if (centerValue <= mouseValue) {
					System.out.println("Show");
				}
				// 마우스 값이 중앙 값보다 작으면 숨김
				else if (centerValue > mouseValue) {
					System.out.println("Hide");
				}
			}
		}
	} */
}
