package com.example.demo_tudien.ctrler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private AnchorPane gameScene;
    private AnchorPane game1Scene;

    private void setScene(AnchorPane anchorPane) {
        AnchorPane.setRightAnchor(anchorPane,5.0);
        AnchorPane.setLeftAnchor(anchorPane,5.0);
        gameScene.getChildren().setAll(anchorPane);
    }

    public void showGame1Pane() {
        setScene(game1Scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/Game_1.fxml"));
            game1Scene = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
