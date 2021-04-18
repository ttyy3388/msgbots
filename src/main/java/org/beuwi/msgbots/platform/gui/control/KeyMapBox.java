package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.openapi.KeyMap;
import org.beuwi.msgbots.platform.gui.layout.HBox;

import java.util.List;

public class KeyMapBox extends HBox {
	private static final int DEFAULT_PREF_WIDTH = 250;
	// private static final int DEFAULT_PREF_HEIGHT = 30;

	private final HBox keyBox = new HBox();

	private final Label nameLabel = new Label();
	// private final Label keyLabel = new Label();

	{
		HBox.setHgrow(nameLabel, Priority.ALWAYS);
		HBox.setHgrow(keyBox, Priority.ALWAYS);
	}

	public KeyMapBox(KeyMap keyMap) {
		this(keyMap.getName(), keyMap.getKey().toString());
	}

	public KeyMapBox(String name, String key) {
		nameLabel.setText(name);
        nameLabel.setPadding(new Insets(0, 5, 0, 5));
        nameLabel.getStyleClass().add("name-label");

		// 2개 이상의 키 조합일 경우
		if (key.contains("+")) {
		    // 해당 문자로 분해
            String[] array = key.split("\\+");
            int index = 0, size = array.length;
            for (index = 0 ; index < size ; index ++) {
                Label keyLabel = new Label(array[index]);
                keyLabel.setPadding(new Insets(5, 10, 5, 10));
                keyLabel.getStyleClass().add("key-label");
                keyBox.getChildren().add(keyLabel);

                // 배열 중간이라면 '+' 기호 삽입
                if ((index + 1) != size) {
                    Label plusLabel = new Label("+");
                    // plusLabel.setPadding(new Insets(5, 10, 5, 10));
                    keyBox.getChildren().add(plusLabel);
                }
            }
        }
		else {
            Label keyLabel = new Label(key);
            keyLabel.setPadding(new Insets(0, 10, 0, 10));
            keyLabel.getStyleClass().add("key-label");
            keyBox.getChildren().add(keyLabel);
        }

		// 딱 반으로 나뉘어지도록 ('-1'은 테두리 제거)
		widthProperty().addListener(change -> {
		    double value = (getWidth() / 2) - 1;
            nameLabel.setPrefWidth(value);
            keyBox.setPrefWidth(value);
        });

        // keyLabel.setText(key);
        keyBox.setSpacing(5.0);

        setPadding(new Insets(10, 0, 10, 0));
		// setPrefWidth(DEFAULT_PREF_WIDTH);
		// setPrefHeight(DEFAULT_PREF_HEIGHT);

		getChildren().setAll(
            nameLabel,
            keyBox
		);
		getStyleClass().add("keymap-box");
	}
}
