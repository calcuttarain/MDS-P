module com.example.mdsp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.mdsp to javafx.fxml;
    exports com.example.mdsp;
}