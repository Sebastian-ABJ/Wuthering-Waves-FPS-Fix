package dev.sebastianjones.wutheringwavesfpsfix;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppController {
    @FXML
    private TextField pathTextField;
    @FXML
    private Button pathChooserButton;
    @FXML
    private Button confirmButton;

    @FXML
    protected void onPathChooserButtonClick() {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        // Show open directory dialog
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            pathTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    protected void onConfirmButtonClick() {
        if(validateInputs()) {
            try {
                SQLite.openConnection(pathTextField.getText());
                if(SQLite.alterFPSValue()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "FPS value successfully modified!", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unknown database error!", ButtonType.OK);
                    alert.show();
                }
            } catch(Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Database error. Ensure the game is closed before using this tool.", ButtonType.OK);
                alert.show();
            }
        }
        SQLite.closeConnection();
    }

    private boolean validateInputs() {
        if(pathTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No folder specified!", ButtonType.OK);
            alert.show();
            return false;
        }
        if(!Files.exists(Path.of(pathTextField.getText() + "\\launcher.exe"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Launcher not found! Ensure you are selecting the root Wuthering Waves folder.", ButtonType.OK);
            alert.show();
            return false;
        }
        return true;
    }


}