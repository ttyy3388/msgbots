package org.beuwi.msgbots.view.util;

import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.view.gui.control.BotItem;
import org.beuwi.msgbots.view.gui.layout.DebugPane;

import java.util.ArrayList;
import java.util.List;

// GUI로 구성되어 있는 게 아닌, OpenAPI 또는 기본 클래스로만 작성되어 있다면 GUI로 변환하는 과정을 거치는 일을 하는 매니저임
// 현재는 OpenAPI로 구성된 Project클래스에 대해서만 지원함
// 또한, 예외 상황은 아직 고려하지 않고, 추후 문제가 야기될 시 수정하도록 함
// ㄴ> 6월 23일 이후로 project.getProperty("ViewPane")이런식으로 접근함
public class GUIManager implements Manager {
    private static final List<Project> list = new ArrayList<>();
    public static List<Project> getList() {
        return list;
    }

    public static void register(Project project) {
        // 등록된 뷰가 없으면 새로 등록함
        if (!list.contains(project)) {
            // Project클래스에서는 직접적으로 뷰에 간섭하지 않도록 GUIManager클래스에서 Property지정
            project.setProperty("ViewPane", new DebugPane(project));
            project.setProperty("ListItem", new BotItem(project));

            list.add(project);
        }
    }

    // 인자로 받은 클래스를 뷰 패널로 변환함 (Project의 경우 디버그 패널로 변환)
    public static DebugPane toViewPane(Project project) {
        // 프로젝트 매니저에 등록 돼 있어야 함
        if (!ProjectManager.getList().contains(project)) {
            return null;
        }
        return (DebugPane) project.getProperty("ViewPane");
    }


    // 인자로 받은 클래스를 리스트 아이템으로 변환함 (Project의 경우 봇 아이템으로 변환)
    public static BotItem toListItem(Project project) {
        return (BotItem) project.getProperty("ListItem");
    }

    /* public TreeItem toListItem(File file) {

    } */
}
