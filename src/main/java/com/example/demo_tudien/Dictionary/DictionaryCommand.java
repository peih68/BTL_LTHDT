package com.example.demo_tudien.Dictionary;

import com.example.demo_tudien.Trie.Trie;

import java.io.*;

public class DictionaryCommand {

    public static void insertFromFile(Dictionary dictionary, String path) {
        try {
            if (dictionary instanceof EnglishVietnamese) {
                FileReader fileReader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String temp = bufferedReader.readLine();
                String[] spilitArray = temp.split("/",2);
                String wordTarget;
                String line;
                if (!spilitArray[0].trim().replace("@", "").isEmpty()) {
                    wordTarget = spilitArray[0].trim().replace("@", "");
                } else {
                    wordTarget = spilitArray[1].trim();
                }
                while ((line = bufferedReader.readLine()) != null) {
                    Word word = new Word();
                    word.setWordTarget(wordTarget.trim());
                    String meaning = "";
                    if (spilitArray.length > 1) {
                        meaning = "/" + spilitArray[1].trim() + "\n" + line + "\n";
                    }
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.startsWith("@")) {
                            spilitArray = line.split("/", 2);
                            if (!spilitArray[0].trim().replace("@", "").isEmpty()) {
                                wordTarget = spilitArray[0].trim().replace("@", "");
                            } else {
                                if (spilitArray.length > 1) {
                                    wordTarget = spilitArray[1].trim();
                                }
                            }
                            break;
                        } else {
                            meaning += line + "\n";
                        }
                    }
                    word.setWordExplain(meaning.trim());
                    dictionary.getWords().add(word);
                    }
                bufferedReader.close();
            }
            if (dictionary instanceof VietnameseEnglish) {
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
            }
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public static void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary.getWords()) {
                bufferedWriter.write("@" + word.getWordTarget() + "\n");
                bufferedWriter.write(word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }



    public static void addWord(Word word, String path) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
             bufferedWriter.write("@" + word.getWordTarget() + "\n" + word.getWordExplain());
             bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("IOException.");
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public static void deleteWord(Dictionary dictionary, String wordTarget, String path) {
        try {
            dictionary.getWords().removeIf(w -> w.getWordTarget().equals(wordTarget));
            Trie trie = new Trie();
            trie.setTrieFromDictionary(dictionary);
            DictionaryCommand.exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public static void updateWord(Dictionary dictionary, String wordTarget, String newWordExplain, String path) {
        try {
            Word wordToUpdate = new Word();
            for (Word word : dictionary.getWords()) {
                if (word.getWordTarget().equals(wordTarget)) {
                    wordToUpdate.setWordTarget(word.getWordTarget());
                    wordToUpdate.setWordExplain(newWordExplain);
                    dictionary.getWords().remove(word);
                    dictionary.getWords().add(wordToUpdate);
                    break;
                }
            }
            Trie trie = new Trie();
            trie.setTrieFromDictionary(dictionary);
            DictionaryCommand.exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }


}
