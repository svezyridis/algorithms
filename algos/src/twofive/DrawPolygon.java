package twofive;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

public class DrawPolygon {

    public static class Point2D {
        double x;
        double y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static double length(Point2D point2D) {
            return Math.sqrt(Math.pow(point2D.x, 2) + Math.pow(point2D.y, 2));
        }

        public static double innerProduct(Point2D a, Point2D b) {
            return a.x * b.x + a.y * b.y;
        }

        public static double angle(Point2D a, Point2D b) {
            return Math.atan((b.y - a.y) / (b.x - a.x));
        }

        public static class CompareByX implements Comparator<Point2D> {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                if (o1.x > o2.x) return 1;
                else if (o1.x < o2.x) return -1;
                else return 0;
            }
        }

        public static class CompareByY implements Comparator<Point2D> {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                if (o1.y > o2.y) return 1;
                else if (o1.y < o2.y) return -1;
                else {
                    if (o1.x > o2.x) return 1;
                    else if (o1.x < o2.x) return -1;
                }
                return 0;
            }
        }

        public static class CompareByAngleToPoint implements Comparator<Point2D> {
            Point2D point2DToCompare;

            @Override
            public int compare(Point2D o1, Point2D o2) {
                if (angle(point2DToCompare, o1) > angle(point2DToCompare, o2)) return 1;
                else if (angle(point2DToCompare, o1) < angle(point2DToCompare, o2)) return -1;
                return 0;
            }

            public CompareByAngleToPoint(Point2D point2D) {
                point2DToCompare = point2D;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("enter number of points");
        int no = StdIn.readInt();
        Point2D[] points = new Point2D[no];
        for (int i = 0; i < no; i++) {
            points[i] = new Point2D(StdRandom.uniform(-90, 90), StdRandom.uniform(-90, 90));
        }
        Arrays.sort(points, new Point2D.CompareByY());
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setYscale(-100, 100);
        StdDraw.setXscale(-100, 100);
        Point2D firstPoint = points[0];

        Arrays.sort(points, new Point2D.CompareByAngleToPoint(firstPoint));


        for (int i = 1; i < points.length; i++) {
            StdDraw.point(points[i - 1].x, points[i - 1].y);
            StdDraw.point(points[i].x, points[i].y);
            StdDraw.line(points[i - 1].x, points[i - 1].y, points[i].x, points[i].y);
        }
        StdDraw.line(points[points.length - 1].x, points[points.length - 1].y, points[0].x, points[0].y);
    }
}
