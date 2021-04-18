package org.beuwi.msgbots.platform.util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;

import javafx.stage.Stage;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.editor.Editor;

// 현재 프로그램의 정보
public class ProgramData {
    // 현재 포커스(활성화)된 컴포넌트
    public static Node getFocusedNode() {
        Stage stage = MainView.getStage();
        if (stage != null) {
            return stage.getScene().focusOwnerProperty().get();
        }
        return null;
    }

    // 현재 포커스된 탭
    private static final ObjectProperty<TabItem> focusedTab = new SimpleObjectProperty(null);
    public static void setFocusedTab(TabItem tab) {
        focusedTab.set(tab);
    }
    public static TabItem getFocusedTab() {
        return focusedTab.get();
    }

    // 현재 열린 에디터
    /* private static final ObjectProperty<Editor> openedEditor = new SimpleObjectProperty(null);
    public static void setOpenedEditor(Editor editor) {
        openedEditor.set(editor);
    } */
    public static Editor getFocusedEditor() {
        Node node = getFocusedNode();
        if (node instanceof Editor) {
            return (Editor) node;
        }
        else {
            return null;
        }
    }
}
