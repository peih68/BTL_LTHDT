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
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean isSidebarVisible = true;
    private static final Duration ANIMATION_DURATION = Duration.millis(300);
    private AnchorPane Pane;

    @FXML
    private AnchorPane translateScene;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button Option;

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
        wordTarget.setOnAction(event -> {
            String userInput = wordTarget.getText();
            for (Word word : EVdictionary.getWords()) {
                if (userInput.equals(word.getWordTarget())) {
                    wordExplain.setText(word.getWordExplain());
                    return;
                }
            }
            wordExplain.setText("khong co tu");
        });
    }


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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/translate.fxml"));
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
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/Views/google.fxml")));
            Pane = loader.load();

            AnchorPane.setRightAnchor(Pane,5.0);
            AnchorPane.setLeftAnchor(Pane,5.0);
            translateScene.getChildren().setAll(Pane);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Home() {
        translateScene.getChildren().clear();
    }
}
