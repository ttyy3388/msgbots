package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.platform.app.impl.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToggleViewPartAction implements Action {
    private static final Map<Pane, List<Pane>> saveList = new HashMap<>();
    private static final Map<Pane, Integer> saveIndex = new HashMap<>();

    @Override
    public void init() {

    }

    public static void execute(Pane pane) {
        if (!saveList.containsKey(pane)) {
            Pane parent = (Pane) pane.getParent();
            List<Pane> list = (List) parent.getChildren();
            saveList.put(pane, list);
            saveIndex.put(pane, list.indexOf(pane));
        }

        List<Pane> list = saveList.get(pane);
        int index = saveIndex.get(pane);

        // if showing
        if (list.contains(pane)) {
            list.remove(pane);
        }
        // if hiding
        else {
            list.add(index, pane);
        }

        // 굳이 숨겨졌던 뷰를 다시 추가할 때 맵에서 삭제할 필요는 없을 거 같아
        // 삭제 부분 구현 X
    }

    @Override
    public String getName() {
        return "toggle.view.part.action";
    }
}
