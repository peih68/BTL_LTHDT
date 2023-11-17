package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.DictionaryCommand;
import com.example.demo_tudien.Trie.Trie;
import com.example.demo_tudien.ggApi.Translator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SearchController implements Initializable {

    enum Type {
        AnhViet,
        VietAnh
    }
    Type type = Type.AnhViet;

    @FXML
    ImageView testImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryCommand.insertFromFile(FullDictionary.EVdictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Anh-Viet.txt");
        DictionaryCommand.insertFromFile(FullDictionary.VEdictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Viet-Anh.txt");
        DictionaryCommand.insertFromFile(FullDictionary.savedWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
        DictionaryCommand.insertFromFile(FullDictionary.historyWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt");
        searchEV.setTrieFromDictionary(FullDictionary.EVdictionary);
        searchVE.setTrieFromDictionary(FullDictionary.VEdictionary);
        thongBao.setVisible(false);
    }

    @FXML
    ToggleButton typeSearch;

    @FXML
    private void handleToggle() {
        if (typeSearch.isSelected()) {
            type = Type.VietAnh;
            typeSearch.setText("Việt-Anh");
        } else {
            type = Type.AnhViet;
            typeSearch.setText("Anh-Việt");
        }
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
        switch (type) {
            case AnhViet :
                if (FullDictionary.EVdictionary.getWordFromWordTarget(wordTarget) != null) {
                    FullDictionary.savedWords.getWords().add(FullDictionary.EVdictionary.getWordFromWordTarget(wordTarget));
                    DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
                    save_Success = true;
                } else {
                    if (!searchArea.getItems().isEmpty()) {
                        String firstWord = searchArea.getItems().getFirst();
                        FullDictionary.savedWords.getWords().add(FullDictionary.EVdictionary.getWordFromWordTarget(firstWord));
                        DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
                        save_Success = true;
                    } else {
                        save_Success = false;
                    }
                }
                showThongBao(save_Success);
                break;
            case VietAnh:
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
                        save_Success = false;
                    }
                }
                showThongBao(save_Success);
                break;
        }


    }

    private void setThongBao(boolean save_Success) {
        if(save_Success) {
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

        switch (type) {
            case AnhViet :
                if (!wordTargetTextField.getText().isEmpty()) {
                    Translator.textToSpeech(wordTargetTextField.getText(), Translator.languages.get("English"));
                } else {
                    System.out.println("Hiện thông báo : Không có từ nào để phát âm");
                }
                break;
            case VietAnh:
                if (!wordTargetTextField.getText().isEmpty()) {
                    Translator.textToSpeech(FullDictionary.VEdictionary.getWordFromWordTarget(wordTargetTextField.getText()).getWordExplain(), Translator.languages.get("English"));
                } else {
                    System.out.println("Hiện thông báo : Không có từ nào để phát âm");
                }
                break;
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
        switch (type) {
            case AnhViet :
                searchArea.setOnMouseClicked(event -> {
                    String selectedItem = searchArea.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        String userInput = selectedItem.trim();
                        wordLable.setText(selectedItem);
                        wordTargetTextField.setText(selectedItem);
                        wordExplainTextField.setText(FullDictionary.EVdictionary.getWordFromWordTarget(userInput).getWordExplain());
                        FullDictionary.historyWords.getWords().add(FullDictionary.EVdictionary.getWordFromWordTarget(userInput));
                        DictionaryCommand.exportToFile(FullDictionary.historyWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt");
                    }
                });
                break;
            case VietAnh:
                searchArea.setOnMouseClicked(event -> {
                    String selectedItem = searchArea.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        String userInput = selectedItem.trim();
                        wordLable.setText(selectedItem);
                        wordTargetTextField.setText(selectedItem);
                        wordExplainTextField.setText(FullDictionary.VEdictionary.getWordFromWordTarget(userInput).getWordExplain());
                        FullDictionary.historyWords.getWords().add(FullDictionary.VEdictionary.getWordFromWordTarget(userInput));
                        DictionaryCommand.exportToFile(FullDictionary.historyWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt");
                    }
                });
                break;
        }


    }

    public void findWordByWordTarget(String newValue) {
        String userInput = newValue.trim();

        switch (type) {
            case AnhViet :
                List<String> resEV = searchEV.Query(userInput);
                ObservableList<String> dataEV = FXCollections.observableArrayList(resEV);
                searchArea.setItems(dataEV);
                if (!searchArea.getItems().isEmpty()) {
                    String firstWord = searchArea.getItems().getFirst();
                    wordLable.setText(firstWord);
                    wordExplainTextField.setText(FullDictionary.EVdictionary.getWordFromWordTarget(firstWord).getWordExplain());
                } else {
                    wordLable.setText("");
                    wordExplainTextField.setText("");
                }
                break;
            case VietAnh:
                List<String> resVE = searchVE.Query(userInput);
                ObservableList<String> dataVE = FXCollections.observableArrayList(resVE);
                searchArea.setItems(dataVE);
                if (!searchArea.getItems().isEmpty()) {
                    String firstWord = searchArea.getItems().getFirst();
                    wordLable.setText(firstWord);
                    wordExplainTextField.setText(FullDictionary.VEdictionary.getWordFromWordTarget(firstWord).getWordExplain());
                } else {
                    wordLable.setText("");
                    wordExplainTextField.setText("");
                }
                break;
        }
    }

    /* --- */


}
