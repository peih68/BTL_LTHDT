package com.example.demo_tudien.Trie;

/**lớp trie giúp tìm kiếm nhanh hơn.*/

public class Trie {
    public Node root;
    public Trie() {
        root = new Node();
    }

    /**thêm từ vào cây trie.*/
    public void add(String word) {
        Node curr = root;
        int l = word.length();
        for(int i = 0; i < l; i++) {
            int index = word.charAt(i) - 'a';
            if(index > 26 || index < 0) {return;}
            if(curr.nexts[index] == null) {
                curr.nexts[index] = new Node();
            }
            curr.nexts[index].count++;
            curr = curr.nexts[index];
        }
        curr.isWord = true;
    }

    /**phương thức get các từ có tiền tố giống từ người dùng tìm vào mảng arr.*/
    private void getString(Node node, String prefix, String[] arr, int index) {
        if (node == null) {
            return;
        }
        if (node.isWord) {
            arr[index] = prefix;
            index++;
        }
        for (int i = 0; i < 26; i++) {
            if(node.nexts[i] == null) continue;
            char c = (char) ('a' + i);
            String newPre = prefix + c;
            getString(node.nexts[i], newPre, arr, index);
        }
        if (index == 10) return;
    }

    /**thao tác tìm kiếm
     * trả về chuỗi các từ liên quan.*/
    String[] Query(String word) {
        Node curr = root;
        int len = word.length();
        for (int i = 0; i < len; ++i) {
            int index = word.charAt(i) - 'a';
            if (curr.nexts[index] == null) {
                return null;
            }
            curr = curr.nexts[index];
        }
        String[] toReturn = new String[curr.count];
        getString(curr, word, toReturn, 0);
        return toReturn;
    }
}
