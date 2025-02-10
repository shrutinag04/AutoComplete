package com.example.autocompletePro.controller;


import com.example.autocompletePro.service.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autocomplete")
public class AutocompleteController {

    private final Trie trie;

    @Autowired
    public AutocompleteController(Trie trie) {
        this.trie = trie;
    }

    @GetMapping("/")
    public String getGreeting(){
        return "Hi,I work as an autocomplete";
    }

    @PostMapping("/add")
    public String addWord(@RequestBody String word) {
        trie.insert(word);
        return "Word added: " + word;
    }

    @GetMapping("/search/{word}")
    public boolean searchWord(@PathVariable String word) {
        return trie.search(word);
    }

    @GetMapping("/suggestions")
    public List<String> getAutocompleteSuggestions(@RequestParam String prefix) {
        return trie.autocomplete(prefix);
    }
}
