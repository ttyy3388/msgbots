package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Project;

import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.manager.ProjectManager;
import org.mozilla.javascript.annotations.JSFunction;

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
    public Boolean reply(String room, String message, Boolean hideToast) {
        /* Project project = ProjectManager.findByName(room);
        // 존재하지 않는 방을 입력한 경우 전역 디버그 룸으로 전송
        Session session = (project != null) ? project.getSession() : Session.GLOBAL; */
        session.chat(message, true);
        return true;
    }
}
