package CodeDuel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(7 % 5);
        Trie trie = new Trie();

        // Insert lowercase and uppercase words
        trie.insert("flow");
        trie.insert("flower");
        trie.insert("flight");
        trie.insert("apple");
        trie.insert("App");
        trie.insert("Banana");
        trie.insert("BAN");
        trie.insert("she");
        trie.insert("appl");
        trie.insert("a");
        trie.insert("app");
        trie.insert("ap");
        trie.insert("appl");




        // Test search
        System.out.println(trie.search("apple"));  // true
        System.out.println(trie.search("App"));    // true
        System.out.println(trie.search("Banana")); // true
        System.out.println(trie.search("BAN"));    // true
        System.out.println(trie.search("ban"));    // false (case-sensitive)

        // Test prefix search
        System.out.println(trie.startsWith("Ap")); // true
        System.out.println(trie.startsWith("Ba")); // true
        System.out.println(trie.startsWith("bA")); // false (case-sensitive)
        System.out.println(trie.size());
        System.out.println(trie.isEmpty());
        System.out.println(trie.longestPrefixOf("shells"));
        System.out.println(trie.longestPrefixOf("application"));
        System.out.println(trie.allPrefixOf("application"));
//        System.out.println(trie.longestCommonPrefix());
        trie.printTrie();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(List.of("cat","cats", "and", "sand", "dog"));
        System.out.println(Algorithm.wordBreakSolution2(s, wordDict));
        List<String> sample = new ArrayList<>();
        for (int i = 1; i <= 21; i++)
            sample.add(String.valueOf(i));
//        String[] a = Arrays.toString(sample).split("[\\[\\]]")[1].split(", ");
        System.out.println(sample);
        System.out.println('2' - '1');
    }
}
