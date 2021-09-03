package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Project;

import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.manager.ProjectManager;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class Replier {
    private final Session session;
    public Replier(Session session) {
        this.session = session;
    }

    @JSFunction
    public Boolean reply(String message) {
        session.chat(message, true);
        return true;
    }

    @JSFunction
    public static Boolean reply(String room, String message, Boolean hideToast) {
        /*
        // 존재하지 않는 방을 입력한 경우 전역 디버그 룸으로 전송
        Session session = (project != null) ? project.getSession() : Session.GLOBAL; */
        // room을 프로젝트 이름으로 간주
        Project project = ProjectManager.findByName(room);
        if (project == null) {
            return false;
        }
        Session session = project.getSession();
        session.chat(message, true);
        return true;
    }
}
