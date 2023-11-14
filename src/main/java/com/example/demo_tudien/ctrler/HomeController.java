package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateWordOfDay();
        setWordOfDay();
        setTextLHW();
    }

    @FXML
    TextField wordTargetWOD, wordTargetLHW;

    @FXML
    TextArea wordExplainWOD, wordExplainLHW;

    private Word wordOfDay;
    private static final int MAX_WORDS = 1000;

    public void generateWordOfDay() {
        Random random = new Random(LocalDate.now().getDayOfMonth());
        long index = random.nextLong();
        index %= MAX_WORDS;
        wordOfDay = SearchController.EVdictionary.getWords().get(Math.abs((int) index));
    }
    public void setWordOfDay() {
        wordTargetWOD.setText(wordOfDay.getWordTarget());
        wordExplainWOD.setText(wordOfDay.getWordExplain());

    }

    public void setTextLHW() {
        wordExplainLHW.setText(SearchController.historyWords.getWords().getLast().getWordExplain());
        wordTargetLHW.setText(SearchController.historyWords.getWords().getLast().getWordTarget());
    }

}
