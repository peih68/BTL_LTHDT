package com.example.demo_tudien.ctrler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    SetSceneController setSceneController;

    @FXML
    public void setSceneController() {
        if (setSceneController != null) {
            setSceneController.showSearchPane();
        }
    }

    @FXML
    public void setHome(){
        if (setSceneController != null) {
            setSceneController.showHomePane();
        }
    }

    public void setSetSceneController(SetSceneController setSceneController) {
        this.setSceneController = setSceneController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
