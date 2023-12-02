package com.example.demo_tudien.Dictionary;

public class Word {
    private String wordTarget;
    private String wordExplain;

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public Word() {
        this.wordExplain = "";
        this.wordTarget = "";
    }
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word otherWord = (Word) o;
        return this.wordTarget.equals(otherWord.wordTarget);
    }

    @Override
    public String toString() {
        return "Word{" + "wordTarget='" + wordTarget + '\'' + ", wordExplain='" + wordExplain + '\'' + '}';
    }
}
