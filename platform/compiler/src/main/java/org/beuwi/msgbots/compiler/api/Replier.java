package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.compiler.manager.CompileManager;

import org.mozilla.javascript.annotations.JSFunction;

public class Replier {
    private final Project project;
    public Replier(Project project) {
        this.project = project;
    }

    @JSFunction
    public Boolean reply(String message) {
        CompileManager.sendChat(project, message, true);
        return true;
    }

    @JSFunction
    public Boolean reply(String room, String message, Boolean hideToast) {
        CompileManager.sendChat(project, message, true);
        return true;
    }
}
