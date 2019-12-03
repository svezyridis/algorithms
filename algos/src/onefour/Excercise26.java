package onefour;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise26
 */
public class Excercise26 {

    public static int countTriples(Point2D[] points) {
        int res = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                // define line
                double a = (points[j].y() - points[i].y()) / (points[j].x() - points[i].x());
                double b = points[i].y()
                        - (points[j].y() - points[i].y()) / (points[j].x() - points[i].x()) * points[i].x();
                for (int k = j + 1; k < points.length; k++) {
                    if (points[k].y() == a * points[k].x() + b)
                        res++;

                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        Point2D[] points = new Point2D[6];
        for (int i = 0; i < points.length; i++) {
            Point2D point = new Point2D(i, i + 1.5);
            points[i] = point;
        }

        int numberOfTriples1 = countTriples(points);
        StdOut.println("Number of triples: " + numberOfTriples1 + " Expected: 20");

        // Based on
        // https://www.algebra.com/algebra/homework/Length-and-distance/Length-and-distance.faq.question.530663.html
        // (-3,4) (3,2) (6,1) are on the same line
        Point2D pointA = new Point2D(-3, 4);
        Point2D pointB = new Point2D(3, 2);
        Point2D pointC = new Point2D(6, 1);

        Point2D[] points2 = { pointA, pointB, pointC };

        int numberOfTriples2 = countTriples(points2);
        StdOut.println("Number of triples: " + numberOfTriples2 + " Expected: 1");

        Point2D pointD = new Point2D(6, 1);

        Point2D[] points3 = { pointA, pointB, pointC, pointD };

        int numberOfTriples3 = countTriples(points3);
        StdOut.println("Number of triples: " + numberOfTriples3 + " Expected: 4");

        // Case with cubic y coordinate
        Point2D pointE = new Point2D(1, 1);
        Point2D pointF = new Point2D(2, 8);
        Point2D pointG = new Point2D(-3, -27);

        Point2D[] points4 = { pointE, pointF, pointG };
        int numberOfTriples4 = countTriples(points4);
        StdOut.println("Number of triples: " + numberOfTriples4 + " Expected: 1");
    }
}