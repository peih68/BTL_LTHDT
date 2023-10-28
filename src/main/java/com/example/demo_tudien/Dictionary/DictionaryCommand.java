package com.example.demo_tudien.Dictionary;

import java.io.*;
import java.util.ArrayList;

public class DictionaryCommand {

    public void insertFromFile(Dictionary dictionary, String path) {
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
                    }
                    else {
                        meaning += line + "\n";
                    }
                word.setWordExplain(meaning.trim());
                dictionary.getWords().add(word);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary.getWords()) {
                bufferedWriter.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }


}
