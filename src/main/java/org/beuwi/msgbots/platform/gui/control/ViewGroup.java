package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;

import java.util.Arrays;

// or Editor Group
public class ViewGroup<T extends Node> extends SplitPane {
	public ObjectProperty<T> selectedViewProperty = new SimpleObjectProperty();

	public ViewGroup() {
		this(null);
	}

	public ViewGroup(T... views) {
	    if (views != null) {
            getItems().addAll(views);
        }

	    // 포커스 받으면 선택 중인 뷰로 지정정
	   Arrays.stream(views).forEach(item -> {
	    	item.focusedProperty().addListener(change -> {
	    		setSelectedView(item);
			});
		});

		getItems().addListener((ListChangeListener) change -> {
			int size = getItems().size();

			// 삭제 시 자동으로 1번 에디터가 선택되도록
			/* while (change.next()) {
				if (change.wasRemoved()) {
					if (size > 0) {
						property.set((Editor) list.get(0));
					}
				}
			} */

            // 추가될 때 마다 디비더 포지션을 바꿈
			for (int i = 0 ; i < size - 1 ; i ++) {
				setDividerPosition(i, (1 / (double) size) * (i + 1));
			}
		});
	}

	public T getSelectedView() {
		return selectedViewProperty.get();
	}

	public void setSelectedView(T item) {
		selectedViewProperty.set(item);
	}

	public ObjectProperty<T> selectedViewProperty() {
		return selectedViewProperty;
	}

	public ObservableList<T> getViews() {
		return (ObservableList) getItems();
	}
}
