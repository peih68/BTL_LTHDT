package com.example.demo_tudien.ctrler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class HangmanController implements Initializable {

    private SetSceneController setSceneController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHint();
    }

    @FXML
    ImageView img;

    Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo_tudien/image/1.png")));
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo_tudien/image/2.png")));
    Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo_tudien/image/3.png")));
    Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo_tudien/image/4.png")));
    Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo_tudien/image/5.png")));
    Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo_tudien/image/6.png")));
    Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo_tudien/image/7.png")));

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;
    @FXML
    TextField tf5;
    @FXML
    TextField tf6;
    @FXML
    TextField tf7;
    @FXML
    TextField tf8;
    @FXML
    TextField input;
    @FXML
    Label hint;
    @FXML
    Label letter_count;
    @FXML
    Label hint_label;

    @FXML
    Label gameOver;
    String[] data = {
            "MEXICO COUNTRY",
            "CANADA COUNTRY",
            "FRANCE COUNTRY",
            "INDIA COUNTRY",
            "DOCTOR PROFESSION",
            "TEACHER PROFESSION",
            "LAWYER PROFESSION",
            "PILOT PROFESSION",
            "FARMER PROFESSION",
            "ARTIST PROFESSION",
            "ENGINEER PROFESSION",
            "BANKER PROFESSION",
            "TENNIS SPORT",
            "GOLF SPORT",
            "HOCKEY SPORT",
            "FOOTBALL SPORT",
            "LEOPARD ANIMAL",
            "ZEBRA ANIMAL",
            "TIGER ANIMAL",
            "PANDA ANIMAL",
            "DEER ANIMAL",
            "BICYCLE TRANSPORT",
            "AIRPLANE TRANSPORT",
            "SUBWAY TRANSPORT",
            "TRAIN TRANSPORT",
            "SHIP TRANSPORT",
            "SALMON FISH",
            "TUNA FISH",
            "PARROTS BIRD",
            "FALCON BIRD",
            "EAGLE BIRD",
    };

    int random = new Random().nextInt(data.length);
    String word_hint = data [random];
    String[] split = word_hint.split(" ", 2);
    String word = split[0];
    String hint_str = split[1];
    int letter_size = word.length();

    public void setHint(){
        hint.setText(hint_str);
        letter_count.setText(letter_size+" Letters");

        if(letter_size==7){
            tf8.setVisible(false);
        }
        if(letter_size==6){
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==5){
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==4){
            tf5.setVisible(false);
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
    }
    private int letter = 0;

    public void CheckInput(){
        String str = input.getText();
        str = str.toUpperCase();
        // Nếu đoán đúng
        if (word.contains(str)) {
            int index = 0;
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (String.valueOf(c).equals(str)) {
                    setLetter(index, Character.toString(c));
                    letter++;
                    if (letter == word.length()) {
                        gameOver.setText("Bạn đã chiến thắng !!!");
                    }
                }
                index++;
            }
        }
        // Nếu đoán sai
        else {
            setImage();
        }
        input.clear();
    }

    public void setLetter(int index, String str) {
        switch (index) {
            case 0:
                tf1.setText(str);
                break;
            case 1:
                tf2.setText(str);
                break;
            case 2:
                tf3.setText(str);
                break;
            case 3:
                tf4.setText(str);
                break;
            case 4:
                tf5.setText(str);
                break;
            case 5:
                tf6.setText(str);
                break;
            case 6:
                tf7.setText(str);
                break;
            case 7:
                tf8.setText(str);
                break;
            default:
                break;
        }
    }

    int life=6;
    public void setImage(){
        switch (life) {
            case 6:
                img.setImage(image2);
                break;
            case 5:
                img.setImage(image3);
                break;
            case 4:
                img.setImage(image4);
                break;
            case 3:
                img.setImage(image5);
                break;
            case 2:
                img.setImage(image6);
                break;
            case 1:
                img.setImage(image7);
                gameOver.setText("Từ khóa đúng là: " + word);
                break;
            default:
                break;
        }
        life--;
    }


    public void resetGame() {
        life = 6; letter = 0;
        img.setImage(image1);
        gameOver.setText("");

        input.clear();
        tf5.setVisible(true);
        tf6.setVisible(true);
        tf7.setVisible(true);
        tf8.setVisible(true);
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tf7.setText("");
        tf8.setText("");

        random = new Random().nextInt(data.length);
        String word_hint = data[random];
        String[] split = word_hint.split(" ", 2);
        word = split[0];
        hint_str = split[1];
        letter_size = word.length();

        setHint();
    }

    @FXML
    public void setGamePane() {
        if (setSceneController != null) {
                setSceneController.showGamePane();
                resetGame();
            } else {
                setSceneController.showGamePane();
            }
        }

    public void setSceneController(SetSceneController setSceneController) {
        this.setSceneController = setSceneController;
    }
}
