package com.example.demo_tudien.Dictionary;

import javafx.fxml.FXML;

import java.io.*;

public class VietnameseEnglish extends Dictionary {
    @Override
    public void insertFromFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String wordTarget = bufferedReader.readLine();
            wordTarget = wordTarget.replace("@", "");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Word word = new Word();
                word.setWordTarget(wordTarget.trim());
                String meaning = line + "\n";
                while ((line = bufferedReader.readLine()) != null)
                    if (line.startsWith("@")) {
                        wordTarget = line.replace("@", "");
                        break;
                    } else {
                        meaning += line + "\n";
                    }
                word.setWordExplain(meaning.trim());
                this.getWords().add(word);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    @Override
    public void exportToFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : this.getWords()) {
                bufferedWriter.write("@" + word.getWordTarget() + "\n");
                bufferedWriter.write(word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
}
