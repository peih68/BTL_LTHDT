package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.FullDictionary;
import com.example.demo_tudien.Dictionary.Word;
import com.example.demo_tudien.Trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable{

    SetSceneController setSceneController;

    Trie trie = new Trie();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateWordOfDay();
        setWordOfDay();
        setTextLHW();
        trie.setTrieFromDictionary(FullDictionary.EVdictionary);
    }

    @FXML
    Label wordTargetWOD, wordTargetLHW;

    @FXML
    Label wordExplainWOD, wordExplainLHW;

    @FXML
    ListView<String> searchListView;

    @FXML
    TextField wordTargetTextField;

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
    public void onActionWOD() {
        if (setSceneController != null) {
            setSceneController.findWordInSearch(wordTargetWOD.getText());
        }
    }
    @FXML
    public void onActionLHW() {
        if (setSceneController != null) {
            setSceneController.findWordInSearch(wordTargetLHW.getText());
        }
    }

    public void updateUITrie(String newValue) {
        String userInput = newValue.trim();
        List<String> res = trie.Query(userInput);
        ObservableList<String> dataVE = FXCollections.observableArrayList(res);
        searchListView.setItems(dataVE);
        searchListView.setVisible(!searchListView.getItems().isEmpty());
    }

    public void wordTargetListener() {
        wordTargetTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUITrie(newValue);
            handleClickWord();
        });
    }

    public void handleClickWord() {
        searchListView.setOnMouseClicked(event -> {
            String selectedItem = searchListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                setSceneController.findWordInSearch(selectedItem);
            }
        });
    }

    @FXML
    public void goToHistory() {
        if (setSceneController != null) {
            setSceneController.showHistoryPane();
        }
    }

    public void setSetSceneController(SetSceneController setSceneController) {
        this.setSceneController = setSceneController;
    }


}
