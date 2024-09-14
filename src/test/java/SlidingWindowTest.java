import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SlidingWindowTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void maximumSumSubarray() {
        int[] nums = {-1, 2, 3, 1, -3, 2};
        int k = 4;
        System.out.println(Arrays.toString(SlidingWindow.maximumSumSubarray(nums, k)));
    }

    @Test
    void subarrayWithDesiredSum() {
        int[] nums = {1, 7, 4, 3, 1, 2, 1, 5, 1};
        int desired = 7;
        List<int[]> result = SlidingWindow.subarrayWithDesiredSum(nums, desired);
        for (int[] array : result)
            System.out.println(Arrays.toString(array));
    }

    @Test
    void subarrayWithMaximumContinuousOnes() {
        int[] nums = {0, 1, 0, 1, 0, 0, 1, 1};
        int flip = 3;
        System.out.println(Arrays.toString(SlidingWindow.subarrayWithMaximumContinuousOnes(nums, flip)));
    }

    @Test
    void continuousOnes() {
        int[] nums = {0, 1, 0, 1, 0, 0, 1, 1};
        int flip = 3;
        System.out.println(SlidingWindow.continuousOnes(nums, flip));
    }

    @Test
    void shortestSubstring() {
        String s = "fa4achba4c";
        String word = "abcc";
        System.out.println(SlidingWindow.shortestSubstring(s, word));
    }

    @Test
    void lengthOfLongestSubstring() {
        String s = "aabbbac";
        System.out.println(SlidingWindow.lengthOfLongestSubstring(s));
    }

    @Test
    void findSubstring() {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(SlidingWindow.findSubstring(s, words));
    }

    @Test
    void minWindow() {
        String s = "FAADOBECBDEBANC";
        String t = "ABBC";
        System.out.println(SlidingWindow.minWindow(s, t));

    }
}
