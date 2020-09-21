package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        last = (last + 1) % rb.length;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T element = rb[first];
        fillCount -= 1;
        first = (first + rb.length + 1) % rb.length;
        return element;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }


    @Override
    public Iterator<T> iterator() {
        return new ArbIterator(first, this.rb, fillCount);
    }

    private class ArbIterator implements Iterator<T> {
        int loc;
        int length;
        T[] arr;
        int count;

        ArbIterator(int front, T[] array, int c) {
            loc = front;
            length = 0;
            arr = array;
            count = c;

        }

        @Override
        public boolean hasNext() {
            return length != count;
        }

        @Override
        public T next() {
            T element =  arr[loc];
            loc = (loc + 1) % arr.length;
            length += 1;
            return element;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArrayRingBuffer)
                || this.fillCount() != ((ArrayRingBuffer) o).fillCount()) {
            return false;
        } else {
            Iterator fIter = this.iterator();
            Iterator sIter = ((ArrayRingBuffer) o).iterator();
            while (fIter.hasNext()) {
                if (!fIter.next().equals(sIter.next())) {
                    return false;
                }
            }
        }
        return true;
    }
}

