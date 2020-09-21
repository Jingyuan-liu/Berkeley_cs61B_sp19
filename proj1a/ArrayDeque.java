/**
 * This is the class of ArrayDeque where we use
 *  array to implement a double-ended queue.
 * @author Jingyuan Liu
 */

public class ArrayDeque<T> {
    private T[] items;
    private int front;
    private int size;
    private int cap;

    public ArrayDeque() {
        items = (T[]) new Object[16];
        front = 0;
        size = 0;
        cap = 16;
    }

    public ArrayDeque(ArrayDeque other) {
        T[] newArray = (T[]) new Object[other.cap];
        for (int i = 0; i < other.items.length; i++) {
            newArray[i] = (T) other.items[i];
        }
        this.items = newArray;
        this.front = other.front;
        this.size = other.size;
        this.cap = other.cap;
    }


    private int prevItem(int loc) {
        return (loc + this.cap - 1) % cap;
    }

    private int nextItem(int first) {
        return (first + size) % cap;
    }


    private void resize() {
        T[] newArray = (T[]) new Object[this.cap * 2];
        System.arraycopy(this.items, front, newArray, 0, cap - front);
        System.arraycopy(this.items, 0, newArray, cap - front, size + front - cap);
        front = 0;
        cap = cap * 2;
        this.items = newArray;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[this.cap / 2];
        if (front + size > cap) {
            System.arraycopy(this.items, front, newArray, 0, cap - front);
            System.arraycopy(this.items, 0, newArray, cap - front, size + front - cap);
        } else {
            System.arraycopy(this.items, front, newArray, 0, size);
        }

        cap = newArray.length;
        front = 0;
        this.items = newArray;
    }

    /**
     * This method insert an element at the beginning of the Deque.
     * @param item The item we want to add into deque.
     */
    public void addFirst(T item) {
        if (size == cap) {
            this.resize();
        }
        int place = this.prevItem(front);
        this.items[place] = item;
        size++;
        this.front = place;
    }

    /**
     * This method insert an element at the end of the Deque.
     * @param item The item we want to insert.
     */
    public void addLast(T item) {
        if (size == cap) {
            this.resize();
        }
        int place = this.nextItem(this.front);
        this.items[place] = item;
        size++;
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
        for (int i = 0; i < size; i++) {
            System.out.print(this.items[(front + i) % cap]);
            System.out.print(" ");
        }
        System.out.println();
    }

    /**
     * Remove the first element of a deque.
     * @return The element that has been removed; null if such element does not exists.
     */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        int place = front;
        T remove = this.items[place];
        front = (front + 1) % cap;
        size--;
        if (size > 0 && cap > 16 && cap / size >= 4) {
            this.shrink();
        }
        return remove;
    }

    /**
     * Remove the last element of a deque.
     * @return The element that has been removed; null if no such element.
     */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        int place = (front + size - 1) % cap;
        T remove = this.items[place];
        size--;
        if (size > 0 && cap > 16 && cap / size >= 4) {
            this.shrink();
        }
        return remove;
    }

    /**
     *
     * @param index The index of element we want to access to.
     * @return The element at given index.
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        int place = ((front + index) % cap);
        return this.items[place];
    }
}
