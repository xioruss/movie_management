module com.example.movie_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;


    opens com.example.movie_management to javafx.fxml;
    opens com.example.movie_management.Controller to javafx.fxml;
    exports com.example.movie_management.Controller;
    exports com.example.movie_management;
}