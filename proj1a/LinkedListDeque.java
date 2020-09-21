/**
 * This is the class of LinkedListDeque where we use
 * doubly linked list to implement a double-ended queue.
 * @author Jingyuan Liu
 */

public class LinkedListDeque<T> {
    /**
     * This is a nested class of Node to implement the doubly linked list.
     */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node() {
            item = null;
            prev = null;
            next = null;
        }

        public Node(T t) {
            item = t;
            prev = null;
            next = null;
        }
    }

    private Node sen;
    private int size;

    public LinkedListDeque() {
        sen = new Node();
        sen.next = sen;
        sen.prev = sen;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        this();
        if (other.size() > 0) {
            for (int i = 0; i < other.size(); i++) {
                this.addLast((T) other.get(i));
            }
        }
    }

    /**
     * This method insert an element at the beginning of the Deque.
     * @param item The item we want to add into deque.
     */
    public void addFirst(T item) {
        Node second = this.sen.next;
        Node first = new Node(item);
        first.next = second;
        first.prev = sen;
        sen.next = first;
        second.prev = first;
        this.size++;
    }

    /**
     * This method insert an element at the end of the Deque.
     * @param item The item we want to insert.
     */
    public void addLast(T item) {
        Node last = this.sen.prev;
        Node newLast = new Node(item);
        newLast.next = sen;
        newLast.prev = last;
        last.next = newLast;
        sen.prev = newLast;
        this.size++;
    }

    /**
     * Check if a deque is empty.
     * @return boolean that identify whether a deque is empty.
     */
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    /**
     * Return the size of a deque.
     * @return Size of the deque.
     */
    public int size() {
        return this.size;
    }

    /**
     * Print the deque in order.
     */
    public void printDeque() {
        Node node = sen.next;
        while (node != sen) {
            System.out.print(node.item);
            System.out.print(' ');
            node = node.next;
        }
        System.out.println();
    }

    /**
     * Remove the first element of a deque.
     * @return The element that has been removed; null if such element does not exists.
     */
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        } else {
            Node first = this.sen.next;
            Node second = this.sen.next.next;
            this.sen.next = second;
            second.prev = this.sen;
            this.size--;
            return first.item;
        }
    }

    /**
     * Remove the last element of a deque.
     * @return The element that has been removed; null if no such element.
     */
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        } else {
            Node last = this.sen.prev;
            Node newLast = this.sen.prev.prev;
            newLast.next = this.sen;
            this.sen.prev = newLast;
            this.size--;
            return last.item;
        }
    }

    /**
     * This returns the element at a given index.
     * @param index The index of element we want to access to.
     * @return The element at given index.
     */
    public T get(int index) {
        Node node = this.sen.next;

        if (index + 1 > this.size() || index < 0) {
            return null;
        } else {
            while (index != 0) {
                node = node.next;
                index--;
            }
            return node.item;
        }
    }

    /**
     * Works exactly the same as get but in recursive way.
     * @param index The index of element we want to access to.
     * @return The element at given index.
     */
    public T getRecursive(int index) {
        if (index + 1 > this.size() || index < 0) {
            return null;
        }
        if (index == 0) {
            return (T) this.sen.next.item;
        }
        LinkedListDeque<T> para = new LinkedListDeque<>(this);
        para.removeFirst();
        return para.getRecursive(index - 1);
    }
}
