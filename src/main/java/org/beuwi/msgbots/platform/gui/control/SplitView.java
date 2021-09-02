package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Skin;

import org.beuwi.msgbots.platform.gui.skin.SplitViewSkin;

// 원래 SplitPane은 다중 분할을 위해 만들어 졌으나,
// Intellij Splitter처럼 left, right 두개의 노드를 위한 Pane로 개조함
// 따라서 Divider는 1개만 존재함
public class SplitView extends javafx.scene.control.SplitPane {
	public SplitView() {
	}

	public Divider getDivider() {
		return getDividers().get(0);
	}

	public boolean isVertical() {
	    Orientation orientation = getOrientation();
	    if (orientation != null) {
	        return orientation.equals(Orientation.VERTICAL);
        }
	    // throw error
	    return false;
    }

	/* private final ObjectProperty<Orientation> orientationProperty = new SimpleObjectProperty<>(Orientation.HORIZONTAL);
	public final void setOrientation(Orientation orientation) {
		orientationProperty.set(orientation);
	}
	public final Orientation getOrientation() {
		return orientationProperty.get();
	}
	public final ObjectProperty<Orientation> orientationProperty() {
		return orientationProperty;
	} */

	// 메인 노드가 있는 위치 (Left or Right)
	// 메인 노드라 함은, 리사이즈의 주체가 되는 노드를 뜻함
	// 토글 이벤트 등 모든 이벤트가 이 메인 노드를 중심으로 작동함
	private final ObjectProperty<Side> sideProperty = new SimpleObjectProperty<>(Side.LEFT);
	public final void setSide(Side side) {
		sideProperty.set(side);
	}
	public final Side getSide() {
		return sideProperty.get();
	}
	public final ObjectProperty<Side> sideProperty() {
		return sideProperty;
	}

	private final ObjectProperty<Node> leftProperty = new SimpleObjectProperty<>(null);
	public final void setLeft(Node left) {
		leftProperty.set(left);
	}
	public final Node getLeft() {
		return leftProperty.get();
	}
	public final ObjectProperty<Node> leftProperty() {
		return leftProperty;
	}

	private final ObjectProperty<Node> rightProperty = new SimpleObjectProperty<>(null);
	public final void setRight(Node node) {
		rightProperty.set(node);
	}
	public final Node getRight() {
		return rightProperty.get();
	}
	public final ObjectProperty<Node> rightProperty() {
		return rightProperty;
	}

	// 사이즈가 반 이하로 줄어들면 토글 기능을 사용하도록
	// 토글 관련 기능은 비율이 아닌 사이즈로 계산함
	private final BooleanProperty toggleableProperty = new SimpleBooleanProperty(true);
	public boolean getToggleable() {
		return toggleableProperty.get();
	}
	public void setToggleable(boolean toggleable) {
		toggleableProperty.set(toggleable);
	}
	public final BooleanProperty toggleableProperty() {
		return toggleableProperty;
	}

	// 지정한 사이드(왼쪽, 오른쪽) 노드의 중심 비율
	// 해당 값이 변경되는 경우는 두 가지임.
	// 직접 지정할 때, 마우스로 변경할 때만 값이 변경됨 (!= JavaFX.position)
	private final DoubleProperty proportionProperty = new SimpleDoubleProperty(0.5f);
	// 최소값으로 시작하려면 0을 입력하면 됨
	public double getProportion() {
		return proportionProperty.get();
	}
	public void setProportion(double proportion) {
		if (getProportion() == proportion) {
			return ;
		}
		if (proportion < .0f || proportion > 1.0f) {
			throw new RuntimeException("Wrong proportion");
		}
		proportionProperty.set(proportion);
	}
	public DoubleProperty proportionProperty() {
		return proportionProperty;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new SplitViewSkin(this);
	}
}
