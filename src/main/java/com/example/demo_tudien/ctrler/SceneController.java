package com.example.demo_tudien.ctrler;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchTranslate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("translate.fxml"));
        Node sourceNode = (Node) event.getSource();
        stage = (Stage) sourceNode.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
