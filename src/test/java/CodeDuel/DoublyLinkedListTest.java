package CodeDuel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;


class DoublyLinkedListTest {
    private static final int LOWER_BOUND = 15;
    private static final int UPPER_BOUND = 20;
    private static Random random;
    private static DoublyLinkedList<Integer> doublyLinkedList;
    private static Comparator<Integer> comparator;

    @BeforeAll
    static void initialize() {
        comparator = Comparator.naturalOrder();
        random = new Random(1);
        doublyLinkedList = new DoublyLinkedList<>(comparator, 14);
        int[] array = fisherYates();
        for (int num : array)
            doublyLinkedList.insertRight(num);
    }

    @Test
    void insertRight() {
        int lastElement = 77;
        doublyLinkedList.insertRight(lastElement);
        Assertions.assertEquals(lastElement, doublyLinkedList.last());
    }

    @Test
    void insertLeft() {
        int firstElement = 11;
        doublyLinkedList.insertLeft(firstElement);
        Assertions.assertEquals(firstElement, doublyLinkedList.first());
    }

    @Test
    void search() {
        int searchedValue = 1000;
        doublyLinkedList.insertRight(searchedValue);
        Assertions.assertTrue(doublyLinkedList.search(searchedValue));
    }

    @Test
    void delete() {
        int size = doublyLinkedList.size();
        int rand = random.nextInt(LOWER_BOUND, UPPER_BOUND + 1);
        boolean bool = doublyLinkedList.delete(rand);
        Assertions.assertEquals(size - 1, doublyLinkedList.size());
        Assertions.assertTrue(bool);
    }

    @Test
    void testDelete() {
        int firstElement = doublyLinkedList.first();
        doublyLinkedList.delete();
        Assertions.assertNotEquals(firstElement, doublyLinkedList.first());
    }

    @Test
    void replace() {
    }

    @Test
    void testReplace() {
        int size = doublyLinkedList.size();
        int firstElement = doublyLinkedList.first();
        doublyLinkedList.replace(10);
        Assertions.assertNotEquals(firstElement, doublyLinkedList.first());
        Assertions.assertEquals(size, doublyLinkedList.size());
    }

    @Test
    void reverse() {
        DoublyLinkedList<Integer> originalList = new DoublyLinkedList<>(comparator, doublyLinkedList);
        doublyLinkedList.reverse();
        Assertions.assertEquals(originalList.size(), doublyLinkedList.size());

        Iterator<Integer> reverseIterator = originalList.reverseIterator();
        for (Integer value : doublyLinkedList)
            assert value.equals(reverseIterator.next()) : "Value Mismatch!";
    }

    @Test
    void reversed() {
        DoublyLinkedList<Integer> originalList = new DoublyLinkedList<>(comparator, doublyLinkedList);
        DoublyLinkedList<Integer> reversedList = new DoublyLinkedList<>(comparator, doublyLinkedList);
        reversedList.reverse();
        String result = reversedList.toString();
        Assertions.assertEquals(result, doublyLinkedList.reversed().toString());
        Assertions.assertEquals(originalList.toString(), doublyLinkedList.toString());
    }

    @Test
    void extendLeft() {
        // 1 <---> 2 <---> 3 <---> 4 ---> null
        DoublyLinkedList<Integer> other = new DoublyLinkedList<>(comparator);
        int[] array = {52, 90, 32, 48};             // Test array
        for (int num : array)
            other.insertRight(num);
        StringBuilder sb = new StringBuilder(other.toString());
        int deleteSize = sb.length() - 10;
        sb.delete(deleteSize, sb.length());
        sb.append(" <---> ").append(doublyLinkedList.toString());
        doublyLinkedList.extendLeft(other);
        String result = sb.toString();
        Assertions.assertEquals(result, doublyLinkedList.toString());
    }

    @Test
    void extendRight() {
        // 1 <---> 2 <---> 3 <---> 4 ---> null
        DoublyLinkedList<Integer> other = new DoublyLinkedList<>(comparator);
        int[] array = {52, 90, 32, 48};
        for (int num : array)
            other.insertRight(num);
        StringBuilder sb = new StringBuilder(String.valueOf(doublyLinkedList));
        int deleteSize = sb.length() - 10;
        sb.delete(deleteSize, sb.length());
        sb.append(" <---> ").append(other);
        doublyLinkedList.extendRight(other);
        String result = sb.toString();
        Assertions.assertEquals(result, doublyLinkedList.toString());
    }

    private static int[] fisherYates() {
        int[] array = new int[UPPER_BOUND - LOWER_BOUND + 1];
        Arrays.setAll(array, i -> LOWER_BOUND + i);
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }

    @Test
    void size() {
        int size = fisherYates().length + 1;
        Assertions.assertEquals(size, doublyLinkedList.size());
    }

    @Test
    void test() {
        DoublyLinkedList<Integer> simple1 = new DoublyLinkedList<>(comparator);
        simple1.insertRight(4);
        simple1.insertRight(5);
        simple1.insertRight(6);
        System.out.println(simple1.hashCode());

        DoublyLinkedList<Integer> simple2 = new DoublyLinkedList<>(comparator);
        simple2.insertRight(4);
        simple2.insertRight(6);
        simple2.insertRight(5);
        System.out.println(simple2.hashCode());

        DoublyLinkedList<Integer> simple3 = new DoublyLinkedList<>(comparator);
        simple3.insertRight(4);
        simple3.insertRight(5);
        simple3.insertRight(6);
        System.out.println(simple3.hashCode());
        System.out.println(simple1.equals(simple2));
    }

}
