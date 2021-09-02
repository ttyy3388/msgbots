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

import org.beuwi.msgbots.view.gui.layout.StackPane;

// 기본적으로 클래스 자체가 [Header]이며, [Content]를 포함하는 형태임
public abstract class TabItemBase<T extends TabViewBase> extends StackPane {
	public TabItemBase() {
		headerProperty().addListener(change -> {
			getChildren().setAll(getHeader());
		});

		addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			// 좌측 마우스 클릭 시
			MouseButton button = event.getButton();
			if (button.equals(MouseButton.PRIMARY)) {
				getView().selectTab(this);
			}
		});
	}

	// Tab item header
	private final ObjectProperty<Node> headerProperty = new SimpleObjectProperty(null);
	public final void setHeader(Node header) {
		headerProperty.set(header);
	}
	public final Node getHeader() {
		return headerProperty.get();
	}
	public final ReadOnlyObjectProperty<Node> headerProperty() {
		return headerProperty;
	}

	// Tab item content
	private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty(null);
	public final void setContent(Node content) {
		this.contentProperty.set(content);
	}
	public final Node getContent() {
		return contentProperty.get();
	}
	public final ReadOnlyObjectProperty<Node> contentProperty() {
		return contentProperty;
	}

	private final ObjectProperty<T> viewProperty = new SimpleObjectProperty(null);
	public final void setView(T view) {
		viewProperty.set(view);
	}
	public final T getView() {
		return viewProperty.get();
	}
	public final ReadOnlyObjectProperty<? extends T> viewProperty() {
		return viewProperty;
	}

	private final BooleanProperty selectedProperty = new SimpleBooleanProperty(false);
	/*
	 * 해당 함수는 내부에서만 접근 가능함
	 * 탭이 선택된지, 안됐는지를 지정하기 위한 함수이므로, "selectTab"과는 다른 개념임
	 * 따라서 외부에서는 접근 여부를 알기 위한 "isSelected"만 접근이 가능함
	 */
	protected void setSelected(boolean selected) {
		selectedProperty.set(selected);
	}
	// 외부에서는 isSelected만 접근이 가능함
	public boolean isSelected() {
		return selectedProperty.get();
	}
	public ReadOnlyBooleanProperty selectedProperty() {
		return selectedProperty;
	}

	private final BooleanProperty closableProperty = new SimpleBooleanProperty(true);
	public final void setClosable(boolean closable) {
		this.closableProperty.set(closable);
	}
	public final boolean isClosable() {
		return closableProperty.get();
	}
	public final ReadOnlyBooleanProperty closableProperty() {
		return closableProperty;
	}
}
