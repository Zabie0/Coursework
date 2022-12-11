module com.staryy.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.staryy.coursework to javafx.fxml;
    exports com.staryy.coursework;
    opens com.staryy.coursework.Controllers to javafx.fxml;
    exports com.staryy.coursework.Controllers;
}