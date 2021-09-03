package org.beuwi.msgbots.base;

import org.beuwi.msgbots.base.type.LogType;

// 컴파일러에서 로그를 남기기 위한 클래스
public class Logger {
    private final Session session;
    protected Logger(Session session) {
        this.session = session;
    }

    public void info(String data) {
        session.log(LogType.INFO, data);
    }

    public void debug(String data) {
        session.log(LogType.DEBUG, data);
    }

    public void error(String data) {
        session.log(LogType.ERROR, data);
    }
}
