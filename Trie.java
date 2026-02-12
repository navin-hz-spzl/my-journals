import java.util.HashMap;
import java.util.Map;

/**
 * High-performance Trie (Prefix Tree) implementation.
 * Showcases recursive traversal and efficient prefix searching.
 */
public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    // Search for a prefix (The recursive "heart" of the structure)
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        return node != null;
    }

    private TrieNode searchNode(String str) {
        TrieNode current = root;
        for (char ch : str.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) return null;
            current = node;
        }
        return current;
    }

    private static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean isEndOfWord;
    }
}
