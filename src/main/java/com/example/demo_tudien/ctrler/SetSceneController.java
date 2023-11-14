package com.example.demo_tudien.ctrler;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SetSceneController implements Initializable {
    private AnchorPane searchPane;
    private AnchorPane googlePane;
    private AnchorPane homePane;
    private AnchorPane gamePane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane Scene;

    private GameController gameController;

    private HomeController homeController;

    private static final Duration ANIMATION_DURATION = Duration.millis(300);

    private boolean isSidebarVisible = true;



    public void Option() {
        double endValue = isSidebarVisible ? 0.13 : 0.0;

        KeyValue keyValue = new KeyValue(splitPane.getDividers().get(0).positionProperty(), endValue);
        KeyFrame keyFrame = new KeyFrame(ANIMATION_DURATION, keyValue);

        Timeline timeline = new Timeline(keyFrame);
        timeline.setOnFinished(event -> {
            isSidebarVisible = !isSidebarVisible;
            //toggleButton.setText(isSidebarVisible ? "Khép SplitPane" : "Mở SplitPane");
        });
        timeline.play();
    }

    private void setScene(AnchorPane anchorPane) {
        AnchorPane.setRightAnchor(anchorPane,5.0);
        AnchorPane.setLeftAnchor(anchorPane,5.0);
        Scene.getChildren().setAll(anchorPane);
    }

    @FXML
    public void showSearchPane() {
        setScene(searchPane);
    }
    @FXML
    public void showGooglePane() {
        setScene(googlePane);
    }
    @FXML
    public void showHomePane() {
        setScene(homePane);
    }


    @FXML
    public void showGamePane() {
        setScene(gamePane);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load search.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/Search.fxml"));
            searchPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Google.fxml
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Google.fxml")));
            googlePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //HomePane
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Home.fxml")));
            homePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //gamePane
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Game.fxml")));
            gamePane = loader.load();
            gameController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setScene(homePane);
    }
}
