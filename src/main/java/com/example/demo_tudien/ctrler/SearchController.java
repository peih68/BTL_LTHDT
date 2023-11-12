package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.*;
import com.example.demo_tudien.Trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryCommand.insertFromFile(EVdictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Anh-Viet.txt");
        dictionaryCommand.insertFromFile(VEdictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Viet-Anh.txt");
        dictionaryCommand.insertFromFile(savedWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
        dictionaryCommand.insertFromFile(savedWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt");
        searchVE.setTrieFromDictionary(VEdictionary);
        searchEV.setTrieFromDictionary(EVdictionary);
    }

    @FXML
    Label ketQua1;

    @FXML
    Label ketQua2;

    @FXML
    ToggleButton typeSearch;

    @FXML
    private ListView<String> searchArea;

    @FXML
    private TextField wordTargetTextField;

    @FXML
    private TextArea wordExplainTextField;

    @FXML
    private Button savedButton;
    @FXML
    private void handleToggle() {
        if (typeSearch.isSelected()) {
            typeSearch.setText("Việt-Anh");
        } else {
            typeSearch.setText("Anh-Việt");
        }
    }
    @FXML
    private void onActionSavedButton() {
        String wordTarget = wordTargetTextField.getText();
        if (EVdictionary.getWordFromWordTarget(wordTarget) != null) {
            savedWords.getWords().add(EVdictionary.getWordFromWordTarget(wordTarget));
            dictionaryCommand.exportToFile(savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
        } else {
            if (!searchArea.getItems().isEmpty()) {
                String firstWord = searchArea.getItems().getFirst();
                savedWords.getWords().add(EVdictionary.getWordFromWordTarget(firstWord));
                dictionaryCommand.exportToFile(savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
            } else {
                System.out.println("Hiện thông báo lỗi : không có từ để lưu");
            }
        }
    }

    /** Dictionary */
    EnglishVietnamese EVdictionary = new EnglishVietnamese();
    VietnameseEnglish VEdictionary = new VietnameseEnglish();

    Dictionary savedWords = new Dictionary();

    Dictionary historyWords = new Dictionary();


    DictionaryCommand dictionaryCommand = new DictionaryCommand();

    Trie searchEV = new Trie();

    Trie searchVE = new Trie();


    public void wordTargetListener() {
        wordTargetTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            findWordByWordTarget(newValue);
            findWordExplainByWordTarget();
            setKetQua(searchArea.getItems().size());
        });
    }

    public void setKetQua(int length) {
        ketQua1.setText(length + " kết quả liên quan");
        ketQua2.setText(length + " kết quả liên quan");
    }

    public void findWordExplainByWordTarget() {
        searchArea.setOnMouseClicked(event -> {
            String selectedItem = searchArea.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String userInput = selectedItem.trim();
                wordExplainTextField.setText(EVdictionary.getWordFromWordTarget(userInput).getWordExplain());
                historyWords.getWords().add(EVdictionary.getWordFromWordTarget(userInput));
                dictionaryCommand.exportToFile(historyWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt");
            }
        });

    }

    public void findWordByWordTarget(String newValue) {
        String userInput = newValue.trim();
        List<String> res = searchEV.Query(userInput);
        ObservableList<String> data = FXCollections.observableArrayList(res);
        searchArea.setItems(data);

        if (!searchArea.getItems().isEmpty()) {
            String firstWord = searchArea.getItems().getFirst();
            wordExplainTextField.setText(EVdictionary.getWordFromWordTarget(firstWord).getWordExplain());
        } else {
            wordExplainTextField.setText("");
        }
    }

    /* --- */


}
