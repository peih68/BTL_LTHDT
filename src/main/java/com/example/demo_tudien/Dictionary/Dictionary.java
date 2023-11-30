package com.example.demo_tudien.Dictionary;

import org.controlsfx.dialog.WizardPane;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
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
}
