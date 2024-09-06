import java.util.HashMap;
import java.util.Map;

/*
    Leetcode Top Interview 150 Questions
 */
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

    }


}
