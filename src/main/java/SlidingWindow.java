import java.util.*;

public class SlidingWindow {
    public static int[] maximumSumSubarray(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 ||    k > n || k == 0)
            return new int[] {};

        int sum = 0;
        int maximum = Integer.MIN_VALUE;
        int left = 0;
        int start = 0;
        int right = 0;

        while (right < n) {
            sum += nums[right];
            if (right >= k - 1) {
                if (maximum < sum) {
                    maximum = sum;
                    start = left;
                }
                sum -= nums[left++];
            }
            right++;
        }
        return Arrays.copyOfRange(nums, start, start + k);
    }

    public static List<int[]> subarrayWithDesiredSum(int[] nums, int desired) {
        int n = nums.length;
        if (n == 0)
            return new ArrayList<>();

        List<int[]> result = new ArrayList<>();
        int[] temp;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < n) {
            sum += nums[right];
            while (sum > desired && left <= right)
                sum -= nums[left++];

            if (sum == desired) {
                temp = Arrays.copyOfRange(nums, left, right + 1);
                result.add(temp);
            }
            right++;
        }
        return result;
    }

    public static int continuousOnes(int[] nums, int flip) {
        int n = nums.length;
        int windowStart = 0;
        int windowLength = 0;
        int right = 0;

        // Widening the window
        while (right < n) {
            // If we encounter a 0 and can still flip, flip it.
            if (nums[right] == 0) {
                if (flip > 0)
                    flip--;
                else {
                    // When no flips are available, start shrinking the window.
                    while (nums[windowStart] != 0)
                        windowStart++;
                    // Move past the 0
                    windowStart++;
                }
            }
            // Update the maximum window length
            windowLength = Math.max(windowLength, right - windowStart + 1);
            right++;
        }
        return windowLength;
    }

    public static int[] subarrayWithMaximumContinuousOnes(int[] nums, int flip) {
        int n = nums.length;
        int start = 0;
        int end = 0;
        int windowStart = 0;
        int windowLength = 0;
        int right = 0;

        // Widening the window
        while (right < n) {
            // If we encounter a 0 and can still flip, flip it.
            if (nums[right] == 0) {
                if (flip > 0)
                    flip--;
                else {
                    // When no flips are available, start shrinking the window.
                    while (nums[windowStart] != 0)
                        windowStart++;
                    // Move past the 0
                    windowStart++;
                }
            }
            // Update the maximum window length
            if (windowLength < right - windowStart + 1) {
                windowLength = right - windowStart + 1;
                start = windowStart;
                end = right;
            }
            right++;
        }
        return Arrays.copyOfRange(nums, start, end + 1);
    }

    public static String shortestSubstring(String s, String word) {
        int n = s.length();
        int m = word.length();

        if (n == 0 || m > n)
            return "";

        Map<Character, Integer> requiredCharacters = new HashMap<>();                       // Frequency of all required characters in word
        for (char character : word.toCharArray())
            requiredCharacters.put(character, requiredCharacters.getOrDefault(character, 0) + 1);

        int windowStart = 0, windowLength = Integer.MAX_VALUE, start = 0;
        int matched = 0;                // Keep track of matched characters
        Map<Character, Integer> windowCharacters = new HashMap<>();                        // Frequency of all required characters in the valid window

        // Expand the window with the right pointer
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            // Include the current character
            if (requiredCharacters.containsKey(c)) {
                windowCharacters.put(c, windowCharacters.getOrDefault(c, 0) + 1);

                // Only increase matched count when window contains enough of characters
                if (windowCharacters.get(c).intValue() == requiredCharacters.get(c).intValue())
                    matched++;
            }

            // Shrink the window when all characters are matched
            while (matched == requiredCharacters.size()) {
                // Update the shortest valid window
                if (right - windowStart + 1 < windowLength) {
                    windowLength = right - windowStart + 1;
                    start = windowStart;
                }

                // Shrink from the left
                char leftCharacter = s.charAt(windowStart);
                windowStart++;

                if (requiredCharacters.containsKey(leftCharacter)) {
                    if (windowCharacters.get(leftCharacter).intValue() == requiredCharacters.get(leftCharacter).intValue())
                        matched--;
                    windowCharacters.put(leftCharacter, windowCharacters.get(leftCharacter) - 1);
                }
            }
        }
        return windowLength == Integer.MAX_VALUE ? "" : s.substring(start, start + windowLength);
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int windowStart = 0;
        int windowLength = 0;
        Map<Character, Integer> unique = new HashMap<>();

        // Expand the Window
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            // If the character is already in the map, move the windowStart to the right of the previous occurrence
            if (unique.containsKey(c))
                // Move windowStart to max of its current position and one right of the last occurrence.
                windowStart = Math.max(windowStart, unique.get(c) + 1);

            // Update the latest position of the current character
            unique.put(c, right);

            // Update the maximum window length
            windowLength = Math.max(windowLength, right - windowStart + 1);
        }
        return windowLength;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        // s = "pbarfoofoobarmanpbarbarfoo", words = ["foo", "bar"]
        int n = s.length();
        int m = words.length;
        int eachWordLength = words[0].length();
        int totalWindowLength = m * eachWordLength;

        if (eachWordLength > n || totalWindowLength > n)
            return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        Map<String, Integer> wordRequired = new HashMap<>();

        // Build the word frequency map from the "words" array
        for (String word : words)
            wordRequired.put(word, wordRequired.getOrDefault(word, 0) + 1);

        // Iterate over each possible start point
        for (int i = 0; i < eachWordLength; i++) {
            int left = i;
            Map<String, Integer> seenWords = new HashMap<>();
            int matchedWords = 0;

            for (int right = i; right <= n - eachWordLength; right += eachWordLength) {
                String currentWord = s.substring(right, right + eachWordLength);

                if (wordRequired.containsKey(currentWord)) {
                    seenWords.put(currentWord, seenWords.getOrDefault(currentWord, 0) + 1);
                    matchedWords++;

                    // If the word count exceeds the required, shrink the window
                    while (seenWords.get(currentWord) > wordRequired.get(currentWord)) {
                        String leftWord = s.substring(left, left + eachWordLength);
                        seenWords.put(leftWord, seenWords.get(leftWord) - 1);
                        matchedWords--;
                        left += eachWordLength;
                    }

                    // If we've matched all words, record the start index
                    if (matchedWords == m)
                        result.add(left);
                } else {
                    // Reset the window if a non-matching word is found
                    seenWords.clear();
                    matchedWords = 0;
                    left = right + eachWordLength;
                }
            }
        }
        return result;
    }

    public static String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty())
            return "";

        // Frequency map of characters in t
        Map<Character, Integer> tCharacters = new HashMap<>();
        for (char character : t.toCharArray())
            tCharacters.put(character, tCharacters.getOrDefault(character, 0) + 1);

        int left = 0, matched = 0, minLength = Integer.MAX_VALUE;
        int startIndex = 0; // The start index of the minimum window
        Map<Character, Integer> windowChars = new HashMap<>();

        // Expand the window with the right pointer
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If the character is part of t, add it to the window
            if (tCharacters.containsKey(c)) {
                windowChars.put(c, windowChars.getOrDefault(c, 0) + 1);

                // Only increment 'matched' if the frequency in window matches t's frequency
                if (windowChars.get(c).intValue() == tCharacters.get(c).intValue())
                    matched++;
            }

            // Shrink the window from the left until it's no longer valid
            while (matched == tCharacters.size()) {
                // Update the minimum window
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startIndex = left;
                }

                // Try to remove the left character to shrink the window
                char leftChar = s.charAt(left);
                if (tCharacters.containsKey(leftChar)) {
                    if (windowChars.get(leftChar).intValue() == tCharacters.get(leftChar).intValue())
                        matched--;
                    windowChars.put(leftChar, windowChars.get(leftChar) - 1);
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLength);
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        // s = "s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
        // s = "AAAAAGGGGGTGAAAAAGGGGG"
        if (s == null || s.isEmpty())
            return new ArrayList<>();

        final Map<String, Integer> validSequence = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i <= s.length() - 10 ; i++) {
            String temp = s.substring(i, i + 10);
            validSequence.put(temp, validSequence.getOrDefault(temp, 0) + 1);

            // Do we have this "temp" at least twice?
            if (validSequence.get(temp) == 2)
                result.add(temp);
        }
        return result;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0;
        int windowLength = Integer.MAX_VALUE;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];
            // is sum >= target?
            while (left <= right && sum >= target) {                    // A valid window is found
                // Let's attempt to shrink the window
                if (windowLength > right - left + 1)
                    windowLength = right - left + 1;
                // Remove the element at "left"
                sum -= nums[left];
                left++;
            }
        }
        return windowLength == Integer.MAX_VALUE ? 0 : windowLength;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        // nums = [1, 2, 3, 1, 2, 3], k = 2
        // nums = [1, 1], k = 5
        int n = nums.length;
        if (n == 1 || k == 0)
            return false;

        Map<Integer, Integer> frequency = new HashMap<>();

        // Sliding window approach
        for (int right = 0; right < n; right++) {
            // If the element already exists in the map and is within the distance of k
            if (frequency.containsKey(nums[right]) && right - frequency.get(nums[right]) <= k)
                return true;

            // Update the map with the current index of the element
            frequency.put(nums[right], right);
        }
        return false;
    }

    public static int constrainedSubsetSum(int[] nums, int k) {
        if (nums == null || nums.length == 1)
            return Integer.MIN_VALUE;

        int n = nums.length;
        int globalMaximum = nums[0];
        int currentMaximum;
        Comparator<int[]> comp = (o1, o2) -> {
            if (o1[0] < o2[0])
                return 1;
            else if (o1[0] > o2[0])
                return -1;
            return 0;
        };

        // Comparator<int[]> comp = (o1, o2) -> Integer.compare(o2[0], o1[0])
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(comp);
        maxHeap.offer(new int[] {nums[0], 0});

        for (int i = 1; i < n; i++) {
            // Out of k window of length k + 1
            while (!maxHeap.isEmpty() && i - maxHeap.peek()[1] > k)
                maxHeap.poll();
            assert maxHeap.peek() != null;
            currentMaximum = Math.max(nums[i], nums[i] + maxHeap.peek()[0]);
            globalMaximum = Math.max(globalMaximum, currentMaximum);
            maxHeap.offer(new int[] {currentMaximum, i});
        }
        return globalMaximum;
    }

    // Time Complexity = O(n * indexDiff)
    public static boolean containsNearbyAlmostDuplicateBruteForce(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length == 0)
            return false;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && j <= i + indexDiff; j++) {
                if (Math.abs(i - j) <= indexDiff)
                    if (Math.abs(nums[i] - nums[j]) <= valueDiff)
                        return true;
            }
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicateOptimized(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || indexDiff == 0 || nums.length == 1)
            return false;

        int n = nums.length;
        TreeSet<Integer> window = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            /*\
            Condition 1:
            // Maintain the sliding window size of at most indexDiff elements
             */
            if (i > indexDiff)
                // Remove the leftmost element from the window.
                window.remove(nums[i - indexDiff - 1]);
            /*
            Condition 2:
            abs(nums[i] - nums[j]) <= valueDiff
            |(nums[i] - nums[j])| <= valueDiff

            nums[j] >= nums[i] - valueDiff...............(1)
            nums[j] <= nums[i] + valueDiff...............(2)
             */
            // Is there any value in window such that the value >= nums[i] - valueDiff?
            Integer lowerBound = window.ceiling(nums[i] - valueDiff);

            // If this value exists, is it <= nums[i] + valueDiff?
            if (lowerBound != null && lowerBound <= nums[i] + valueDiff)
                return true;

            // Add the current element to the Sliding window;
            window.add(nums[i]);
        }
        return false;

    }

    public static int longestSubstring(String s, int k) {
        if (s == null)
            return 0;
        int n = s.length();
        if (n == 0 || k > n)
            return 0;
        if (k == 0)
            return n;

        // Frequency map of characters in s
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : s.toCharArray())
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);

        int left = 0;
        while (left < n && frequency.get(s.charAt(left)) >= k)
            left++;

        // All elements in s occur at least k times
        if (left == n)
            return left;

        // All elements in s before left occur at least k times
        int leftResult = longestSubstring(s.substring(0, left), k);

        // Check if all elements immediately after left occur less than k times
        left++;
        while (left < n && frequency.get(s.charAt(left)) < k)
            left++;

        // Check if all elements immediately after left occur at least k times
        int rightResult = longestSubstring(s.substring(left, n), k);

        return Math.max(leftResult, rightResult);
    }


}
