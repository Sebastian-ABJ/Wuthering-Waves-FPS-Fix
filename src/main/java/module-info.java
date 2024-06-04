module dev.sebastianjones.wutheringwavesfpsfix {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens dev.sebastianjones.wutheringwavesfpsfix to javafx.fxml;
    exports dev.sebastianjones.wutheringwavesfpsfix;
}