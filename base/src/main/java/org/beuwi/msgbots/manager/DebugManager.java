package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.compiler.engine.ScriptEngine;

// 추후 유저가 직접 엔진(ScriptEngine)을 제작하여 넣는 방식도 고려하면 좋을듯
/* public class DebugManager implements Manager {
    private static ScriptEngine engine = null;
    public static void run(String message) {run(message);

    }
    public static void run(Project project, String message) {
        getManager().run(project, message);
    }

    public static void preInit() {
        getManager().preInit();
    }

    public static void initAll(boolean isManual) {
        getManager().initAll(isManual);
    }

    public static boolean initScript(Project project, boolean isManual, boolean ignoreError) {
        return getManager().initScript(project, isManual, ignoreError);
    }

    // 엔진을 넣어야 작동하는 방식(해당 앱에서는 컴파일러를 직접 제작하지 않는다.)
    // 따라서 유저가 엔진 JAR을 삽입해야 작동함

    public static void setEngine(ScriptManager value) {
        manager = value;
    }

    private final static ScriptManager getManager() {
        if (manager == null) {
            throw new RuntimeException("not initialized");
        }
        return manager;
    }
} */