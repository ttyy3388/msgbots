package org.beuwi.msgbots.platform.app.view.actions;

import javafx.css.PseudoClass;
import javafx.scene.paint.Color;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.layout.AnchorPane;
import org.beuwi.msgbots.platform.gui.layout.HBox;

// MDB 연결 시 다이얼로그 박스가 오픈됨
// 그 박스의 결괏값에 따라 이 클래스가 호출됨
public class SwitchMDBModeAction implements Action {
    private static final PseudoClass mdbmode = PseudoClass.getPseudoClass("mdbmode");
    private static AnchorPane statusbar;

    private static final String message = "MDB CONNECTED";

    @Override
    public void init() {
        statusbar = StatusBarPart.getRoot();
    }

    public static void execute(boolean connect) {
        // CSS에 모드 여부를 업데이트 함
        statusbar.pseudoClassStateChanged(mdbmode, connect);

        // 상태 바에 메시지 출력
        UpdateStatusBarAction.execute(new String[] {
                null, connect ? message : "" });
    }

    @Override
    public String getName() {
        return "switch.mdb.mode.action";
    }
}
