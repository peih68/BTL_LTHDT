package com.example.demo_tudien.Trie;

import com.example.demo_tudien.Dictionary.Word;

import java.util.ArrayList;
import com.example.demo_tudien.Dictionary.Dictionary;
import java.util.List;

/**lớp trie giúp tìm kiếm nhanh hơn.*/

public class Trie {
    public Node root;
    public Trie() {
        root = new Node();
    }

    /**thêm từ vào cây trie.*/
    public void add(String word) {
        Node curr = root;
        if (word == null || word.isEmpty()) {
            System.out.println("từ trống");
            return;
        }
        for(char i : word.toCharArray()) {
            curr.nexts.putIfAbsent(i, new Node());
            curr = curr.nexts.get(i);
        }
        curr.isWord = true;
    }

    /**duyện DFS để tìm các từ liên quan.*/
    public void DFS(Node node, String prefix, List<String> arr) {
        if(node.isWord ) arr.add(prefix);
        for (char i = 'a'; i <= 'z'; i++) {
            if (node.nexts.containsKey(i)) {
                DFS(node.nexts.get(i), prefix + i, arr);
            }
        }
    }

    /**thao tác tìm kiếm
     * trả về chuỗi các từ liên quan.*/
    public List<String> Query(String word) {
        List<String> searchedWords = new ArrayList<>();
        if(word.isEmpty()) return searchedWords;
        Node curr = root;

        //cho chạy đến node cuối của từ đang tìm
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
