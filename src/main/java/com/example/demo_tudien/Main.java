package com.example.demo_tudien;

import com.example.demo_tudien.Dictionary.Dictionary;
import com.example.demo_tudien.Dictionary.DictionaryCommand;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Dictionary dictionary = new Dictionary();
            DictionaryCommand dictionaryCommand = new DictionaryCommand();
            dictionaryCommand.insertFromFile(dictionary,"src/main/resources/com/example/demo_tudien/DictionarySrc/Anh-Viet.txt");

            System.out.println(dictionary.getWords().get(0));

            FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/resources/com/example/Views/sample.fxml").toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load(), 945, 614);
            stage.setTitle("Hello!");
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
