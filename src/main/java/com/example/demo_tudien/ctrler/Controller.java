package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.DictionaryCommand;
import com.example.demo_tudien.Dictionary.EnglishVietnamese;
import com.example.demo_tudien.Dictionary.VietnameseEnglish;
import com.example.demo_tudien.Dictionary.Word;
import com.example.demo_tudien.Trie.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.List;

public class Controller implements Initializable {
    private AnchorPane Pane;

    @FXML
    private AnchorPane Sence;

    @FXML
    private SplitPane splitPane;

    private static final Duration ANIMATION_DURATION = Duration.millis(300);

    private boolean isSidebarVisible = true;

    @FXML
    private TextField wordTarget;

    @FXML
    private TextArea wordExplain;

    EnglishVietnamese EVdictionary = new EnglishVietnamese();
    VietnameseEnglish VEdictionary = new VietnameseEnglish();

    DictionaryCommand dictionaryCommand = new DictionaryCommand();

    Trie searchEV = new Trie();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryCommand.insertFromFile(EVdictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Anh-Viet.txt");
        dictionaryCommand.insertFromFile(VEdictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Viet-Anh.txt");
        for(Word word : EVdictionary.getWords()) {
            searchEV.add(word.getWordTarget());
        }
    }

    public void findWordByWordTarget() {
        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            String userInput = newValue.trim();
            List<String> res = searchEV.Query(userInput);
            String finalRes = "";
            for (String str : res) {
                finalRes += str + "\n";
            }
            if (userInput.isEmpty()) {
                wordExplain.setText("trong");
            } else {
                wordExplain.setText(finalRes);
            }
        });
    }

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

    public void setSearch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/Search.fxml"));
            Pane = loader.load();

            Sence.setRightAnchor(Pane,5.0);
            Sence.setLeftAnchor(Pane,5.0);
            Sence.getChildren().setAll(Pane);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setGoogle() {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Google.fxml")));
            Pane = loader.load();

            Sence.setRightAnchor(Pane,5.0);
            Sence.setLeftAnchor(Pane,5.0);
            Sence.getChildren().setAll(Pane);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setHome() {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Home.fxml")));
            Pane = loader.load();

            Sence.setRightAnchor(Pane,5.0);
            Sence.setLeftAnchor(Pane,5.0);

            Sence.getChildren().setAll(Pane);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setGame() {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/Game.fxml")));
            Pane = loader.load();

            Sence.setRightAnchor(Pane,5.0);
            Sence.setLeftAnchor(Pane,5.0);

            Sence.getChildren().setAll(Pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
