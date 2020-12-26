open module org.beuwi.msgbots {
    // Java
    requires java.desktop;

    // Java Fx
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;

    requires com.jfoenix;
    requires json.simple;
    // requires org.json;
    requires org.jsoup;
    requires rhino;

    requires jdk.jsobject;

    exports org.beuwi.msgbots;
}