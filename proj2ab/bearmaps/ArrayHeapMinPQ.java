package bearmaps;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
 * @source: PrintHeapDemo.java
 */

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private class Node<T> {
        T elem;
        double priority;
        int ind;

        private Node(T e, double p, int index) {
            elem = e;
            priority = p;
            ind = index;
        }

        private void changeP(double p) {
            this.priority = p;
        }

        @Override
        public String toString() {
            return "Value: " + elem;
        }
    }

    //Uses the book implement, offset.
    private ArrayList<Node<T>> array;
    private HashMap<T, Node<T>> map;

    public ArrayHeapMinPQ() {
        array = new ArrayList<>();
        array.add(null); //offsets
        map = new HashMap<>();
    }

    private void up(int index) {
        if (index == 1) {
            return;
        } else if (array.get(index).priority >= array.get(index / 2).priority) {
            return;
        } else {
            swap(index, index / 2);
            array.get(index).ind = index;
            array.get(index / 2).ind = index / 2;

            this.up(index / 2);
        }
    }

    private void down(int index) {
        //if no child
        if (index * 2 > this.size()) {
            return;
        } else if (index * 2 == this.size()) { //only left child
            if (array.get(index).priority > array.get(index * 2).priority) {
                this.swap(index, index * 2);
                this.array.get(index).ind = index;
                this.array.get(index * 2).ind = index * 2;
                this.down(index * 2);
            }
        } else { //two child
            int left = index * 2;
            int right = index * 2 + 1;
            //right child smaller or equal
            if (array.get(left).priority >= array.get(right).priority) {
                if (array.get(index).priority > array.get(right).priority) {
                    swap(index, right);
                    this.array.get(index).ind = index;
                    this.array.get(right).ind = right;
                    this.down(right);
                }

            } else { //left child smaller
                if (array.get(index).priority > array.get(left).priority) {
                    swap(index, left);
                    this.array.get(index).ind = index;
                    this.array.get(left).ind = left;
                    this.down(left);
                }
            }

        }

    }

    private void swap(int i1, int i2) {
        Node<T> node1 = array.get(i1);
        Node<T> node2 = array.get(i2);
        array.set(i1, node2);
        array.set(i2, node1);
    }

    @Override
    public void add(T item, double priority) {
        if (this.contains(item)) {
            throw new IllegalArgumentException("Such element already exists");
        }
        Node<T> node = new Node<>(item, priority, this.size() + 1);
        map.put(item, node);
        array.add(node);
        this.up(array.size() - 1);
    }

    @Override
    public boolean contains(T item) {
        return this.map.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (this.size() == 0) {
            throw new NoSuchElementException("No element in heap");
        }
        return this.array.get(1).elem;
    }

    @Override
    public T removeSmallest() {
        if (this.size() == 0) {
            throw new NoSuchElementException("No element in heap");
        }

        this.swap(1, this.size());
        Node<T> smallest = this.array.remove(this.size());
        this.map.remove(smallest.elem);
        this.down(1);
        return smallest.elem;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!this.contains(item)) {
            throw new NoSuchElementException("Element does not exist");
        }
        Node<T> node = this.map.get(item);
        node.changeP(priority);
        int index = node.ind;
        this.up(index);
        this.down(index);
    }

    private void print() {
        PrintHeapDemo.printFancyHeapDrawing(this.array.toArray());
    }
}
