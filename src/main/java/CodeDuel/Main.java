package CodeDuel;

import java.util.*;

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
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
//        Algorithm.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(Algorithm.spiralOrder(matrix));
        System.out.println(Arrays.deepToString(Algorithm.generateMatrix(12)));

        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(Algorithm.uniquePathsWithObstacles(obstacleGrid));

        int[][] paths = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        };
        System.out.println(Algorithm.uniquePathsIII(paths));

        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(Algorithm.minPathSum(grid));

        int[][] matrix2 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        System.out.println(Algorithm.searchMatrix(matrix2, target));

        int[][] zeroes = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        Algorithm.setZeroes(zeroes);
        System.out.println(Arrays.deepToString(zeroes));

        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        Algorithm.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));

        String matchedWord = "dog cat cat dog";
        String pattern = "abba";
        System.out.println(Algorithm.wordPattern(pattern, matchedWord));


        String pattern1 = "ABCDABCY";
        String s1 = "ABCXABCDABXABCDABCDABCY";
        System.out.println(Algorithm.substringSearchKMP(pattern1, s1));
        System.out.println(Algorithm.substringSearch(pattern1, s1));
        {
            int[] nums = {5, 12, 15, 16, 18, 21, 25};
            int k = 4;
            int x = 19;
            System.out.println(Algorithm.findClosestElements(nums, k, x));
        }
        System.out.println(Algorithm.getMoneyAmountMemoization(20));
        System.out.println(Algorithm.getMoneyAmountBottomUp(20));
//        System.out.println(Algorithm.getMoneyAmountRecursion(50));
        System.out.println(Algorithm.longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));

        {
            int[][] intervals = {
                    {1, 3},
                    {2, 6},
                    {8, 10},
                    {15, 18}
            };
            System.out.println(Arrays.deepToString(Algorithm.merge(intervals)));
        }

        {
            int[][] intervals = {
                    {1, 2},
                    {3, 5},
                    {6, 7},
                    {8, 10},
                    {12, 16}
            };
            int[] newInterval = {4, 8};
            System.out.println(Arrays.deepToString(Algorithm.insert(intervals, newInterval)));
        }




    }
}
