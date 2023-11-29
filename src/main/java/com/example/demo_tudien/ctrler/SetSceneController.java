package com.example.demo_tudien.ctrler;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SetSceneController implements Initializable {
    private AnchorPane searchPane;
    private AnchorPane googlePane;
    private AnchorPane homePane;
    private AnchorPane gamePane;
    private AnchorPane CardMatchingGameControllerPane;

    private AnchorPane HangmanPane;
    private AnchorPane historyPane;
    @FXML
    private AnchorPane basicSence;
    @FXML
    private AnchorPane Scene;
    @FXML
    private AnchorPane showScene;

    @FXML
    ToggleButton Option;

    private GameController gameController;

    private HomeController homeController;

    private ggTranslateController ggTranslateController;

    private HistoryController historyController;

    private SearchController searchController;

    private CardMatchingGameController cardMatchingGameController;

    private HangmanController hangmanController;

    public void Option() {
        if(Option.isSelected()) {
                basicSence.setLeftAnchor(Scene, 8.0);
            }
            else {
                basicSence.setLeftAnchor(Scene, 105.0);
        }
    }

    private void setScene(AnchorPane anchorPane) {
        AnchorPane.setRightAnchor(anchorPane,0.0);
        AnchorPane.setLeftAnchor(anchorPane,0.0);
        showScene.getChildren().setAll(anchorPane);
    }

    @FXML
    public void findWordInSearch(String word) {
        setScene(searchPane);
        searchController.updateUIwhenFindWord(word);
        searchController.handlePixabayImage(word);
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
        homeController.setTextLHW();
    }
    @FXML
    public void showHistoryPane() {
        setScene(historyPane);
        historyController.setStartListView();
    }
    @FXML void showCardMatchingGame() {
        setScene(CardMatchingGameControllerPane);
    }

    @FXML void showHangmanGame() {
        setScene(HangmanPane);
    }

    @FXML
    public void showGamePane() {
        setScene(gamePane);
    }

    @FXML void close() {
        Platform.exit();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load search.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/Search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
            searchController.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Google.fxml
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Google.fxml")));
            googlePane = loader.load();
            ggTranslateController = loader.getController();
            ggTranslateController.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //HomePane
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Home.fxml")));
            homePane = loader.load();
            homeController = loader.getController();
            homeController.setSetSceneController(this);
            homeController.searchListView.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //gamePane
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Game.fxml")));
            gamePane = loader.load();
            gameController = loader.getController();
            gameController.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //storePane
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Store.fxml")));
            historyPane = loader.load();
            historyController = loader.getController();
            historyController.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Game 1
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/CardMatchingGame.fxml")));
            CardMatchingGameControllerPane = loader.load();
            cardMatchingGameController =  loader.getController();
            cardMatchingGameController.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Hangman
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Hangman.fxml")));
            HangmanPane = loader.load();
            hangmanController =  loader.getController();
            hangmanController.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setScene(homePane);
    }
}
