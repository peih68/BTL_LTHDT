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
        Translator.textToSpeech(chuaDichTextArea.getText(),Translator.Vietnamese);
    }

    public void onActionphatAmDaDichButton() {
        Translator.textToSpeech(daDichTextArea.getText(),Translator.English);
    }

    public void onActionTranslateButton() throws IOException {
        String target = langluageWordTarget.getValue();
        String meaning = langluageWordMeaning.getValue();
        if(target== null||meaning == null) {
            langluageWordTarget.setValue("Vietnamese");
            langluageWordMeaning.setValue("English");
            target = langluageWordTarget.getValue();
            meaning = langluageWordMeaning.getValue();
        }
        String str = Translator.translate(Translator.languages.get(target),Translator.languages.get(meaning),chuaDichTextArea.getText());
        daDichTextArea.setText(str);
    }

    @FXML
    public void switchType()  {
        String target = langluageWordTarget.getValue();
        String meaning = langluageWordMeaning.getValue();
        if(target== null||meaning == null) {
            langluageWordTarget.setValue("Vietnamese");
            langluageWordMeaning.setValue("English");
            target = langluageWordTarget.getValue();
            meaning = langluageWordMeaning.getValue();
        }
        langluageWordTarget.setValue(meaning);
        langluageWordMeaning.setValue(target);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(Translator.languageList);
        langluageWordTarget.setItems(items);
        langluageWordMeaning.setItems(items);
    }
}
