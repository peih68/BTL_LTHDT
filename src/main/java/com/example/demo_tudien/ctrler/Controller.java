package com.example.demo_tudien.ctrler;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Controller {
    private boolean isSidebarVisible = true;
    private static final Duration ANIMATION_DURATION = Duration.millis(300);
    private AnchorPane Pane;

    @FXML
    private AnchorPane translateScene;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button Option;
    public void Option() {
        double endValue = isSidebarVisible ? 0.0 : 0.1;

        KeyValue keyValue = new KeyValue(splitPane.getDividers().get(0).positionProperty(), endValue);
        KeyFrame keyFrame = new KeyFrame(ANIMATION_DURATION, keyValue);

        Timeline timeline = new Timeline(keyFrame);
        timeline.setOnFinished(event -> {
            isSidebarVisible = !isSidebarVisible;
        });
        timeline.play();
    }

    public void setTranslate() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/com/example/Views/translate.fxml").toURI().toURL());
            Pane = loader.load();

            translateScene.setRightAnchor(Pane,5.0);
            translateScene.setLeftAnchor(Pane,5.0);
            translateScene.getChildren().setAll(Pane);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setGoogle() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/com/example/Views/google.fxml").toURI().toURL());
            Pane = loader.load();

            translateScene.setRightAnchor(Pane,5.0);
            translateScene.setLeftAnchor(Pane,5.0);
            translateScene.getChildren().setAll(Pane);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Home() {
        translateScene.getChildren().clear();
    }
}
