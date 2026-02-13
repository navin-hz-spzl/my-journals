import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A thread-safe Trie implementation for high-speed prefix lookups in 
 * Banking API keys and Secure Token segments.
 */
public class SecureTokenRegistry {

    private final TrieNode root;

    public SecureTokenRegistry() {
        this.root = new TrieNode();
    }

    private static class TrieNode {
        private final Map<Character, TrieNode> children = new ConcurrentHashMap<>();
        private boolean isEndOfToken = false;
        private String metadata; // Store token classification (e.g., "SAML", "OAUTH", "WEB3")

        public void setEndOfToken(boolean endOfToken, String metadata) {
            this.isEndOfToken = endOfToken;
            this.metadata = metadata;
        }
    }

    /**
     * Inserts a token into the registry with metadata.
     */
    public void registerToken(String token, String type) {
        TrieNode current = root;
        for (char ch : token.toCharArray()) {
            current = current.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        current.setEndOfToken(true, type);
    }

    /**
     * Validates if a prefix exists in the registry. 
     * Essential for routing and fraud-monitoring flows.
     */
    public boolean isValidPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) return false;
            current = node;
        }
        return true;
    }

    public static void main(String[] args) {
        SecureTokenRegistry registry = new SecureTokenRegistry();
        registry.registerToken("BOFA_SEC_123", "OAUTH2");
        registry.registerToken("MET_SSO_999", "SAML2.0");

        System.out.println("Checking prefix 'BOFA': " + registry.isValidPrefix("BOFA")); 
        System.out.println("Checking prefix 'CRYPTO': " + registry.isValidPrefix("CRYPTO"));
    }
}
