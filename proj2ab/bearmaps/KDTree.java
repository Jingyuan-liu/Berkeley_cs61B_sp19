package bearmaps;
import java.util.Arrays;
import java.util.List;

public class KDTree implements PointSet {
    Node root;


    private class Node {
        boolean lr;
        Point point;
        Node leftChild;
        Node rightChild;

        Node(boolean lR, Point p) {
            lr = lR;
            point = p;
            leftChild = null;
            rightChild = null;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p: points) {
            this.insert(p);
        }
    }

    private void insert(Point p) {
        if (this.root == null) {
            this.root = new Node(true, p);
        } else {
            insertRec(p, this.root);
        }
    }

    private void insertRec(Point p, Node n) {
        if (p.equals(n.point)) {
            return;
        }
        if (n.lr) {
            double upperX = n.point.getX();
            double lowerX = p.getX();
            if (upperX > lowerX) {
                if (n.leftChild == null) {
                    n.leftChild = new Node(false, p);
                } else {
                    insertRec(p, n.leftChild);
                }
            } else {
                if (n.rightChild == null) {
                    n.rightChild = new Node(false, p);
                } else {
                    insertRec(p, n.rightChild);
                }
            }
        } else if (!n.lr) {
            double upperY = n.point.getY();
            double lowerY = p.getY();
            if (upperY > lowerY) {
                if (n.leftChild == null) {
                    n.leftChild = new Node(true, p);
                } else {
                    insertRec(p, n.leftChild);
                }
            } else {
                if (n.rightChild == null) {
                    n.rightChild = new Node(true, p);
                } else {
                    insertRec(p, n.rightChild);
                }
            }
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearestRec(this.root, goal, this.root).point;
    }

    private Node nearestRec(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }

        if (n.lr) {
            if (n.point.getX() >= goal.getX()) {
                best = nearestRec(n.leftChild, goal, best);
                if (Point.distance(best.point, goal) > Math.pow(n.point.getX() - goal.getX(), 2)) {
                    best = nearestRec(n.rightChild, goal, best);
                }
            } else {
                best = nearestRec(n.rightChild, goal, best);
                if (Point.distance(best.point, goal) > Math.pow(n.point.getX() - goal.getX(), 2)) {
                    best = nearestRec(n.leftChild, goal, best);
                }
            }
        } else {
            if (n.point.getY() >= goal.getY()) {
                best = nearestRec(n.leftChild, goal, best);
                if (Point.distance(best.point, goal) > Math.pow(n.point.getY() - goal.getY(), 2)) {
                    best = nearestRec(n.rightChild, goal, best);
                }
            } else {
                best = nearestRec(n.rightChild, goal, best);
                if (Point.distance(best.point, goal) > Math.pow(n.point.getY() - goal.getY(), 2)) {
                    best = nearestRec(n.leftChild, goal, best);
                }
            }
        }

        return best;
    }

    public static void main(String[] as) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree kdt = new KDTree(Arrays.asList(p1, p2, p3));
        Point ret = kdt.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4

    }




}
