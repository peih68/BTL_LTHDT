package com.example.demo_tudien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
//            Stage.initStyle(StageStyle.UNDECORATED);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/Views/Dictionary.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 600);
            stage.setTitle("Từ điển");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
