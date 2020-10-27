package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.stream.Collectors;

public class NoticeView extends VBox<Notice>
{
	private static final String DEFAULT_STYLE_CLASS = "notice-view";
	private static final int DEFAULT_MIN_WIDTH = 500;
	private static final int DEFAULT_GAP_VALUE = 10;

	public NoticeView()
	{
		getItemsProperty().addListener((ListChangeListener<Node>) change ->
		{
			/* for (Toast toast : getToasts())
			{
				toast.setView(this);
			} */
		});

		setSpacing(DEFAULT_GAP_VALUE);
		setMinWidth(DEFAULT_MIN_WIDTH);
	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void addToast(Notice notice)
	{
		addItem(notice);
	}

	public Notice getToast(int index)
	{
		return (Notice) getItem(index);
	}

	public Notice getToast(String id)
	{
		return (Notice) getItem(id);
	}

	// Listener 등록 안되고 아이템들만 받아짐 (추후 수정)
	public ObservableList<Notice> getToasts()
	{
		return getItems().stream().map(tab -> (Notice) tab).collect(Collectors.toCollection(FXCollections::observableArrayList));
	}
}