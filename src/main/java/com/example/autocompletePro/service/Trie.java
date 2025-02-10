package com.example.autocompletePro.service;

import com.example.autocompletePro.model.TrieNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        word = word.replaceAll("\\s+", "");  // Remove  whitespaces
        word = word.toLowerCase();

        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (ch < 'a' || ch > 'z') {
                throw new IllegalArgumentException("Invalid character encountered: " + ch);
            }
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEnd;
    }

    // Check if there's any word that starts with a given prefix
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    // Get autocomplete suggestions for a given prefix
    public List<String> autocomplete(String prefix) {
        TrieNode node = searchNode(prefix);
        List<String> result = new ArrayList<>();
        if (node != null) {
            autocompleteHelper(node, prefix, result);
        }
        return result;
    }

    // Helper function to perform DFS and find all words
    private void autocompleteHelper(TrieNode node, String currentWord, List<String> result) {
        if (node.isEnd) {
            result.add(currentWord);
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char nextChar = (char) (i + 'a');
                autocompleteHelper(node.children[i], currentWord + nextChar, result);
            }
        }
    }

    // Helper function to find a node corresponding to a given prefix
    private TrieNode searchNode(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';  // Get the index of the character (0 for 'a', 1 for 'b', ..., 25 for 'z')
            if (node.children[index] == null) {
                return null;  // If no node exists for this character, return null
            }
            node = node.children[index];  // Move to the next node
        }
        return node;
    }
}
