package com.sincosmos.algorithms.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

class Node {
    private String c;
    private boolean wordEnd;
    Map<String, Node> children;

    public Node(String c) {
        this.c = c;
        this.children = new HashMap<>();
    }

    public boolean charExist(String c) {
        return children.keySet().contains(c);
    }

    public Node getExistingNode(String c) {
        return children.get(c);
    }

    public void append(Node node) {
        children.put(node.c, node);
    }

    public void setWordEnd(boolean b) {
        this.wordEnd = true;
    }

    public boolean isWordEnd() {
        return wordEnd;
    }
}

public class Trie {
    /** Initialize your data structure here. */
    private Node root;
    public Trie() {
        root = new Node(null);
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for(int i=0; i<word.length(); ++i){
            String c = String.valueOf(word.charAt(i));
            if(cur.charExist(c)){
                //当前字符节点已存在
                cur = cur.getExistingNode(c);
            }else{
                //当前字符节点不存在
                Node tmp = new Node(c);
                cur.append(tmp);
                cur = tmp;
            }
        }
        cur.setWordEnd(true);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for(int i=0; i<word.length(); ++i){
            String c = String.valueOf(word.charAt(i));
            if(cur.charExist(c)){
                cur = cur.getExistingNode(c);
            }else{
                return false;
            }
        }
        return cur.isWordEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for(int i=0; i<prefix.length(); ++i){
            String c = String.valueOf(prefix.charAt(i));
            if(cur.charExist(c)){
                cur = cur.getExistingNode(c);
            }else{
                return false;
            }
        }
        return true;
    }
}
