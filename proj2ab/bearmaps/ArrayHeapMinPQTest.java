package bearmaps;

import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;
import java.util.HashSet;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

/*
 * @source: PrintHeapDemo.java
 */


public class ArrayHeapMinPQTest {
    @Test
    public void testSize() {
        ArrayHeapMinPQ<Integer> heap1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> heap2 = new NaiveMinPQ<>();
        for (int i = 0; i < 9; i++) {
            int p = StdRandom.uniform(0, 1000000);
            heap1.add(p, p);
            heap2.add(p, p);
        }
        assertEquals(heap1.size(), heap2.size());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.size(), heap2.size());
        for (int i = 0; i < 6; i++) {
            int p = StdRandom.uniform(0, 1000);
            heap1.add(p, p);
            heap2.add(p, p);
        }
        assertEquals(heap1.size(), heap2.size());

    }

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> heap1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> heap2 = new NaiveMinPQ<>();
        for (int i = 0; i < 10000; i++) {
            heap1.add(i, i);
            heap2.add(i, i);
            assertEquals(heap1.getSmallest(), heap2.getSmallest());
        }
        for (int i = -1; i > -10000; i--) {
            heap1.add(i, i);
            heap2.add(i, i);
            assertEquals(heap1.getSmallest(), heap2.getSmallest());
        }
    }

    @Test
    public void testRemove() {
        ArrayHeapMinPQ<Double> heap1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Double> heap2 = new NaiveMinPQ<>();
        for (int i = 0; i < 10000; i++) {
            double priority = StdRandom.uniform(0.0, 1000.0);
            heap1.add(priority, priority);
            heap2.add(priority, priority);
            assertEquals(heap1.size(), heap2.size());
        }
        for (int i = 0; i < 5000; i++) {
            assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
            assertEquals(heap1.size(), heap2.size());
        }

        for (int i = 0; i < 5000; i++) {
            double priority = StdRandom.uniform(0.0, 1000.0);
            heap1.add(priority, priority);
            heap2.add(priority, priority);
            assertEquals(heap1.size(), heap2.size());
        }

        for (int i = 0; i < 5000; i++) {
            assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
            assertEquals(heap1.size(), heap2.size());
        }


    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Double> heap1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Double> heap2 = new NaiveMinPQ<>();
        HashSet<Double> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            double priority = StdRandom.uniform(0.0, 1000.0);
            heap1.add(priority, priority);
            heap2.add(priority, priority);
            set.add(priority);
        }

        for (Double p: set) {
            assertEquals(heap1.contains(p), heap2.contains(p));
        }

        for (int i = 0; i < 10; i++) {
            double priority = StdRandom.uniform(0.0, 1000.0);
            assertEquals(heap1.contains(priority), heap2.contains(priority));
        }

        for (int i = 0; i < 10; i++) {
            assertFalse(heap1.contains(heap1.removeSmallest()));
        }

    }

    @Test
    public void testRemoveE() {
        try {
            ArrayHeapMinPQ<Double> heap = new ArrayHeapMinPQ<>();
            heap.removeSmallest();
        } catch (NoSuchElementException e) {
            return;
        }
        fail();
    }

    @Test
    public void testChangeP() {
        ArrayHeapMinPQ<Integer> heap1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> heap2 = new NaiveMinPQ<>();
        for (int i = 0; i < 9; i++) {
            int p = StdRandom.uniform(0, 1000000);
            heap1.add(p, p);
            heap2.add(p, p);
        }
        assertEquals(heap1.size(), heap2.size());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        assertEquals(heap1.size(), heap2.size());
        for (int i = 0; i < 15; i++) {
            heap1.add(i, i);
            heap2.add(i, i);
        }
        heap1.changePriority(2, 120000000);
        heap2.changePriority(2, 120000000);
        for (int i = 0; i < 20; i++) {
            assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
        }
    }

    @Test
    public void testChangePRandom() {
        ArrayHeapMinPQ<Integer> heap1 = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> heap2 = new NaiveMinPQ<>();
        for (int i = 1000; i >= 0; i--) {
            heap1.add(i, i);
            heap2.add(i, i);
        }

        for (int i = 0; i < 100; i++) {
            int index = StdRandom.uniform(0, 1000);
            int p = StdRandom.uniform(-1000000, 10000000);
            heap1.changePriority(index, p);
            heap2.changePriority(index, p);
            assertEquals(heap1.size(), heap2.size());
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals(heap1.removeSmallest(), heap2.removeSmallest());
            assertEquals(heap1.size(), heap2.size());
        }
    }



}
