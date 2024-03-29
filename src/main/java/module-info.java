open module org.beuwi.msgbots {
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
    // requires json.simple;
    // requires org.json;
    // requires org.jsoup;

    // requires com.sun.jna;
    // requires com.sun.jna.platform;

    requires jdk.jsobject;

    requires javafx.swing;

    // requires rhino;
    requires base;

    // opens org.beuwi.msgbots;
    // exports org.beuwi.msgbots to javafx.graphics;
}