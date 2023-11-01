package com.example.demo_tudien.Trie;
/**lớp dùng để làm cơ sở cho lớp trie.*/
public class Node {
    Node[] nexts = new Node[26];
    int count;

    /**có phải từ không.*/
    boolean isWord;
    public Node() {
        for(Node next : nexts) {
            next = null;
        }
        count = 0;
        isWord = false;
    }
}
