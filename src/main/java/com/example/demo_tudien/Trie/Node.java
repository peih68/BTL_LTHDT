package com.example.demo_tudien.Trie;

import java.util.HashMap;
import java.util.Map;

/**lớp dùng để làm cơ sở cho lớp trie.*/
public class Node {
    Map<Character, Node> nexts;
    int count;

    /**có phải từ không.*/
    boolean isWord;
    public Node() {
        nexts = new HashMap<>();
        count = 0;
        isWord = false;
    }
}
