package com.example.demo_tudien.ctrler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController {

    SetSceneController setSceneController;
    @FXML
    private AnchorPane gameScene;
    private AnchorPane game1Scene;

    @FXML
    public void showCardMatchingGame() {
        if (setSceneController != null) {
            setSceneController.showCardMatchingGame();
        }
    }

    @FXML
    public void showHangmanGame() {
        if (setSceneController != null) {
            setSceneController.showHangmanGame();
        }
    }

    public void setSceneController(SetSceneController setSceneController) {
        this.setSceneController = setSceneController;
    }

    @FXML
    public void setHome() {
        if (setSceneController != null) {
            setSceneController.showHomePane();
        }
    }
}
