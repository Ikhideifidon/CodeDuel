import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
