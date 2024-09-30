package CodeDuel;

@SuppressWarnings("unused")
public class WordDictionary implements TrieUtils{
    private static final int R = 26;
    private final TrieNode root;

    // Lightweight TrieNode class
    private static class TrieNode {
        private final TrieNode[] children;
        private boolean isEnd;

        private TrieNode() {
            children = new TrieNode[R];
            isEnd = false;
        }
    }

    public WordDictionary() { root = new TrieNode(); }

    @Override
    public void insert(String word) {
        if (word == null)
            return;
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null)
                node.children[index] = new TrieNode();

            node = node.children[index];
        }
        node.isEnd = true;
    }

    @Override
    public boolean search(String word) {
        return searchInNode(word, 0, root);

    }

    private boolean searchInNode(String word, int index, TrieNode node) {
        if (index == word.length())
            return node.isEnd;

        char c = word.charAt(index);

        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null && searchInNode(word, index + 1, child))
                    return true;
            }
            return false;

        } else {
            int childIndex = c - 'a';
            TrieNode child = node.children[childIndex];

            if (child == null)
                return false;
            return searchInNode(word, index + 1, child);
        }
    }

    @Override
    public boolean startsWith(String prefix) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not Implemented.");
    }

    @Override
    public String longestPrefixOf(String word) {
        throw new UnsupportedOperationException("Not Implemented.");
    }

    @Override
    public String longestCommonPrefix() {
        throw new UnsupportedOperationException("Not Implemented.");
    }

    @Override
    public Iterable<String> allPrefixOf(String word) {
        throw new UnsupportedOperationException("Not Implemented.");
    }
}
