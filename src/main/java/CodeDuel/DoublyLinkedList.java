package CodeDuel;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T> implements Iterable<T> {
    // Light-weight Node class
    private static class Node<T> {
        private final T data;
        private Node<T> next;
        private Node<T> prev;

        private Node() { this(null); }
        private Node(T data) { this(data, null, null); }

        private Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Comparator<T> comparator;
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList(Comparator<T> comparator, T data) {
        this.comparator = comparator;
        head = new Node<>(data);
        tail = head;
        this.size = 1;
    }

    public DoublyLinkedList(Comparator<T> comparator) {
        this.comparator = comparator;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Copy Constructor
    public DoublyLinkedList(Comparator<T> comparator, DoublyLinkedList<T> other) {
        this.comparator = comparator;
        if (other.head == null) {
            this.head = null;
            this.tail = null;
        } else {
            Node<T> dummy = new Node<>();
            Node<T> dummyHead = dummy;
            Node<T> current = other.head;

            while (current != null) {
                dummyHead.next = new Node<>(current.data);
                dummyHead.next.prev = dummyHead;
                dummyHead = dummyHead.next;
                current = current.next;
            }
            this.head = dummy.next;
            this.head.prev = null;

            this.tail = dummyHead;
        }
        this.size = other.size;
    }

    // Iterable : Forward
    @Override
    public @NotNull Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    public @NotNull Iterator<T> reverseIterator() {
        return new DoublyLinkedListReverseIterator();
    }

    private class DoublyLinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("Element not found!");
            T value = current.data;
            current = current.next;
            return value;
        }
    }

    // Iterable : Reverse
    private class DoublyLinkedListReverseIterator implements Iterator<T> {
        private Node<T> current = tail;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("Element not found!");
            T value = current.data;
            current = current.prev;
            return value;
        }
    }

    public T first() {
        if (head != null)
            return head.data;
        throw new NullPointerException("List is empty.");
    }

    public T last() {
        if (head != null)
            return tail.data;
        throw new NullPointerException("List is empty.");
    }

    public void insertRight(T key) {
        if (head == null) {
            head = new Node<>(key);
            tail = head;
        } else {
            Node<T> newNode = new Node<>(key, null, tail);
            tail.next = newNode;
            tail = newNode;
        }
        this.size++;
    }

    public void insertLeft(T key) {
        if (head == null) {
            head = new Node<>(key);
            tail = head;
        } else {
            Node<T> newNode = new Node<>(key, head, null);
            head.prev = newNode;
            head = newNode;
        }
        this.size++;
    }

    public boolean search(T key) {
        if (head == null)
            return false;
        Node<T> current = head;
        while (current != null) {
            if (comparator.compare(key, current.data) == 0)
                return true;
            current = current.next;
        }
        return false;
    }

    public boolean delete(T key) {
        // 5<---->6<---->8<---->10
        if (head == null)
            return false;

        // If the key to be deleted is the head
        if (comparator.compare(key, head.data) == 0)
            return this.delete();

        // If the key to be deleted is the tail
        else if (comparator.compare(key, tail.data) == 0) {
            Node<T> previousTail = tail.prev;
            previousTail.next = null;
            tail = previousTail;
        }

        // If the key to be deleted is not the head
        Node<T> current = head.next;
        while (current != null) {
            if (comparator.compare(key, current.data) == 0) {
                Node<T> previousNode = current.prev;
                Node<T> nextNode = current.next;

                if (previousNode != null)
                    previousNode.next = nextNode;

                if (nextNode != null)
                    nextNode.prev = previousNode;

                if (current == tail)
                    tail = previousNode;

                this.size--;
                return true;
            }
            current = current.next;
        }
        this.size--;
        return false;
    }

    public boolean delete() {
        if (head == null)
            return false;
        head = head.next;
        if (head != null)
            head.prev = null;
        else
            tail = null;
        this.size--;
        return true;
    }

    public void replace(T oldKey, T newKey) {
        if (head == null)
            return;

        // Check if current is the head
        if (comparator.compare(oldKey, head.data) == 0) {
            this.replace(newKey);
            return;
        }

        Node<T> current = head;
        while (current != null) {
            if (comparator.compare(oldKey, current.data) == 0) {
                Node<T> newNode = new Node<>(newKey);

                newNode.prev = current.prev;
                newNode.next = current.next;

                if (current.prev != null)
                    current.prev.next = newNode;
                else
                    head = newNode;

                if (current.next != null)
                    current.next.prev = newNode;
                else
                    tail = newNode;
            }
            current = current.next;
        }
    }
    /* Replace the head of the DoublyLinkedList */
    public void replace(T newKey) {
        if (head == null)
            return;

        Node<T> newHead = new Node<>(newKey);
        // Check if List is a singleton
        if (head.next == null) {
            head = newHead;
            tail = newHead;
        } else {
            newHead.next = head.next;
            head.next.prev = newHead;
            head = newHead;
        }
    }

    // Destructive reverse
    public void reverse() { reverse(this.head); }

    // Non-destructive reverse
    public DoublyLinkedList<T> reversed() {
        if (head == null || head.next == null)
            return new DoublyLinkedList<>(comparator, this);

        DoublyLinkedList<T> other = new DoublyLinkedList<>(comparator, this);
        other.reverse(other.head);
        return other;
    }

    private void reverse(Node<T> head) {
        if (head == null || head.next == null)
            return;

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            Node<T> next = current.next;
            current.next = previous;
            current.prev = next;
            previous = current;
            current = next;
        }
        tail = this.head;
        this.head = previous;
        tail.next = null;
    }

    public void extendLeft(DoublyLinkedList<T> other) {
        if (other == null)
            return;
        other.tail.next = this.head;
        this.head.prev = other.tail;
        this.head = other.head;
        this.size += other.size;
    }

    public void extendRight(DoublyLinkedList<T> other) {
        if (other == null)
            return;
        this.tail.next = other.head;
        other.head.prev = this.tail;
        this.tail = other.tail;
        this.size += other.size;
    }

    public int size() { return this.size; }

    @Override
    public String toString() {
        // 1 <---> 2 <---> 3 <---> 4 ---> null
        if (size == 0)
            return "null";
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;

        while (current != null) {
            sb.append(current.data);
            if (current.next != null)
                sb.append(" <---> ");
            else
                sb.append(" ---> null");
            current = current.next;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
       if (this == object)
           return true;
       if (object == null || this.getClass() != object.getClass())
           return false;

       @SuppressWarnings("unchecked")
       DoublyLinkedList<T> that = (DoublyLinkedList<T>) object;

       if (this.size != that.size)
           return false;

       if (!Objects.equals(this.comparator, that.comparator))
           return false;

       Node<T> thisCurrent = this.head;
       Node<T> thatCurrent = that.head;

       while (thisCurrent != null && thatCurrent != null) {
           if (comparator.compare(thisCurrent.data, thatCurrent.data) != 0)
               return false;
           thisCurrent = thisCurrent.next;
           thatCurrent = thatCurrent.next;
       }
       return thisCurrent == null && thatCurrent == null;
    }

    @Override
    public int hashCode() {
        int result = this.comparator.hashCode();
        Node<T> current = this.head;
        while (current != null) {
            result = 31 * result + current.data.hashCode();
            current = current.next;
        }
        result = 31 * this.size * result;
        return result;
    }
}
