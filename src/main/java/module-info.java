module com.example.mdsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires commons.validator;
    requires com.google.gson;
    requires java.net.http;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires spring.web;
    requires spring.context;
    requires spring.aop;
    requires spring.jdbc;
    requires spring.data.jpa;

    // Export the packages that contain your application's classes
    exports com.example.mdsp;
    exports com.example.mdsp.models;
    exports com.example.mdsp.exceptions;
    exports com.example.mdsp.repos;
    exports com.example.mdsp.services;

    // Open the packages that need to be accessed reflectively (by Spring for proxying)
    opens com.example.mdsp to spring.core, spring.beans, spring.context, spring.aop;
}
