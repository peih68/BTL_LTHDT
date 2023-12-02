package com.example.demo_tudien.Dictionary;

import com.example.demo_tudien.Trie.Trie;
import org.controlsfx.dialog.WizardPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();

    private int length;

    public int getLength() {
        return words.size();
    }

    public Word getWordFromWordTarget(String wordTarget) {
        for (Word word : words) {
            if (wordTarget.equals(word.getWordTarget())) {
                return word;
            }
        }
        return null;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public List<String> getWordTargetList() {
        List<String> res = new ArrayList<String>();
        for (Word word : this.getWords()) {
            res.add(word.getWordTarget());
        }
        return res;
    }

    public void insertFromFile(String path) {}

    public void exportToFile(String path) {}

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

    public void deleteWord(String wordTarget, String path) {
        try {
            getWords().removeIf(w -> w.getWordTarget().equals(wordTarget));
            Trie trie = new Trie();
            trie.setTrieFromDictionary(this);
            exportToFile(path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    public void updateWord(String wordTarget, String newWordExplain, String path) {
        try {
            Word wordToUpdate = new Word();
            for (Word word : this.getWords()) {
                if (word.getWordTarget().equals(wordTarget)) {
                    wordToUpdate.setWordTarget(word.getWordTarget());
                    wordToUpdate.setWordExplain(newWordExplain);
                    this.getWords().remove(word);
                    this.getWords().add(wordToUpdate);
                    break;
                }
            }
            Trie trie = new Trie();
            trie.setTrieFromDictionary(this);
            exportToFile(path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

}
