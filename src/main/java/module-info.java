module com.example.mdsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.google.gson;
    requires java.net.http;

    opens com.example.mdsp to javafx.fxml;
    exports com.example.mdsp;
    exports business.medicalassistant.services;
    exports business.models;
}
