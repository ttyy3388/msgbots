package org.beuwi.msgbots.platform.gui.skin;

import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.platform.gui.control.SplitView;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

import java.util.List;

public class SplitViewSkin extends javafx.scene.control.skin.SplitPaneSkin {
	private static final PseudoClass DRAG_PSEUDO_CLASS = PseudoClass.getPseudoClass("drag");

	private final SplitView control;
	private final StackPane leftPane = new StackPane();
	private final StackPane rightPane = new StackPane();

	private Pane grabberPane;
	private Pane dividerPane;
	private Node leftNode;
	private Node rightNode;

	private Side side;

	private double proportion = 0.5f; // half
	private double grabberWidth = 7;

	private boolean toggleable;
	private boolean horizontal;
	private boolean dragging;

	private javafx.scene.control.SplitPane.Divider divider;

	public SplitViewSkin(final SplitView control) {
		super(control);

		this.control = control;
		control.getItems().setAll(
			leftPane, rightPane
		);

		initialize();

		control.leftProperty().addListener(change -> initialize());
		control.rightProperty().addListener(change -> initialize());
		// control.proportionProperty().addListener(change -> initialize());
		control.sideProperty().addListener(change -> initialize());

		control.getItems().addListener((ListChangeListener<? super Node>) change -> {
			List<Node> list = control.getItems();
			if (list.size() > 2) {
				throw new IllegalStateException(String.valueOf(3));
			}
			// setLeft(), setRight()로 추가한 게 아닌,
			// getItems().add()로 추가했을 경우는 생각하지 않음
			// 따라서 해당 블록에서는 initialize()를 호출하지 않음
			// else initialize();
		});

		SplitView.setResizableWithParent(leftPane, false);
		SplitView.setResizableWithParent(rightPane, false);
		SplitView.setResizableWithParent(control, false);
	}

	/* private void rearrange() { } */

	private boolean isFirstSide(Side side) {
		return side.equals(Side.TOP) || side.equals(Side.LEFT);
	}

	// Proportion을 설정해도, 너비 변경 시 JavaFX가 너비에 맞춰서 재조정함
	// 따라서 레이아웃을 적용한 후, 커스텀 비율을 적용함
	// 이러면 화면을 키우고, 줄이고, 노드를 키우든 줄이든 내가 설정한 비율에 맞춰 적용됨
	@Override
	protected void layoutChildren(final double x, final double y,
								  final double w, final double h) {
		super.layoutChildren(x, y, w, h);
		// 사용자가 드래그 중일때는 커스텀 적용 X
		if (dragging) {
			return ;
		}

		double proportion = control.getProportion();
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

		side = control.getSide();
		// 필수 값이 지정이 안됐다면 작동 X
		if (side == null) {
			return ;
		}

		leftNode = control.getLeft();
		rightNode = control.getRight();
		// 필수 값이 지정이 안됐다면 작동 X
		if (leftNode == null ||
			rightNode == null) {
			return ;
		}

		leftPane.getChildren().setAll(leftNode);
		rightPane.getChildren().setAll(rightNode);

		proportion = control.getProportion();
		toggleable = control.getToggleable();
		divider = control.getDivider();

		// Divider 및 Grabber는 노드가 추가돼야 같이 추가되므로 'getItems().addAll' 이후에 추가
		if (dividerPane == null) {
			dividerPane = (Pane) control.lookup(".split-pane-divider");
			dividerPane.getStyleClass().add("divider");
			dividerPane.setPrefWidth(1);

			// Prop값이 변경되는 건 사용자가 직접 prop 값을 변경할 때, 드래그할 때 두가지
			// 즉 사용자가 드래그를 해도 값을 prop 값을 지정한 것으로 봄
			dividerPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
				double position = divider.getPosition();
				if (isFirstSide(side)) {
					control.setProportion(position);
				}
				else {
					control.setProportion(1 - position);
				}
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

		// 노드가 변경될 때마다 Divider가 바뀌는 것 같음
		// Divider를 대상으로 너비를 조정하면, 테두리도 넓어지므로 Grabber만 수정
		if (grabberPane == null) {
			if (horizontal) {
				grabberPane = (Pane) control.lookup(".horizontal-grabber");
				grabberPane.setPrefWidth(grabberWidth);
			}
			else {
				grabberPane = (Pane) control.lookup(".vertical-grabber");
				grabberPane.setPrefHeight(grabberWidth);
			}
		}
	}
}
