package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.ggApi.Translator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ggTranslateController implements Initializable {
    SetSceneController setSceneController;

    @FXML
    TextArea chuaDichTextArea;

    @FXML
    TextArea daDichTextArea;

    @FXML
    Button translateButton;

    @FXML
    Button phatAmChuaDichButton;

    @FXML
    Button phatAmDaDichButton;

    @FXML
    ComboBox<String> langluageWordTarget;

    @FXML
    ComboBox<String> langluageWordMeaning;

    public void onActionPhatAmChuaDichButton() {
        String text = chuaDichTextArea.getText();
        String target = langluageWordTarget.getValue();
        if (!text.isEmpty()) {
            Translator.textToSpeech(chuaDichTextArea.getText(), Translator.languages.get(target));
        }
    }

    public void onActionphatAmDaDichButton() {
        String text = daDichTextArea.getText();
        String meaning = langluageWordMeaning.getValue();
        if (!text.isEmpty()) {
            Translator.textToSpeech(daDichTextArea.getText(), Translator.languages.get(meaning));
        }
    }

    public void onActionTranslateButton() throws IOException {
        String target = langluageWordTarget.getValue();
        String meaning = langluageWordMeaning.getValue();
        String str = Translator.translate(Translator.languages.get(target),Translator.languages.get(meaning),chuaDichTextArea.getText());
        daDichTextArea.setText(str);
    }

    @FXML
    public void switchType()  {
        String target = langluageWordTarget.getValue();
        String meaning = langluageWordMeaning.getValue();
        langluageWordTarget.setValue(meaning);
        langluageWordMeaning.setValue(target);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(Translator.languageList);
        langluageWordTarget.setItems(items);
        langluageWordMeaning.setItems(items);
        langluageWordTarget.setValue("Vietnamese");
        langluageWordMeaning.setValue("English");
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
