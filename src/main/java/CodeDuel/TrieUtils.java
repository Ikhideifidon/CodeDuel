package CodeDuel;

public interface TrieUtils {
    void insert(String word);
    boolean search(String word);
    boolean startsWith(String prefix);
    boolean isEmpty();
    int size();
    String longestPrefixOf(String word);
    Iterable<String> allPrefixOf(String word);
}
