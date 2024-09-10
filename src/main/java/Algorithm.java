import java.util.*;

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

    static class RandomizedSet { ;
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

}
