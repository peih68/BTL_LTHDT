package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.DictionaryCommand;
import com.example.demo_tudien.PixabayAPI.PixabayAPI;
import com.example.demo_tudien.Trie.Trie;
import com.example.demo_tudien.ggApi.Translator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ru.blizzed.pixabaylib.Pixabay;
import ru.blizzed.pixabaylib.params.LangParam;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SearchController implements Initializable {

    enum Type {
        AnhViet,
        VietAnh
    }
    Type type = Type.AnhViet;

    SetSceneController setSceneController;

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
        Pixabay.initialize("40694300-4e998735bc707c093f172a751");
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
    public TextField wordTargetTextField;

    @FXML
    private TextArea wordExplainTextField;

    @FXML
    private Button savedButton;

    @FXML
    private Button soundButton;
    private boolean wordExist;
    public static final String typeNotifySave = "save";
    public static final String typeNotifyListen = "listen";
    @FXML
    private void onActionSavedButton() {
        String wordTarget = wordTargetTextField.getText();
        switch (type) {
            case AnhViet :
                if (FullDictionary.EVdictionary.getWordFromWordTarget(wordTarget) != null) {
                    FullDictionary.savedWords.getWords().add(FullDictionary.EVdictionary.getWordFromWordTarget(wordTarget));
                    DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
                    wordExist = true;
                } else {
                    if (!searchArea.getItems().isEmpty()) {
                        String firstWord = searchArea.getItems().getFirst();
                        FullDictionary.savedWords.getWords().add(FullDictionary.EVdictionary.getWordFromWordTarget(firstWord));
                        DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
                        wordExist = true;
                    } else {
                        wordExist = false;
                    }
                }
                showThongBao(wordExist, typeNotifySave);
                break;
            case VietAnh:
                if (FullDictionary.VEdictionary.getWordFromWordTarget(wordTarget) != null) {
                    FullDictionary.savedWords.getWords().add(FullDictionary.VEdictionary.getWordFromWordTarget(wordTarget));
                    DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
                    wordExist = true;
                } else {
                    if (!searchArea.getItems().isEmpty()) {
                        String firstWord = searchArea.getItems().getFirst();
                        FullDictionary.savedWords.getWords().add(FullDictionary.VEdictionary.getWordFromWordTarget(firstWord));
                        DictionaryCommand.exportToFile(FullDictionary.savedWords, "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt");
                        wordExist = true;
                    } else {
                        wordExist = false;
                    }
                }
                showThongBao(wordExist, typeNotifySave);
                break;
        }


    }

    private void setThongBao(boolean save_Success, String type) {
        if(type.equals(typeNotifySave)) {
            if (save_Success) {
                thongBao.setText("Thành công!!!!!!!!");
                thongBao.setId("label_notify");
            } else {
                thongBao.setText("Lỗi : không có từ để lưu???");
                thongBao.setId("label_warning");
            }
        }
        if(type.equals(typeNotifyListen)) {
            thongBao.setText("Lỗi : không có từ để phát âm???");
            thongBao.setId("label_warning");
        }
    }
    @FXML
    private void onActionWordTargetTextField() {
        // Nếu k click vào từ ListView thì k hiện được ảnh
        // Muốn hiện được ảnh thì phải ấn Enter ở WordTargetTextField
        switch (type) {
            case AnhViet :
                if (!searchArea.getItems().isEmpty()) {
                    String firstWord = searchArea.getItems().getFirst();
                    if (!PixabayAPI.getRandomImage(firstWord, LangParam.Lang.EN).isError()) {
                        testImage.setImage(PixabayAPI.getRandomImage(firstWord, LangParam.Lang.EN));
                    } else {
                        testImage.setImage(null);
                    }
                }
                break;
            case VietAnh:
                if (!searchArea.getItems().isEmpty()) {
                    String firstWord = searchArea.getItems().getFirst();
                    if (!PixabayAPI.getRandomImage(firstWord, LangParam.Lang.EN).isError()) {
                        testImage.setImage(PixabayAPI.getRandomImage(firstWord, LangParam.Lang.VI));
                    } else {
                        testImage.setImage(null);
                    }
                }
        }
    }


    private void showThongBao(boolean save_Success, String  type) {
        setThongBao(save_Success, type);
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
                    showThongBao(wordExist, typeNotifyListen);
                }
                break;
            case VietAnh:
                if (!wordTargetTextField.getText().isEmpty()) {
                    Translator.textToSpeech(FullDictionary.VEdictionary.getWordFromWordTarget(wordTargetTextField.getText()).getWordExplain(), Translator.languages.get("English"));
                } else {
                    System.out.println("Hiện thông báo : Không có từ nào để phát âm");
                    showThongBao(wordExist, typeNotifyListen);
                }
                break;
        }


    }

    /** Dictionary */
    Trie searchEV = new Trie();

    Trie searchVE = new Trie();


    public void wordTargetListener() {
        wordTargetTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUIwhenFindWord(newValue);
        });
    }

    public void updateUIwhenFindWord(String newValue) {
        updateUITrie(newValue);
        updateUIWordExplain();
        setKetQua(searchArea.getItems().size());
    }
    public void updateUIWordExplain() {
        switch (type) {
            case AnhViet :
                searchArea.setOnMouseClicked(event -> {
                    String selectedItem = searchArea.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        String userInput = selectedItem.trim();
                        //UI update
                        wordLable.setText(selectedItem);
                        wordTargetTextField.setText(selectedItem);
                        wordExplainTextField.setText(FullDictionary.EVdictionary.getWordFromWordTarget(userInput).getWordExplain());
                        handlePixabayImage(userInput);
                        //data update
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
                        //UI update
                        wordLable.setText(selectedItem);
                        wordTargetTextField.setText(selectedItem);
                        handlePixabayImage(userInput);
                        wordExplainTextField.setText(FullDictionary.VEdictionary.getWordFromWordTarget(userInput).getWordExplain());
                        //data update
                        FullDictionary.historyWords.getWords().add(FullDictionary.VEdictionary.getWordFromWordTarget(userInput));
                        DictionaryCommand.exportToFile(FullDictionary.historyWords,"src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt");
                    }
                });
                break;
        }
    }

    public void updateUITrie(String newValue) {
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

    public void handlePixabayImage(String userInput) {
        Image result;
        if (type == Type.AnhViet) {
            result = PixabayAPI.getRandomImage(userInput, LangParam.Lang.EN);
        }
        else {
            result = PixabayAPI.getRandomImage(userInput, LangParam.Lang.VI);
        }
        if (result != null && !result.isError()) {
            testImage.setImage(result);
        } else {
            testImage.setImage(null);
        }
    }

    public void setKetQua(int length) {
        ketQua1.setText(length + " kết quả liên quan");
        ketQua2.setText(length + " kết quả liên quan");
    }

    /* --- */
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
