package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.ggApi.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ggTranslateController {
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

    public void onActionPhatAmChuaDichButton() {
        Translator.textToSpeech(chuaDichTextArea.getText(),Translator.Vietnamese);
    }

    public void onActionphatAmDaDichButton() {
        Translator.textToSpeech(daDichTextArea.getText(),Translator.English);
    }

    public void onActionTranslateButton() throws IOException {
        String str = Translator.translate(Translator.Vietnamese,Translator.English,chuaDichTextArea.getText());
        daDichTextArea.setText(str);
    }
}
