package org.beuwi.msgbots.view.gui.control;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.gui.layout.VBox;

public class ChatArea extends VBox {
	@FXML private ChatView listView;
	@FXML private TextArea textArea;

	private final FormLoader loader;
	// private final ContextMenu menu;

	public ChatArea() {
		loader = new FormLoader();
		loader.setName("chat-area-frame");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

        textArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode.equals(KeyCode.TAB)) {
                event.consume();
                return ;
            }
            if (keyCode.equals(KeyCode.ENTER)) {
                if (event.isShiftDown()) {
                    // 현재 커서의 위치를 가져옴
                    int position = textArea.getCaretPosition();
                    textArea.insertText(position, System.lineSeparator());
                    event.consume();
                    return ;
                }

                String text = textArea.getText();

                if (text.trim().isEmpty()) {
                    if (text.isEmpty()) {
                        textArea.clear();
                    }
                    event.consume();
                    return ;
                }

                ChatItem item = new ChatItem(text);
                listView.getItems().add(item);

                textArea.clear();
                event.consume();
            }
			/* else {
				// 리미트 지정
				if (text.length() > MAX_TEXT_LENGTH) {
					textArea.setText(textArea.getText().substring(0, MAX_TEXT_LENGTH));
				}
			} */
        });

        textArea.requestFocus();

        setMinWidth(300);
        setPrefWidth(350);
        getStyleClass().add("chat-area");
	}

	public ChatView getChatView() {
	    return listView;
    }
}
