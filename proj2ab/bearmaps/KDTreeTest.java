package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class KDTreeTest {
    @Test
    public void testKD() {
        ArrayList<Point> array = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double x = StdRandom.uniform(-10000.0, 100000.0);
            double y = StdRandom.uniform(-10000.0, 100000.0);
            array.add(new Point(x, y));
        }
        NaivePointSet nps = new NaivePointSet(array);
        KDTree kdt = new KDTree(array);

        for (int i = 0; i < 10000; i++) {
            double a = StdRandom.uniform(-10000.0, 100000.0);
            double b = StdRandom.uniform(-10000.0, 100000.0);
            assertEquals(nps.nearest(a, b), kdt.nearest(a, b));
        }


    }

    @Test
    public void testSimple() {
        ArrayList<Point> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(new Point(i, 0));
        }
        for (int j = 0; j < 10; j++) {
            array.add(new Point(0, j));
        }

        KDTree kdt = new KDTree(array);
        NaivePointSet nps = new NaivePointSet(array);
        for (int k = 0; k < 20; k++) {
            assertEquals(nps.nearest(k, 0), kdt.nearest(k, 0));
        }

    }

    public void timeRandom(boolean kdtMode, int count, int query) {
        ArrayList<Point> array = new ArrayList<>();
        ArrayList<Point> qArray = new ArrayList<>();
        PointSet ps;

        for (int i = 0; i < count; i++) {
            double x = StdRandom.uniform(-10000.0, 100000.0);
            double y = StdRandom.uniform(-10000.0, 100000.0);
            array.add(new Point(x, y));
        }

        if (kdtMode) {
            ps = new KDTree(array);
        } else {
            ps = new NaivePointSet(array);
        }

        for (int i = 0; i < query; i++) {
            double a = StdRandom.uniform(-10000.0, 100000.0);
            double b = StdRandom.uniform(-10000.0, 100000.0);
            qArray.add(new Point(a, b));
        }

        Stopwatch sw = new Stopwatch();
        for (Point p : qArray) {
            Point result = ps.nearest(p.getX(), p.getY());
        }

        System.out.println("KD Tree Mode: " + kdtMode
            + "\nTime: " + sw.elapsedTime() + "\n"
            + "queries count: " + query
            + "\nPoints count: " + count);

    }

    @Test
    public void timeTest() {
        timeRandom(true, 100000, 10000);
        timeRandom(false, 100000, 10000);
    }


}
