package com.example.autocompletePro.model;

public class TrieNode {

        public TrieNode[] children;
        public boolean isEnd;
        public TrieNode(){
            children=new TrieNode[26];
        }

}
