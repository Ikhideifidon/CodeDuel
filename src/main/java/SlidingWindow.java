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
}
