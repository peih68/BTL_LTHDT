package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable{
    SetSceneController setSceneController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateWordOfDay();
        setWordOfDay();
        setTextLHW();
    }

    @FXML
    Label wordTargetWOD, wordTargetLHW;

    @FXML
    Label wordExplainWOD, wordExplainLHW;

    private Word wordOfDay;
    private static final int MAX_WORDS = 1000;

    public void generateWordOfDay() {
        Random random = new Random(LocalDate.now().getDayOfMonth());
        long index = random.nextLong();
        index %= MAX_WORDS;
        wordOfDay = FullDictionary.EVdictionary.getWords().get(Math.abs((int) index));
    }
    public void setWordOfDay() {
        wordTargetWOD.setText(wordOfDay.getWordTarget());
        wordExplainWOD.setText(wordOfDay.getWordExplain());

    }

    public void setTextLHW() {
        wordExplainLHW.setText(FullDictionary.historyWords.getWords().getLast().getWordExplain());
        wordTargetLHW.setText(FullDictionary.historyWords.getWords().getLast().getWordTarget());
    }

    @FXML
    public void setSceneController() {
        if (setSceneController != null) {
            setSceneController.showSearchPane();
        }
    }

    public void setSetSceneController(SetSceneController setSceneController) {
        this.setSceneController = setSceneController;
    }

}
