package bearmaps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NaivePointSet implements PointSet {
    List<Point> pointList;

    public NaivePointSet(List<Point> points) {
        pointList = new ArrayList<>();
        for (Point p: points) {
            pointList.add(p);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point p = pointList.get(0);
        Point ori = new Point(x, y);
        double dist = Point.distance(p, ori);
        for (int i = 1; i < pointList.size(); i++) {
            double newDist = Point.distance(ori, pointList.get(i));
            if (newDist < dist) {
                dist = newDist;
                p = pointList.get(i);
            }
        }
        return p;
    }

    public static void main(String[] ars) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(Arrays.asList(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4

    }
}
