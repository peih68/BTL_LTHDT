module com.example.demo_tudien {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jlayer;
    requires pixabay.lib;

    opens com.example.demo_tudien to javafx.fxml;
    exports com.example.demo_tudien;
    exports com.example.demo_tudien.ctrler;
    opens com.example.demo_tudien.ctrler to javafx.fxml;
}