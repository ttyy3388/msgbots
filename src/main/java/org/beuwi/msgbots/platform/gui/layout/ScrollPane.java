package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

import javafx.scene.layout.Pane;
import org.beuwi.msgbots.platform.gui.base.Layout;

public class ScrollPane extends javafx.scene.control.ScrollPane implements Layout {
	private static final String DEFAULT_STYLE_CLASS = "scroll-pane";

	private static final double DEFAULT_SCROLL_SPEED = 0.005;

	// 해당 프로펄티가 활성화일 때는 자동으로 마지막 아이템으로 스크롤함
	private final BooleanProperty autoScrollProperty = new SimpleBooleanProperty();
	/* private final InvalidationListener autoScrollListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			Node content = getContent();

			if (content instanceof Pane) {
				Pane target = (Pane) content;
				vvalueProperty().bind(target.heightProperty());
			}
		}
	}; */
	public void setAutoScroll(boolean value) {
		autoScrollProperty.set(value);
	}
	public boolean isAutoScroll() {
		return autoScrollProperty.get();
	}
	public BooleanProperty autoScrollProperty() {
		return autoScrollProperty;
	}

	public ScrollPane() {
		this(null);
	}

	public ScrollPane(Node node) {
		if (node != null) {
			setContent(node);
		}

		contentProperty().addListener(event -> {
			Node content = getContent();
			if (content != null) {
				if (content instanceof Pane) {
					Pane pane = (Pane) content;
					// 자동 스크롤이 켜져 있다면 제일 아래로 스크롤 되도록
					pane.heightProperty().addListener(change -> {
						if (isAutoScroll()) {
							setVvalue(1.0d);
						}
					});
				}
			}
		});

		addEventFilter(ScrollEvent.SCROLL, event -> {
			setHvalue(getHvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
			setVvalue(getVvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
		});

		setFitToWidth(true);
		setFitToHeight(true);

		// setHbarPolicy(ScrollBarPolicy.ALWAYS);
		// setVbarPolicy(ScrollBarPolicy.ALWAYS);
		setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		// addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public Node findById(String id) {
		return findById(this, id);
	};
}