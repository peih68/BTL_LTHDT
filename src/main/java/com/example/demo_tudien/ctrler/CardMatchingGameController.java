package com.example.demo_tudien.ctrler;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.*;

public class CardMatchingGameController {
    @FXML
    Label Notify;

    @FXML
    private ProgressBar timerProgressBar;
    private Timeline timer;
    private static final int TIME_LIMIT_SECONDS = 30;
    private double elapsedTime = 0;
    private boolean gameEnded = false;
    private int score = 0;


    @FXML
    private GridPane gridPane;

    private static final int GRID_SIZE = 4;
    private List<String> cardValues = new ArrayList<>();
    Map<String, String> wordMap = new HashMap<>();
    private Button[][] cards = new Button[GRID_SIZE][GRID_SIZE];
    private Button firstCard = null;
    private Button secondCard = null;

    @FXML
    private Button startButton;

    @FXML
    private Button playAgainButton;

    boolean isGameStarted =false;

    @FXML
    public void startGame() {
        startButton.setDisable(true);
        startButton.setVisible(false);

        playAgainButton.setVisible(true);

        initializeValues();
        initializeCardValues();
        shuffleCardValues();
        initializeCards();
        displayInitialCards();
//        initializeStyleCards();

        initializeTimer();
        startTimer();

        isGameStarted =true;
    }
    @FXML
    public void playAgain() {
        resetGame();
    }

    private void resetGame() {
        // Reset necessary variables, enable cards, reset score, etc.
        gameEnded = false;
        elapsedTime = 0;
        score = 0;
        timerProgressBar.setProgress(0);
        shuffleCardValues();

        displayInitialCards();
        enableAllCards();

        startTimer();

    }

    @FXML
    public void initialize() {
//        initializeValues();
//        initializeCardValues();
//        shuffleCardValues();
//        initializeCards();
//        displayInitialCards();
//
//        initializeTimer();
//        startTimer();
        Notify.setVisible(false);
        playAgainButton.setVisible(false);
    }
    private void initializeValues() {
        // Thêm từ và ý nghĩa vào HashMap
        wordMap.put("Apple", "quả táo");
        wordMap.put("Book", "sách");
        wordMap.put("Car", "xe hơi");
        wordMap.put("Dog", "con chó");
        wordMap.put("Elephant", "con voi");
        wordMap.put("Flower", "bông hoa");
        wordMap.put("Guitar", "đàn guitar");
        wordMap.put("House", "nhà");
    }
    private void initializeCardValues() {
        for (Map.Entry<String, String> entry : wordMap.entrySet()) {
            cardValues.add(entry.getKey());
            cardValues.add(entry.getValue());
        }
    }
    //trộn thẻ
    private void shuffleCardValues() {
        Collections.shuffle(cardValues);
    }

    private void displayInitialCards() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button card = cards[i][j];
                displayCardValue(card);
            }
        }
    }

    private void initializeCards() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button card = new Button();
                card.setStyle("-fx-font-weight: bold;\n" +
                        "    -fx-font-family: 'Times New Roman';\n" +
                        "    -fx-background-color: rgba(255,127,80, 0.7);\n" +
                        "    -fx-font-size: 18;\n" +
                        "    -fx-background-radius: 22;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-border-color: white;\n" +
                        "    -fx-border-width: 2;\n" +
                        "    -fx-border-radius: 20;");
                card.setPrefSize(300, 300);
                card.setOnAction(event -> handleCardClick(card));
                cards[i][j] = card;
                gridPane.add(card, j, i);
            }
        }
    }

    private void handleCardClick(Button card) {
        if (firstCard == null) {
            firstCard = card;
            setButtonSelectedStyle(firstCard);
        } else if (secondCard == null) {
            secondCard = card;
            setButtonSelectedStyle(firstCard);
            for (Map.Entry<String, String> entry : wordMap.entrySet()) {
                if (entry.getKey().equals(firstCard.getText()) && entry.getValue().equals(secondCard.getText())){
                    firstCard.setDisable(true);
                    secondCard.setDisable(true);
                    score++;
                    if(score == 8){
                        timer.stop();
                        endGame();
                    }
                    break;
                } else if (entry.getValue().equals(firstCard.getText()) && entry.getKey().equals(secondCard.getText())) {
                    firstCard.setDisable(true);
                    secondCard.setDisable(true);
                    score++;
                    if(score == 8){
                        timer.stop();
                        endGame();
                    }
                    break;
                }
            }
            resetButtonStyle(firstCard);
            firstCard = null;
            secondCard = null;
        }
    }
    private void displayCardValue(Button card) {
        String value = cardValues.get(GridPane.getRowIndex(card) * GRID_SIZE + GridPane.getColumnIndex(card));
        card.setText(value);
    }

    private void setButtonSelectedStyle(Button button) {
        button.setStyle("-fx-font-weight: bold;\n" +
                "    -fx-font-family: 'Times New Roman';\n" +
                "    -fx-background-color: rgba(0,255,255, 0.7);\n" +
                "    -fx-font-size: 18;\n" +
                "    -fx-background-radius: 22;\n" +
                "    -fx-alignment: center;\n" +
                "    -fx-border-color: white;\n" +
                "    -fx-border-width: 2;\n" +
                "    -fx-border-radius: 20;");
    }

    private void resetButtonStyle(Button button) {
        button.setStyle("-fx-font-weight: bold;\n" +
                "    -fx-font-family: 'Times New Roman';\n" +
                "    -fx-background-color: rgba(255,127,80, 0.7);\n" +
                "    -fx-font-size: 18;\n" +
                "    -fx-background-radius: 22;\n" +
                "    -fx-alignment: center;\n" +
                "    -fx-border-color: white;\n" +
                "    -fx-border-width: 2;\n" +
                "    -fx-border-radius: 20;");
    }

    private void initializeTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            elapsedTime += 0.1;
            double progress = elapsedTime / TIME_LIMIT_SECONDS;
            timerProgressBar.setProgress(progress);

            if (elapsedTime >= TIME_LIMIT_SECONDS && !gameEnded) {
                timer.stop();
                endGame();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    private void startTimer() {
        timer.play();
    }

    private void endGame() {
        gameEnded = true;
        // Add your game ending logic here
        // For example:
        if(score == 8){
            System.out.println("win");
        } else {
            System.out.println("loss");
        }
        showNotify(score == 8);
//        System.out.println("end!!!!!!!!!!!!!!!!!!!!!!");
        disableAllCards(); // Disable all cards or show game over message
    }
    private void disableAllCards() {
        // Disable all cards or show game over message
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cards[i][j].setDisable(true);
            }
        }
        score=0;
    }
    private void enableAllCards() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cards[i][j].setDisable(false);
                resetButtonStyle(cards[i][j]);
            }
        }
    }

    private void setNotify(boolean win) {
        if(win) {
            Notify.setText("You Won");
        } else {
            Notify.setText("You lose");
        }
    }
    private void showNotify(boolean win) {
        setNotify(win);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1.5),
                        event -> Notify.setVisible(false)
                )
        );
        Notify.setVisible(true);
        timeline.play();
    }




















    SetSceneController setSceneController;
    public void setSceneController(SetSceneController setSceneController) {
        this.setSceneController = setSceneController;
    }

    @FXML
    public void setGamePane() {
        if (setSceneController != null) {
            if(isGameStarted) {
                setSceneController.showGamePane();
                resetGame();
                timer.stop();
                disableAllCards();
            } else {
                setSceneController.showGamePane();
            }
        }
    }


}
