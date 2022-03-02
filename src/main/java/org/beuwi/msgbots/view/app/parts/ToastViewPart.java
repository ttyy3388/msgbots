package org.beuwi.msgbots.view.app.parts;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.base.type.LogType;
import org.beuwi.msgbots.base.type.ToastType;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.control.ToastItem;
import org.beuwi.msgbots.view.gui.control.ToastView;
import org.beuwi.msgbots.view.gui.layout.AnchorPane;
import org.beuwi.msgbots.view.util.AllSVGIcons;

public class ToastViewPart extends AnchorPane implements View {
	private static ToastViewPart instance = null;

	private final ObservableMap<String, Node> namespace;
	private final FormLoader loader;
	// private final AnchorPane root;

	@FXML private ToastView listView;

	public ToastViewPart() {
		loader = new FormLoader();
		loader.setName("toast-view-part");
		loader.setRoot(this);
		loader.setController(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();

		// 글로벌 세션을 가져옴
		final Session session = Session.GLOBAL;
		session.addOnLogListener((type, date, data) -> {
			// System.out.println(type + " : " + data);
			// 에러 로그가 입력됐다면
			if (type.equals(LogType.ERROR)) {
				ToastItem item = new ToastItem(ToastType.ERROR, "Script Error", data);
				listView.getItems().add(item);
			}
		});

		listView.getItems().addListener((ListChangeListener<ToastItem>) change -> {
			// 아이템이 없으면 안보이도록
			setVisible(!listView.getItems().isEmpty());
		});

		Button button = (Button) namespace.get("btnListClear");
		button.setGraphic(AllSVGIcons.get("Box.Close"));
		button.setOnAction(event -> {
			listView.getItems().clear();
		});
		button.setTooltip(new Tooltip("Close All"));

		setVisible(false);
		// 루트는 선택되면 안되고, 안의 토스트 뷰가 선택되어야 하기 때문
		setBackground(null);
		// setMouseTransparent(true);
		setPickOnBounds(false);

		/* component.setVisible(false);
		component.setBackground(null);
		// component.setMouseTransparent(true);
		component.setPickOnBounds(false); */
	}

	@Override
	public Node findById(String id) {
		return namespace.get(id);
	}

	public ToastView getToastView() {
		return listView;
	}

	public static ToastViewPart getInstance() {
		if (instance == null) {
			instance = new ToastViewPart();
		}
		return instance;
	}
}
