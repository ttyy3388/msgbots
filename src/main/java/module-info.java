open module platform {
    // Java
    requires java.desktop;

    // Java Fx
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;

    // requires org.json;
    // requires com.jfoenix;
    requires json.simple;
    // requires org.json;
    requires org.jsoup;
    // requires rhino;

    // requires com.sun.jna;
    // requires com.sun.jna.platform;

    requires jdk.jsobject;

    requires javafx.swing;

    exports org.beuwi.msgbots;
}