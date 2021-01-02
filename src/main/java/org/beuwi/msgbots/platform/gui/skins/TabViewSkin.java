package org.beuwi.msgbots.platform.gui.skins;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.ListView;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.control.VBox;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

import java.util.Collection;

/* Tab View [ Header Area [ Headers [ Tab Header ( Main ) ] ] , Content Area [ Tab Content ] ] */
public class TabViewSkin extends SkinBase<TabView> {
	private final HeaderArea headerArea = new HeaderArea();
	private final ContentArea contentArea = new ContentArea();

	{
		HBox.setHgrow(contentArea, Priority.ALWAYS);
		VBox.setVgrow(contentArea, Priority.ALWAYS);
	}

	private final TabView control;

	public TabViewSkin(final TabView control) {
		super(control);

		this.control = control;

		// Initialize Values
		headerArea.setHeaders(control.getTabs());
		contentArea.setContent(control.getSelectedTab());

		control.tabWidthProperty().addListener(change -> {
			// Left or right
			if (control.getType().isVertical()) {
				headerArea.setPrefWidth(control.getTabWidth());
			}
			else {
				return ;
			}
		});

		control.tabHeightProperty().addListener(change -> {
			// Top or bottom
			if (control.getType().isHorizontal()) {
				headerArea.setPrefWidth(control.getTabHeight());
			}
			else {
				return ;
			}
		});

		control.typeProperty().addListener(change -> {
			this.setType(control.getType());
		});

		control.selectedTabProperty().addListener(change -> {
			contentArea.setContent(control.getSelectedTab());
		});

		this.setType(control.getType());
	}

	// Change Tab View Type
	private void setType(Side type) {
		// Left or right
		if (type.isVertical()) {
			headerArea.setPrefWidth(control.getTabWidth());
			headerArea.setPrefHeight(Control.USE_COMPUTED_SIZE); // Reset Height
			headerArea.setOrientation(Orientation.VERTICAL);
		}
		// Top or bottom
		else {
			headerArea.setPrefWidth(Control.USE_COMPUTED_SIZE); // Reset Width
			headerArea.setPrefHeight(control.getTabHeight());
			headerArea.setOrientation(Orientation.HORIZONTAL);
		}

		getChildren().setAll(switch (control.getType()) {
			case TOP -> new VBox(headerArea, contentArea);
			case BOTTOM -> new VBox(contentArea, headerArea);
			case LEFT -> new HBox(headerArea, contentArea);
			case RIGHT -> new HBox(contentArea, headerArea);
		});
	}

	private class HeaderArea extends ListView<TabItem> {
		private static final String HEADER_STYLE_CLASS = "tab-header-area";

		// private static final int DEFAULT_HEADER_HEIGHT = 30;

		public HeaderArea() {

			// setHvalue(1.0d);
			// setContent(inner);
			// setFitToWidth(false);
			// setFitToHeight(true);
			// setMinHeight(DEFAULT_HEADER_HEIGHT);
			// setPrefHeight(DEFAULT_HEADER_HEIGHT);
			// setMaxHeight(DEFAULT_HEADER_HEIGHT);
			// setHbarPolicy(ScrollBarPolicy.NEVER);
			// setVbarPolicy(ScrollBarPolicy.NEVER);
			getStyleClass().add(HEADER_STYLE_CLASS);
		}

		/* public ObservableList<Tab> getHeaders() {
			return headers.getChildren();
		} */

		public void setHeaders(TabItem... tabs) {
			getItems().setAll(tabs);
		}

		public void setHeaders(Collection<? extends TabItem> tabs) {
			getItems().setAll(tabs);
		}

		public void addHeaders(TabItem... tabs) {
			getItems().addAll(tabs);
		}
	}

	private class ContentArea extends StackPanel {
		private static final String CONTENT_STYLE_CLASS = "tab-content-area";

		private final ObjectProperty<Node> content = new SimpleObjectProperty();

		public ContentArea() {
			contentProperty().addListener(change -> {
				Node content = getContent();
				getChildren().setAll(content);
				// 변경된 탭으로 포커스 이동하도록
				content.requestFocus();
			});

			getStyleClass().add(CONTENT_STYLE_CLASS);
		}

		public void setContent(TabItem tab) {
			contentProperty().set(tab.getContent());
		}

		public void setContent(Node node) {
			contentProperty().set(node);
		}

		private Node getContent() {
			return contentProperty().get();
		}

		public ObjectProperty<Node> contentProperty() {
			return content;
		}
	}
}