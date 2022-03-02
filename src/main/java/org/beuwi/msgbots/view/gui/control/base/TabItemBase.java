package org.beuwi.msgbots.view.gui.control.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.beuwi.msgbots.view.gui.base.Control;
import org.beuwi.msgbots.view.gui.layout.StackPane;

// 기본적으로 클래스 자체가 [Header]이며, [Content]를 포함하는 형태임
public abstract class TabItemBase extends StackPane implements Control {
	public TabItemBase() {
		addChangeListener("header", change -> {
			initChildren(getHeader());
		});

		addChangeListener("selected", change -> {
			setPseudoClass("selected", isSelected());
		});

		addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			// 좌측 마우스 클릭 시
			MouseButton button = event.getButton();
			if (button.equals(MouseButton.PRIMARY)) {
				TabViewBase view = getView();
				if (view != null) {
					view.selectTab(this);
				}
			}
		});
	}

	private final ObjectProperty<TabViewBase> viewProperty = new SimpleObjectProperty(null);
	public final ReadOnlyObjectProperty<TabViewBase> viewProperty() {
		return viewProperty;
	}
	public final void setView(TabViewBase view) {
		viewProperty.set(view);
	}
	public final TabViewBase getView() {
		return viewProperty.get();
	}

	// Tab item header
	private final ObjectProperty<Node> headerProperty = new SimpleObjectProperty(null);
	public final ReadOnlyObjectProperty<Node> headerProperty() {
		return headerProperty;
	}
	public final void setHeader(Node header) {
		headerProperty.set(header);
	}
	public final Node getHeader() {
		return headerProperty.get();
	}

	// Tab item content
	private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty(null);
	public final ReadOnlyObjectProperty<Node> contentProperty() {
		return contentProperty;
	}
	public final void setContent(Node content) {
		this.contentProperty.set(content);
	}
	public final Node getContent() {
		return contentProperty.get();
	}

	/*
	 * 해당 함수는 내부에서만 접근 가능함
	 * 탭이 선택된지, 안됐는지를 지정하기 위한 함수이므로, [selectTab]과는 다른 개념임
	 * 따라서 외부에서는 접근 여부를 알기 위한 [isSelected]만 접근이 가능함
	 */
	private final BooleanProperty selectedProperty = new SimpleBooleanProperty(false);
	public final ReadOnlyBooleanProperty selectedProperty() {
		return selectedProperty;
	}
	protected void setSelected(boolean selected) {
		selectedProperty.set(selected);
	}
	// 외부에서는 [isSelected]만 접근이 가능함
	public boolean isSelected() {
		return selectedProperty.get();
	}

	private final BooleanProperty closableProperty = new SimpleBooleanProperty(true);
	public final ReadOnlyBooleanProperty closableProperty() {
		return closableProperty;
	}
	public final void setClosable(boolean closable) {
		this.closableProperty.set(closable);
	}
	public final boolean isClosable() {
		return closableProperty.get();
	}
}
