import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AlgorithmTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void merge() {
        int[] nums1 = new int[] {0};
        int[] nums2 = new int[] {1};
        Algorithm.merge(nums1, 0, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }

    @Test
    void removeDuplicates() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(Algorithm.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    @Test
    void removeDuplicatesII() {
        int[] nums = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
        System.out.println(Algorithm.removeDuplicatesII(nums));
        System.out.println(Arrays.toString(nums));
    }

    @Test
    void majorityElement() {
        int[] nums = {1,3,1,1,4,1,1,5,1,1,6,2,2};
        System.out.println(Algorithm.majorityElementMethod2(nums));
        System.out.println(Algorithm.majorityElement(nums));
    }

    @Test
    void rotateI() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
        int[] result = {6, 7, 8, 1, 2, 3, 4, 5};
        Algorithm.rotate(nums, k);
        Assertions.assertEquals(Arrays.toString(result), Arrays.toString(nums));
    }

    @Test
    void rotateII() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
        int[] result = {6, 7, 8, 1, 2, 3, 4, 5};
        Algorithm.rotateMethod2(nums, k);
        Assertions.assertEquals(Arrays.toString(result), Arrays.toString(nums));
    }

    @Test
    void rotateIII() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
        int[] result = {6, 7, 8, 1, 2, 3, 4, 5};
        Algorithm.rotateMethod3(nums, k);
        Assertions.assertEquals(Arrays.toString(result), Arrays.toString(nums));
    }

    @Test
    void maxProfit() {
        int[] prices = {6, 1, 3, 2, 4, 7};
        int answer = 6;
        Assertions.assertEquals(answer, Algorithm.maxProfit(prices));
    }

    @Test
    void maxProfitII() {
        int[] prices = {6, 1, 3, 2, 4, 7};
        int answer = 7;
        Assertions.assertEquals(answer, Algorithm.maxProfitII(prices));
    }

    @Test
    void canJump() {
        int[] nums = {3, 2, 1, 0, 4};
        boolean answer = true;
        Assertions.assertEquals(answer, Algorithm.canJumpRecursion(nums));
    }
}
