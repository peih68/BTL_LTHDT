package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.*;
import com.example.demo_tudien.Trie.Trie;
import com.example.demo_tudien.ggApi.Translator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchVEController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryCommand.insertFromFile(FullDictionary.VEdictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Viet-Anh.txt");
        searchVE.setTrieFromDictionary(FullDictionary.VEdictionary);
        thongBao.setVisible(false);
    }

    @FXML
    Label ketQua1;

    @FXML
    Label ketQua2;

    @FXML
    Label thongBao;

    @FXML
    Label wordLable;

    @FXML
    private ListView<String> searchArea;

    @FXML
    private TextField wordTargetTextField;

    @FXML
    private TextArea wordExplainTextField;

    @FXML
    private Button savedButton;

    @FXML
    private Button soundButton;
    private boolean save_Success;
    @FXML
    private void onActionSavedButton() {
        String wordTarget = wordTargetTextField.getText();
        if (FullDictionary.VEdictionary.getWordFromWordTarget(wordTarget) != null) {
            FullDictionary.savedWords.getWords().add(FullDictionary.VEdictionary.getWordFromWordTarget(wordTarget));
            DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
            save_Success = true;
        } else {
            if (!searchArea.getItems().isEmpty()) {
                String firstWord = searchArea.getItems().getFirst();
                FullDictionary.savedWords.getWords().add(FullDictionary.VEdictionary.getWordFromWordTarget(firstWord));
                DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
                save_Success = true;
            } else {
                System.out.println("Hiện thông báo lỗi : không có từ để lưu");
                save_Success = false;
            }
        }
        showThongBao(save_Success);
    }

    private void setThongBao(boolean save_Success) {
        if(save_Success == true) {
            thongBao.setText("Thành công!!!!!!!!");
            thongBao.setId("label_notify");
        } else {
            thongBao.setText("Lỗi : không có từ để lưu???");
            thongBao.setId("label_warning");
        }
    }

    private void showThongBao(boolean save_Success) {
        setThongBao(save_Success);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1.1),
                        event -> thongBao.setVisible(false)
                )
        );
        thongBao.setVisible(true);
        timeline.play();
    }

    public void onActionSoundButton() {
        if (!searchArea.getItems().isEmpty()) {
            String firstWord = searchArea.getItems().getFirst();
            Translator.textToSpeech(firstWord,Translator.English);
        } else {
            System.out.println("Hiện thông báo : Không có từ nào để phát âm");
        }

    }

    public void setKetQua(int length) {
        ketQua1.setText(length + " kết quả liên quan");
        ketQua2.setText(length + " kết quả liên quan");
    }


    /** Dictionary */
    Trie searchEV = new Trie();

    Trie searchVE = new Trie();


    public void wordTargetListener() {
        wordTargetTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            findWordByWordTarget(newValue);
            findWordExplainByWordTarget();
            setKetQua(searchArea.getItems().size());
        });
    }

    public void findWordExplainByWordTarget() {
        searchArea.setOnMouseClicked(event -> {
            String selectedItem = searchArea.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String userInput = selectedItem.trim();
                wordExplainTextField.setText(FullDictionary.VEdictionary.getWordFromWordTarget(userInput).getWordExplain());
                FullDictionary.historyWords.getWords().add(FullDictionary.VEdictionary.getWordFromWordTarget(userInput));
                DictionaryCommand.exportToFile(FullDictionary.historyWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt");
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
            wordLable.setText(firstWord);
            wordExplainTextField.setText(FullDictionary.VEdictionary.getWordFromWordTarget(firstWord).getWordExplain());
        } else {
            wordLable.setText("");
            wordExplainTextField.setText("");
        }
    }

    /* --- */


}
