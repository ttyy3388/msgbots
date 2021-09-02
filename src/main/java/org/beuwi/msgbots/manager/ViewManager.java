package org.beuwi.msgbots.manager;

import javafx.stage.Stage;

import org.beuwi.msgbots.platform.app.view.MainView;

public class ViewManager {
    // Primary Stage
    // private static Application application;
    private static Stage stage;

    // 뷰와 관련된 초반 작업 진행
    public static void init(final Stage value) {
        stage = value;

        // Init Tab (각 파트에서 탭을 사용하므로, 가장 먼저 초기화 되어야 함)
        /* BotListTab.init();
        DetailLogTab.init();
        DebugRoomTab.init();
        GlobalLogTab.init();

        // Init Part
        MenuBarPart.init();
        SideAreaPart.init();
        MainAreaPart.init();
        DebugAreaPart.init();
        StatusBarPart.init();
        ToolAreaPart.init(); */

        // Init Dialog

        // Init Main
        MainView.init();
    }

    public static Stage getStage() {
        return stage;
    }
}
