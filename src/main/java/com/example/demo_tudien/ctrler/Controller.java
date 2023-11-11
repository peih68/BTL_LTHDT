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
}
