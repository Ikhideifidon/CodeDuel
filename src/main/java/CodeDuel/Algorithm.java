package CodeDuel;

import java.util.*;

/*
    Leetcode Top Interview 150 Questions
 */
@SuppressWarnings({"unused", "CommentedOutCode"})
public class Algorithm {

    public static void merge(int[]  nums1, int m, int[] nums2, int n) {
        int i = m - 1;      // Last non-zero element
        int j = n - 1;      // Last element
        int k = m + n - 1;  // Last position in nums1
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j])
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }

        // If there are remaining elements in nums2, add them to nums1
        while (j >= 0)
            nums1[k--] = nums2[j--];
    }

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 1;
        int i = 0;
        for (int j = 1; j < n; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static int removeDuplicatesII(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 1;

        int i = 0;
        int j = 1;
        while (j < n) {
            if (nums[i] == nums[j]) {
                while (j < n - 1 && nums[j] == nums[j + 1])
                    j++;
            }
            i++;
            nums[i] = nums[j];
            j++;
        }
        return i + 1;
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums)
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);

        int majorityCount = 0;
        int majorKey = nums[0];
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int key = entry.getKey();
            // Is the count of this key > than the majorityCount ???
            if (majorityCount < frequency.get(key)) {
                majorityCount = frequency.get(key);
                majorKey = key;
            }
        }
        return majorityCount > nums.length / 2 ? majorKey : 0;
    }

    public static int majorityElementMethod2(int[] nums) {
        int majorityElement = 0;            // Can be any integer
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                majorityElement = num;
                count++;
            }

            else if (majorityElement == num)
                count++;

            else
                count--;
        }
        return majorityElement;
    }

    // Time Complexity = O(kn) and Space Complexity = O(1)
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 1 || k == 0 || k == n)
            return;

        if (k > n)
            k = k % n;

        int i;
        while (k > 0) {
            i = n - 1;
            int current = nums[i];
            while (i > 0) {
                nums[i] = nums[i - 1];
                i--;
            }
            nums[i] = current;
            k--;
        }
    }

    // Time Complexity = O(n) and Space Complexity = O(n)
    public static void rotateMethod2(int[] nums, int k) {
        int n = nums.length;
        if (n == 1 || k == 0 || k == n)
            return;

        k = k % n;
        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            result[(i + k) % n] = nums[i];
            i++;
        }
        System.arraycopy(result, 0, nums, 0, result.length);
    }

    // Time Complexity = O(n) and Space Complexity = O(1)
    public static void rotateMethod3(int[] nums, int k) {
        int n = nums.length;
        if (n == 1 || k == 0 || k == n)
            return;

        k = k % n;
        int start = 0;
        int end = n - 1;
        reverse(nums, start, end);              // Reverse the whole array
        end = k - 1;
        reverse(nums, start, end);              // Reverse the first k - 1 array
        start = k;
        end = n - 1;
        reverse(nums, start, end);              // Reverse the last k array
    }

    private static void reverse(int[] nums, int start, int end) {
        if (start >= end)
            return;
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    public static int maxProfit(int[] prices) {
        int leastBuyingPrice = Integer.MAX_VALUE;
        int maxGain = 0;
        for (int price : prices) {
           if (price < leastBuyingPrice)
               leastBuyingPrice = price;
           maxGain = Math.max(maxGain, price - leastBuyingPrice);
        }
        return maxGain;
    }

    public static int maxProfitII(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }

    // **************************************************** START canJump *********************************************
    public static boolean canJumpGreedy(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return true;
        int i = n - 2;
        int destination = n - 1;
        while (i >= 0) {
            if (nums[i] >= (destination - i))
                destination = i;
            i--;
        }
        return destination == 0;
    }

    public static boolean canJumpRecursion(int[] nums) {
        return canJumpHelper(nums, 0);
    }

    private static boolean canJumpHelper(int[] nums, int start) {
        if (start == nums.length - 1)           // Last Index of nums
            return true;

        if (start >= nums.length)
            return false;                       // Out of bounds

        for (int step = 1; step <= nums[start]; step++) {
            if (canJumpHelper(nums, start + step))
                return true;
        }
        return false;
    }

    public static boolean canJumpMemoized(int[] nums) {
        return canJumpMemoizedHelper(nums, 0, new boolean[10_001]);
    }

    private static boolean canJumpMemoizedHelper(int[] nums, int start, boolean[] memo) {
        if (start == nums.length - 1)
            return true;

        if (start >= nums.length)
            return false;

        if (memo[start])
            return memo[start];

        for (int step = 1; step <= nums[start]; step++) {
            if (canJumpMemoizedHelper(nums, start + step, memo)) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }

    public static boolean canJumpBottomUp(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;                   // The first element is always reachable.

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {                      // Can the second element be reached form the first element???
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
    // **************************************************** END canJump ***********************************************


    // **************************************************** START jump *********************************************
    public static int jumpRecursive(int[] nums) {
        return jumpRecursiveHelper(nums, 0);
    }

    private static int jumpRecursiveHelper(int[] nums, int start) {
        if (start >= nums.length - 1)
            return 0;

        int minJump = Integer.MAX_VALUE;

        // Explore all possible jumps from current index.
        for (int step = 1; step <= nums[start]; step++) {
            int jumps = jumpRecursiveHelper(nums, start + step);
            if (jumps != Integer.MAX_VALUE)
                minJump = Math.min(minJump, 1 + jumps);
        }
        return minJump;
    }

    public static int jumpMemoized(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return jumpMemoizedHelper(nums, 0, memo);
    }

    private static int jumpMemoizedHelper(int[] nums, int start, int[] memo) {
        if (start >= nums.length - 1)
            return 0;

        if (memo[start] != -1)
            return memo[start];

        int minJump = Integer.MAX_VALUE;

        // Explore all possible jumps from current index.
        for (int step = 1; step <= nums[start]; step++) {
            int jumps = jumpMemoizedHelper(nums, start + step, memo);
            if (jumps != Integer.MAX_VALUE)
                minJump = Math.min(minJump, 1 + jumps);
        }
        memo[start] = minJump;
        return memo[start];
    }

    public static int jumpGreedy(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int jumps = 0;
        int currentEnd = 0;
        int currentFarthestIndex = 0;
        for (int currentBegin = 0; currentBegin < n - 1; currentBegin++) {
            currentFarthestIndex = Math.max(currentFarthestIndex, currentBegin + nums[currentBegin]);
            if (currentBegin == currentEnd) {
                jumps++;
                currentEnd = currentFarthestIndex;
            }
        }
        return jumps;
    }

    public static int jumpDynamicProgramming(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            // For each index i, check all possible positions we can jump to.
            for (int j = i + 1; j <= i + nums[i] && j < n; j++)
                dp[j] = Math.min(dp[j], dp[i] + 1);
        }
        return dp[n - 1];
    }
    // **************************************************** End jump ***************************************************

    // **************************************************** START hIndex ***********************************************
    public static int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);

        int hIndex = 0;
        for(int i = 0; i < n; i++) {
            int h = n - i;
            if (citations[i] >= h) {
                hIndex = h;
                break;
            }
        }
        return hIndex;
    }

    public static int hIndexOptimized(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n + 1];

        for (int citation : citations) {
            if (citation < n)
                bucket[citation]++;
            else
                bucket[n]++;
        }

        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[i];
            if (i == count)
                break;
        }
        return count;
    }

    static class RandomizedSet {
        private final Map<Integer, Integer> unique;
        private final List<Integer> container;
        private int available;
        private final Random random;

        public RandomizedSet() {
            this.container = new ArrayList<>();
            this.unique = new HashMap<>();
            this.available = 0;
            this.random = new Random();
        }

        public boolean insert(int val) {
            if (!unique.containsKey(val)) {
                unique.put(val, available);
                container.add(val);
                available++;
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (unique.containsKey(val)) {
                int index = unique.get(val);

                int lastElement = container.get(available - 1);
                container.set(index, lastElement);
                unique.put(lastElement, index);                 // Update the index of the swapped last element.

                unique.remove(val);
                available--;
                return true;
            }
            return false;
        }

        public int getRandom() {
            return container.get(random.nextInt(available));
        }
    }

     /*
    class RandomizedSet {
        private final Map<Integer, Integer> unique;
        private final List<Integer> container;
        private final Random random;

        public RandomizedSet() {
            this.container = new ArrayList<>();
            this.unique = new HashMap<>();
            this.random = new Random();
        }

        public boolean insert(int val) {
            if (!unique.containsKey(val)) {
                unique.put(val, container.size());
                container.add(val);
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (unique.containsKey(val)) {
                int index = unique.get(val);

                if (index < container.size() - 1) {
                    int lastElement = container.get(container.size() - 1);
                    container.set(index, lastElement);
                    unique.put(lastElement, index);
                }// Update the index of the swapped last element.

                unique.remove(val);
                container.remove(container.size() - 1);
                return true;
            }
            return false;
        }

        public int getRandom() {
            return container.get(random.nextInt(container.size()));
        }
    }
     */

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int left = 1;
        // Perform Leftwards prefix product except itself
        for (int i = 0; i < n; i++) {
            if (i > 0)
                left *= nums[i - 1];
            result[i] = left;
        }

        int right = 1;
        // Perform Rightwards suffix product except itself
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1)
                right *= nums[i + 1];
            result[i] *= right;
        }
        return result;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int startIndex = 0;
        int totalGas = 0;
        int totalCost = 0;
        int fuel = 0;

        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            fuel += gas[i] - cost[i];

            if (fuel < 0) {
                startIndex = i + 1;
                fuel = 0;
            }
        }
        return (totalGas >= totalCost) ? startIndex : -1;
    }

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);

        // Left to Right Pass
        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i])
                result[i] = result[i - 1] + 1;
        }
        // Right to Left Pass
        int sum = result[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                result[i] = Math.max(result[i], result[i + 1] + 1);
            sum += result[i];
        }
        return sum;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        Trie trie = new Trie();
        for (String word : strs)
            trie.insert(word);

        return trie.search(strs[0], strs.length);
    }

    // ..........................Trie class starts...................................
    private static class Trie {
        private final TrieNode root;
        private static final int R = 26;

        // Lightweight TrieNode class
        private static class TrieNode {
            private final TrieNode[] children;
            private boolean isEnd;
            private int size;

            private TrieNode() {
                children = new TrieNode[R];
                isEnd = false;
                size = 0;
            }
        }

        private Trie() {
            root = new TrieNode();
        }

        private void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null)
                    node.children[index] = new TrieNode();

                node.children[index].size++;
                node = node.children[index];
            }
            node.isEnd = true;
        }

        private String search(String word, int n) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';

                if (node.children[index] != null && node.children[index].size == n)
                    node = node.children[index];
                else
                    return word.substring(0, i);
            }
            return word;
        }
    }
    //..........................................Trie class Ends..............................

    public boolean wordBreakTrie(String s, List<String> wordDict) {
        if (s == null)
            return false;
        if (s.isEmpty())
            return true;

        Trie trie = new Trie();
        for (String word : wordDict)
            trie.insert(word);

        return dfs(s, 0, new HashMap<>(), trie);
    }

    private boolean dfs(String s, int start, Map<Integer, Boolean> memo, Trie trie) {
        if (start == s.length())
            return true;

        if (memo.containsKey(start))
            return memo.get(start);

        Trie.TrieNode node = trie.root;
        // Try every possible break point starting from 'start'
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';

            if (node.children[index] == null)
                break;

            node = node.children[index];
            // If a valid word is found in the trie, recursively check the remaining part of the string
            if (node.isEnd) {
                if (dfs(s, i + 1, memo, trie)) {
                    memo.put(start, true);
                    return true;
                }
            }
        }

        // No valid word break found
        memo.put(start, false);
        return false;
    }


    public boolean wordBreakRecursion(String s, List<String> wordDict) {
        if (s == null)
            return false;
        if (s.isEmpty())
            return true;
        return wordBreakHelper(s, wordDict, new HashMap<>());
    }

    private boolean wordBreakHelper(String s, List<String> wordDict, HashMap<String, Boolean> memo) {
        if (memo.containsKey(s))
            return memo.get(s);

        if (s.isEmpty())
            return true;

        for (String word : wordDict) {
            // Does 'word' exist in s? If yes, is it a prefix or a suffix?
            if (s.indexOf(word) == 0) {                     // Prefix check
                String suffix = s.substring(word.length());
                boolean result = wordBreakHelper(suffix, wordDict, memo);
                if (result) {
                    memo.put(s, true);
                    return true;
                }
            }
        }

        memo.put(s, false);
        return false;
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty())
            return new ArrayList<>();
        List<String> result = new ArrayList<>();
        wordBreakHelper(s, wordDict, result, new StringBuilder());
        return result;
    }

    private static void wordBreakHelper(String s, List<String> wordDict, List<String> result, StringBuilder sb) {
        if (s.isEmpty())
            result.add(new String(sb));

        for (String word : wordDict) {
            // Is word a prefix of "s"?
            if (s.indexOf(word) == 0) {
                int lenBeforeAppend = sb.length(); // Store the length before appending
                String suffix = s.substring(word.length());

                sb.append(word).append(suffix.isEmpty() ? "" : " ");

                wordBreakHelper(suffix, wordDict, result, sb);
                sb.setLength(lenBeforeAppend);
            }
        }
    }

    public static List<String> wordBreakSolution2(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty())
            return new ArrayList<>();
        return wordBreakHelperII(s, wordDict, new HashMap<>());
    }

    private static List<String> wordBreakHelperII(String s, List<String> wordDict, Map<String, List<String>> memo) {
        if (memo.containsKey(s))
            return memo.get(s);

        List<String> result = new ArrayList<>();
        for (String word : wordDict) {
            if (s.indexOf(word) == 0) {
                String suffix = s.substring(word.length());
                if (suffix.isEmpty())
                    result.add(word);
                else {
                    List<String> suffixDecomposition = wordBreakHelperII(suffix, wordDict, memo);
                    for (String sub : suffixDecomposition)
                        result.add(word + " " + sub);
                }
            }
        }
        memo.put(s, result);
        return result;
    }

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;

        List<String> result = new ArrayList<>();
        final int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        Trie trie = new Trie();

        for (String word : words)
            trie.insert(word);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, result, trie.root, new StringBuilder(), new HashSet<>(), dirs);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int row, int col, List<String> result, Trie.TrieNode node, StringBuilder word, Set<String> foundWords, int[][] directions) {
        // Check for out of bounds or visited cell
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '$')
            return;

        int index = board[row][col] - 'a';
        if (node.children[index] == null)
            return;

        node = node.children[index];            // Move to the next trie node
        word.append(board[row][col]);

        // If a valid word is found
        if (node.isEnd && !foundWords.contains(word.toString())) {
            result.add(word.toString());
            foundWords.add(word.toString()); // Avoid duplicates
        }

        // Mark the cell as visited
        char temp = board[row][col];
        board[row][col] = '$';

        // Explore all directions
        for (int[] direction : directions) {
            int x = row + direction[0];
            int y = col + direction[1];
            dfs(board, x, y, result, node, word, foundWords, directions);
        }

        // Restore the cell and the word after exploring
        board[row][col] = temp;
        word.deleteCharAt(word.length() - 1); // Remove the last character
    }

    // 386. Lexicographical Numbers
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            dfs(n, i, result);
        return result;
    }

    private void dfs(int n, int current, List<Integer> result) {
        if (current > n)
            return;

        result.add(current);
        for (int i = 0; i <= n; i++) {
            int next = current * 10 + i;
            if (next > n)
                break;
            dfs(n, next, result);
        }
    }

    
    public List<Integer> lexicalOrderOptimized(int n) {
         List<Integer> result = new ArrayList<>();
         int current = 1;

         for (int i = 0; i < n; i++) {
             result.add(current);

             if (current * 10 <= n)
                 current *= 10;

             else {
                 if (current >= n)
                     current /= 10;

                 current++;
                 while (current % 10 == 0)
                     current /= 10;
             }
         }
         return result;
    }

    static class NumberTrie {
        private static final int R = 10;
        private final NumberTrieNode root;

        // Lightweight NumberTrieNode class
        private static class NumberTrieNode {
            private final NumberTrieNode[] children;
            private boolean isEnd;

            private NumberTrieNode() {
                children = new NumberTrieNode[R];
                isEnd = false;
            }
        }

        private NumberTrie() { root = new NumberTrieNode(); }

        public void insert(int num) {
            NumberTrieNode node = root;
            String numString = String.valueOf(num);
            for (char c : numString.toCharArray()) {
                int index = c - '0';
                if (node.children[index] == null)
                    node.children[index] = new NumberTrieNode();

                node = node.children[index];
            }
            node.isEnd = true;
        }

        private void dfs(List<Integer> result, NumberTrieNode node, StringBuilder path) {
            if (node.isEnd)
                result.add(Integer.parseInt(path.toString()));

            for (int i = 0; i < NumberTrie.R; i++) {
                if (node.children[i] != null) {
                    path.append(i);
                    dfs(result, node.children[i], path);
                    path.deleteCharAt(path.length() - 1);
                }
            }
        }
    }

    public List<Integer> lexicalOrderTrie(int n) {
        NumberTrie trie = new NumberTrie();
        for (int i = 1; i <= n; i++)
            trie.insert(i);

        List<Integer> result = new ArrayList<>();
        trie.dfs(result, trie.root, new StringBuilder());
        return result;
    }

    static class MapSum {
        private final static int R = 26;
        private final TrieNode root;
        private final Map<String, Integer> map;
        // Lightweight class
        private static class TrieNode {
            private final TrieNode[] children;
            private boolean isEnd;
            private int val;

            private TrieNode() {
                children = new TrieNode[R];
                isEnd = false;
                val = 0;
            }
        }

        private MapSum() {
            root = new TrieNode();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);

            MapSum.TrieNode node = root;
            for (char c : key.toCharArray()) {
                int index = c - 'a';

                if (node.children[index] == null)
                    node.children[index] = new TrieNode();

                node = node.children[index];
                node.val += delta;
            }
            node.isEnd = true;
        }

        public int sum(String prefix) {
            MapSum.TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';

                if (node.children[index] == null)
                    return 0;

                node = node.children[index];
            }
            return node.val;
        }
    }

    // Definition for singly-linked list.
    public static class ListNode {
        private int val;
        private ListNode next = null;

        private ListNode() {}
        private ListNode(int val) { this.val = val; }
        private ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        final ListNode dummy = new ListNode(0, head);
        ListNode previousGroup = dummy;

        while (true) {
            ListNode kthNode = getKthNode(previousGroup, k);
            if (kthNode == null)
                break;

            ListNode nextGroup = kthNode.next;
            ListNode previous = kthNode.next;
            ListNode current = previousGroup.next;
            /*
                            dummy  head  kthNode    previous
                                |    |         |    |
                                0--->1--->2--->3--->4--->5--->6--->7--->null
                                |    |              |
                     previousGroup  current    nextKthGroup
             */

            // Reverse the group of k nodes
            while (current != nextGroup.next) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            // Reconnect the previous group with the newly reversed group
            ListNode next = previousGroup.next;
            previousGroup.next = kthNode;
            previousGroup = next;
        }
        return dummy.next;
    }

    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0){
            start = start.next;
            k--;
        }
        return start;
    }

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1)
            return;
        int n = matrix.length;

        // Reverse each column
        for (int i = 0; i < n / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[n - 1 - i];
            matrix[n - i - 1] = temp;
        }

        // Swap symmetrically
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null)
            return new ArrayList<>();

        if (matrix.length <= 1)
            return new ArrayList<>(List.of(matrix[0][0]));

        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        int direction = 1;

        while (left <= right && top <= bottom) {
            if (direction == 1) {
                for (int i = left; i <= right; i++)
                    result.add(matrix[top][i]);
                top++;
                direction++;
            }

            else if (direction == 2) {
                for (int i = top; i <= bottom; i++)
                    result.add(matrix[i][right]);
                right--;
                direction++;
            }

            else if (direction == 3) {
                for (int i = right; i >= left; i--)
                    result.add(matrix[bottom][i]);
                bottom--;
                direction++;
            }

            else if (direction == 4) {
                for (int i = bottom; i >= top; i--)
                    result.add(matrix[i][left]);
                left++;
                direction = 1;
            }
        }
        return result;
    }

    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int direction = 1;
        int num = 1;

        while (left <= right && top <= bottom) {
            if (direction == 1) {
                for (int i = left; i <= right; i++)
                    result[left][i] = num++;
                top++;
                direction++;
            }

            else if (direction == 2) {
                for (int i = top; i <= bottom; i++)
                    result[i][right] = num++;
                right--;
                direction++;
            }

            else if (direction == 3) {
                for (int i = right; i >= left; i--)
                    result[bottom][i] = num++;
                bottom--;
                direction++;
            }

            if (direction == 4) {
                for (int i = bottom; i >= top; i--)
                    result[i][left] = num++;
                left++;
                direction = 1;
            }
        }
        return result;
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        // Fill the first row if reachable
        for (int j = 1; j < n; j++)
            dp[0][j] = (obstacleGrid[0][j] == 1 || dp[0][j - 1] == 0) ? 0 : 1;

        // Fill the first column if reachable
        for (int i = 1; i < m; i++)
            dp[i][0] = (obstacleGrid[i][0] == 1 || dp[i - 1][0] == 0) ? 0 : 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;

                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int uniquePathsWithObstaclesOptimized(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];

        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int[] temp : obstacleGrid) {
            for (int j = 0; j < n; j++) {
                if (temp[j] == 1)
                    dp[j] = 0;
                else
                    dp[j] += j > 0 ? dp[j - 1] : 0;
            }
        }
        return dp[n - 1];
    }

    public static int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int walkableCount = 0;
        // Find the location of the start cell
        int[] start = new int[2];
        // Find the location of the end cell
        int[] end = new int[2];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Find start cell, end cell and walkableCount
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Starting cell
                if (grid[i][j] == 1)
                    start = new int[]{i, j};

                // Ending cell
                if (grid[i][j] == 2)
                    end = new int[] {i, j};

                // Possible walk over
                if (grid[i][j] != -1)
                    walkableCount++;
            }
        }
        // Start DFS from the start position
        return dfs(grid, start[0], start[1], end, 1, walkableCount, dirs);
    }

    private static int dfs(int[][] grid, int row, int col, int[] end, int visitedCount, int walkableCount, int[][] dirs) {
        // Out of bounds, obstacle, or already visited
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || grid[row][col] == -1 || grid[row][col] == 3)
            return 0;

        // If we reach the end cell
        if (row == end[0] && col == end[1])
            return visitedCount == walkableCount ? 1 : 0;

        // Mark the current cell as visited
        int temp = grid[row][col];
        grid[row][col] = 3;

        int paths = 0;
        for (int[] dir : dirs) {
            int x = row + dir[0];
            int y = col + dir[1];
            paths += dfs(grid, x, y, end, visitedCount + 1, walkableCount, dirs);
        }

        // Backtrack by un-marking the current cell
        grid[row][col] = temp;
        return paths;
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        return dfs(grid, 0, 0, new int[grid.length][grid[0].length]);
    }

    private static int dfs(int[][] grid, int row, int col, int[][] memo) {
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || grid[row][col] == -1)
            return Integer.MAX_VALUE;

        if (row == grid.length - 1 && col == grid[0].length - 1)
            return grid[row][col];

        if (memo[row][col] != 0)
            return memo[row][col];

        int right = dfs(grid, row, col + 1, memo);
        int down = dfs(grid, row + 1, col, memo);

        memo[row][col] = grid[row][col] + Math.min(right, down);
        return memo[row][col];
    }

    // O(log m * log n)
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0;
        int bottom = m - 1;

        while (top <= bottom) {
            int middle = top + (bottom - top) / 2;

            // Check if target is in this row
            if (matrix[middle][0] <= target && matrix[middle][n - 1] >= target) {
                // Check if target is at either extreme of this row.
                if (matrix[middle][0] == target || matrix[middle][n - 1] == target)
                    return true;

                int leftward = 0;
                int rightward = n - 1;

                while (leftward <= rightward) {
                    int mid = leftward + (rightward - leftward) / 2;
                    if (matrix[middle][mid] == target)
                        return true;
                    else if (matrix[middle][mid] < target)
                        leftward = mid + 1;
                    else
                        rightward = mid - 1;
                }
                return false;
            }

            else if (matrix[middle][n - 1] < target)
                top = middle + 1;
            else
                bottom = middle - 1;
        }
        return false;
    }

    // O(log m + n)
    // 2D flattening
    public static boolean searchMatrixOptimized(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int middleValue = matrix[middle / n][middle % n];

            if (target == middleValue)
                return true;
            else if (target > middleValue)
                left = middle + 1;
            else
                right = middle - 1;
        }
        return false;
    }

    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;

        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        // Check first Row zero
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check first Column zero
        for (int[] mat : matrix) {
            if (mat[0] == 0) {
                firstColumnZero = true;
                break;
            }
        }

        // Use row and column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero out based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        // Zero out first row if necessary
        if (firstRowZero) {
            for (int i = 0; i < n; i++)
                matrix[0][i] = 0;
        }

        // Zero out first column if necessary
        if (firstColumnZero) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }

    public static void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;

        int m = board.length;
        int n = board[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighborCounts = gameOfLifeDfs(board, i, j, dirs);

                if (board[i][j] == 1) {                 // Current cell is alive
                    if (liveNeighborCounts < 2 || liveNeighborCounts > 3)
                        board[i][j] = -1;               // alive but marked dead

                } else {                                // Current cell is dead
                    if (liveNeighborCounts == 3)
                        board[i][j] = -2;               // Dead but marked alive
                }
            }
        }

        // Finalize the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1)
                    board[i][j] = 0;

                else if (board[i][j] == -2)
                    board[i][j] = 1;
            }
        }
    }

    private static int gameOfLifeDfs(int[][] board, int row, int col, int[][] dirs) {
        int counts = 0;
        for (int[] dir : dirs) {
            int x = row + dir[0];
            int y = col + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                // Count original live cells (including temporarily marked -1 as live)
                if (board[x][y] == 1 || board[x][y] == -1)
                    counts++;
            }
        }
        return counts;
    }

    public static boolean wordPattern(String pattern, String s) {
        // Split the input string into words.
        String[] words = s.split(" ");

        // If the pattern length is not equal to the number of words, return false.
        if (pattern.length() != words.length)
            return false;

        // Two hashmaps to store the character to word mapping and word to character mapping.
        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        // Iterate through the pattern and corresponding words.
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // If the character is already mapped, check if it's mapped to the same word.
            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) {
                    return false;
                }
            } else {
                // If the word is already mapped to another character, return false.
                if (wordToChar.containsKey(word)) {
                    return false;
                }

                // Map the character to the word and vice versa.
                charToWord.put(c, word);
                wordToChar.put(word, c);
            }
        }

        // If we passed through all the mappings without mismatch, the pattern is valid.
        return true;
    }
}
