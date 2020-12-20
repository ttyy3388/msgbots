package org.beuwi.msgbots.platform.gui.skins;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.SkinBase;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.control.VBox;
import org.beuwi.msgbots.platform.gui.enums.ControlType;
import org.beuwi.msgbots.platform.gui.layout.ScrollPanel;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

public class TabViewSkin extends SkinBase<TabView> {
	// private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private static final String HEADER_STYLE_CLASS = "tab-header-area";
	private static final String CONTENT_STYLE_CLASS = "tab-content-area";

	public static final PseudoClass NORMAL_PSEUDO_CLASS = PseudoClass.getPseudoClass("normal");
	public static final PseudoClass SYSTEM_PSEUDO_CLASS = PseudoClass.getPseudoClass("system");

	private final ObservableList<Tab> tabs = FXCollections.observableArrayList();

	// private static final int DEFAULT_HEADER_WIDTH = 100;
	private static final int DEFAULT_HEADER_HEIGHT = 30;

	private final VBox root = new VBox();

	/* Tab View [ Header Area [ Headers [ Tab Header ( Main ) ] ] , Content Area [ Tab Content ] ] */

	// Tab Content Area
	private final StackPanel content = new StackPanel();

	// Tab Header Area [ Tab Header List ]
	private final ScrollPanel header = new ScrollPanel();

	// Tab Header List
	private final HBox<Tab> headers = new HBox();

	private final TabView control;

	{
		VBox.setVgrow(content, Priority.ALWAYS);
	}

	public enum Type {
		NORMAL, SYSTEM
	}

	public TabViewSkin(final TabView control) {
		super(control);

		this.control = control;

		header.setHvalue(1.0d);
		header.setContent(headers);
		header.setFitToWidth(false);
		header.setFitToHeight(true);
		header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setMaxHeight(DEFAULT_HEADER_HEIGHT);
		header.setHbarPolicy(ScrollBarPolicy.NEVER);
		header.setVbarPolicy(ScrollBarPolicy.NEVER);

		// header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setPrefHeight(DEFAULT_HEADER_HEIGHT);
		// header.setMaxHeight(DEFAULT_HEADER_HEIGHT);

		if (!control.getTabs().isEmpty()) {
			headers.setItems(control.getTabs());
			content.setItem(control.getSelectedTab().getContent());
		}

		if (control.getType() != null) {
            this.setType(control.getType());
        }

		header.getStyleClass().add(HEADER_STYLE_CLASS);
		content.getStyleClass().add(CONTENT_STYLE_CLASS);

		control.getTabs().addListener((ListChangeListener<Tab>) change -> {
			while (change.next()) {
				for (Tab tab : change.getRemoved()) {
					headers.remove(tab);
				}
				for (Tab tab : change.getAddedSubList()) {
					headers.addItem(tab);
				}
			}
		});

		control.getTypeProperty().addListener(change -> {
			this.setType(control.getType());
		});

		control.getSelectedTabProperty().addListener(change -> {
			Node target = control.getSelectedTab().getContent();
			content.setItem(target);
			// 변경된 탭으로 포커스 이동하도록
			target.requestFocus();
		});

		root.setItems(header, content);
		root.setFittable(true);
		// root.addStyleClass("tab-view");

		this.setItems(root);
	}

	// private or protected
	private void setType(ControlType type) {
		switch (type) {
			case NORMAL :
				header.setFitToWidth(false);

				for (Tab tab : control.getTabs()) {
					HBox.setHgrow(tab, Priority.NEVER);
				}
				break;

			case SYSTEM :
				header.setFitToWidth(true);

				for (Tab tab : control.getTabs()) {
					HBox.setHgrow(tab, Priority.ALWAYS);
				}
				break;
		}
	}
}