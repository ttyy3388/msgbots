package org.beuwi.msgbots.view.gui.dialog;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.type.DialogType;
import org.beuwi.msgbots.view.gui.dialog.base.DialogWrapper;
import org.beuwi.msgbots.view.gui.layout.BorderPane;
import org.beuwi.msgbots.view.gui.layout.HBox;

/*
 * 추후 개발 할 필요가 있어보여서 임시로 구현함
 * 다이얼 로그 박스 프레임에는 윈도우 자체만 구현해두고,
 * 이후 Yes/No 다이얼로그 버튼만 있는 다이얼로그를 구현함
 * 이 다이얼로그를 베이스를 해서 대다수의 다이얼로그를 구현함
 * EX : 아이콘을 포함해서 메시지를 출력하는 다이얼로그(Yes/No 포함)
 */
public abstract class YesOrNoDialog extends DialogWrapper {
	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;
	private final BorderPane root;

	// @FXML private BorderPane brpBoxRoot;
	@FXML private HBox hbxButtonBar;

	@FXML private Button btnAction;
	@FXML private Button btnCancel;

	public YesOrNoDialog() {
		this(DialogType.NONE);
	}

	public YesOrNoDialog(DialogType type) {
		this(type, false, false);
	}

	public YesOrNoDialog(DialogType type, boolean action, boolean cancel) {
		super(type);

		loader = new FormLoader();
		loader.setName("yes-or-no-dialog");
		loader.setController(this);
		loader.load();

		namespace = loader.getNamespace();
		root = loader.getRoot();

		/* ------------------------------------------------------ */

		btnAction.addEventHandler(ActionEvent.ACTION, event -> action());
		btnCancel.addEventHandler(ActionEvent.ACTION, event -> close());

		getFooterBar()
		    .getChildren()
	    	.add(hbxButtonBar);
		setUseFooterBar(true);
	}

	@Override
	public void setContent(Node content) {
		root.setCenter(content);
		super.setContent(root);
	}
	@Override
	public Node getContent() {
		return root.getCenter();
	}

	public HBox getButtonBar() {
		return hbxButtonBar;
	}

	public Button getActionButton() {
		return btnAction;
	}

	public Button getCancelButton() {
		return btnCancel;
	}

	// 액션(OK) 버튼 사용, 취소(NO) 버튼 사용
	public void setUseButton(boolean action, boolean cancel) {
		if (!action) {
			hbxButtonBar.getChildren().remove(btnAction);
		}
		if (!cancel) {
			hbxButtonBar.getChildren().remove(btnCancel);
		}
	}
}
