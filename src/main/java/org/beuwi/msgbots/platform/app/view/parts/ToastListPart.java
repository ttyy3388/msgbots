package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.Tooltip;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.ToastItem;
import org.beuwi.msgbots.platform.gui.control.ToastView;
import org.beuwi.msgbots.platform.gui.layout.AnchorPane;
import org.beuwi.msgbots.platform.gui.layout.ShadowPane;
import org.beuwi.msgbots.platform.util.AllSVGIcons;

// 추후 쓸 예정 ( 다이얼로그 박스로 대체 )
public class ToastListPart implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static AnchorPane root;
	private static ShadowPane component;

	@Override
	public void init() {
		loader = new FormLoader("part", "toast-list-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		ToastView toast = (ToastView) namespace.get("tsvToastList");
		toast.getItems().addListener((ListChangeListener<ToastItem>) change -> {
			// 아이템이 없으면 안보이도록
			component.setVisible(!toast.getItems().isEmpty());
		});

		Button button = (Button) namespace.get("btnListClear");
		button.setGraphic(AllSVGIcons.get("Box.Close"));
		button.setOnAction(event -> {
			toast.getItems().removeAll(toast.getItems());
		});
		button.setTooltip(new Tooltip("Close All"));

		root.setBackground(null);
		// root.setMouseTransparent(true);
		root.setPickOnBounds(false);

		component.setVisible(false);
		component.setBackground(null);
		// component.setMouseTransparent(true);
		component.setPickOnBounds(false);
	}

	public static AnchorPane getRoot() {
		return root;
	}

	public static ShadowPane getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}
