package com.example.demo_tudien.Trie;

import com.example.demo_tudien.Dictionary.Word;

import java.util.ArrayList;
import com.example.demo_tudien.Dictionary.Dictionary;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**lớp trie giúp tìm kiếm nhanh hơn.*/

public class Trie {
    public Node root;
    public Trie() {
        root = new Node();
    }

    public void add(String word) {
        Node curr = root;
        if (word == null || word.isEmpty()) {
            System.out.println("Từ trống");
            return;
        }
        for(char i : word.toCharArray()) {
            curr.nexts.putIfAbsent(i, new Node());
            curr = curr.nexts.get(i);
        }
        curr.isWord = true;
    }

    public void DFS(Node node, String prefix, List<String> arr) {
        if (node.isWord) {
            arr.add(prefix);
        }
        ArrayList<String> sortedWords = new ArrayList<>();
        for (Map.Entry<Character, Node> entry : node.nexts.entrySet()) {
            char i = entry.getKey();
            DFS(entry.getValue(), prefix + i, sortedWords);
        }
        Collections.sort(sortedWords);
        arr.addAll(sortedWords);
    }

    public List<String> Query(String word) {
        List<String> searchedWords = new ArrayList<>();
        if(word.isEmpty()) return searchedWords;
        Node curr = root;

        for (char i : word.toCharArray()) {
            curr = curr.nexts.get(i);
            if (curr == null) {
                return searchedWords;
            }
        }
        DFS(curr, word, searchedWords);
        return searchedWords;
    }

    public void setTrieFromDictionary(Dictionary dictionary) {
        for (Word word : dictionary.getWords()) {
            this.add(word.getWordTarget());
        }
    }
}
