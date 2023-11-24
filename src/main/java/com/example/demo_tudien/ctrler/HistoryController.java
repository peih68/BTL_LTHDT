package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        savedWordsTrie.setTrieFromDictionary(FullDictionary.savedWords);
        historyTrie.setTrieFromDictionary(FullDictionary.historyWords);
    }
    SetSceneController setSceneController;

    Trie savedWordsTrie = new Trie();

    Trie historyTrie = new Trie();

    @FXML
    public ListView<String> savedWordsListView;

    @FXML
    public ListView<String> historyListView;

    @FXML
    public TextField savedWordsSearchBar;

    @FXML
    public TextField historySearchBar;

    public void SearchBarListener() {
        savedWordsSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUISavedWordsTrie(newValue);
        });
        historySearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUIHistoryTrie(newValue);
        });
    }

    public void setStartListView () {
        List<String> res = FullDictionary.savedWords.getWordTargetList();
        ObservableList<String> data = FXCollections.observableArrayList(res.reversed());
        savedWordsListView.setItems(data);
        res = FullDictionary.historyWords.getWordTargetList();
        data = FXCollections.observableArrayList(res.reversed());
        historyListView.setItems(data);
    }

    public void updateUISavedWordsTrie(String newValue) {
        String userInput = newValue.trim();
        if (userInput.isEmpty()) {
            List<String> res = FullDictionary.savedWords.getWordTargetList();
            ObservableList<String> data = FXCollections.observableArrayList(res.reversed());
            savedWordsListView.setItems(data);
        } else {
            List<String> res = savedWordsTrie.Query(userInput);
            ObservableList<String> data = FXCollections.observableArrayList(res.reversed());
            savedWordsListView.setItems(data);
        }
    }

    public void updateUIHistoryTrie(String newValue) {
        String userInput = newValue.trim();
        if (userInput.isEmpty()) {
            List<String> res = FullDictionary.historyWords.getWordTargetList();
            ObservableList<String> data = FXCollections.observableArrayList(res);
            historyListView.setItems(data.sorted(Comparator.reverseOrder()));
        } else {
            List<String> res = historyTrie.Query(userInput);
            ObservableList<String> data = FXCollections.observableArrayList(res);
            historyListView.setItems(data);
        }
    }

    public void handleClickSavedWord () {
        savedWordsListView.setOnMouseClicked(event -> {
                String selectedItem = savedWordsListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    setSceneController.findWordInSearch(selectedItem);
                }
        });
    }
    public void handleClickHistoryWord () {
        historyListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                String selectedItem = historyListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    setSceneController.findWordInSearch(selectedItem);
                }
            }
        });
    }

    @FXML
    public void goToSearch() {
        setSceneController.findWordInSearch("test");
    }

    public void setSceneController(SetSceneController setSceneController) {
        this.setSceneController = setSceneController;
    }

    @FXML
    public void  setHome() {
        if (setSceneController != null) {
            setSceneController.showHomePane();
        }
    }

}
