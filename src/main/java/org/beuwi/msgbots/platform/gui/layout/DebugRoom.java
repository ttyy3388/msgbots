package org.beuwi.msgbots.platform.gui.layout;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.view.actions.SendChatMessageAction;
import org.beuwi.msgbots.platform.gui.control.ChatView;
import org.beuwi.msgbots.platform.gui.control.ContextMenu;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.TextArea;

public class DebugRoom extends StackPane {
    private static final String DEFAULT_STYLE_CLASS = "debug-room";

    private static final int DEFAULT_MIN_WIDTH = 300;
	private static final int DEFAULT_PREF_WIDTH = 350;

	@FXML private ChatView chatView;
	@FXML private TextArea textArea;
	
	private final FormLoader loader;
	// private final ContextMenu menu;
	private final VBox root;
	
	public DebugRoom() {
		loader = new FormLoader("layout", "debug-room-layout", this);
		root = loader.getRoot();

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

                SendChatMessageAction.execute(text);

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

        setMinWidth(DEFAULT_MIN_WIDTH);
        setPrefWidth(DEFAULT_PREF_WIDTH);
        getChildren().setAll(root);
        getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
