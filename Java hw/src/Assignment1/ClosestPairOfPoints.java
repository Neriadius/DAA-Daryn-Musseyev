package Assignment1;

import java.util.*;

public class ClosestPairOfPoints {

    // Point in 2D
    static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Public entry point
    public static double closestPair(Point[] points) {
        // Sort points by X coordinate
        Point[] ptsSortedByX = points.clone();
        Arrays.sort(ptsSortedByX, Comparator.comparingDouble(p -> p.x));

        // Sort points by Y coordinate
        Point[] ptsSortedByY = points.clone();
        Arrays.sort(ptsSortedByY, Comparator.comparingDouble(p -> p.y));

        return closestPairRec(ptsSortedByX, ptsSortedByY);
    }

    // Recursive divide-and-conquer
    private static double closestPairRec(Point[] ptsX, Point[] ptsY) {
        int n = ptsX.length;

        // Base case: brute force for <= 3 points
        if (n <= 3) {
            return bruteForce(ptsX);
        }

        int mid = n / 2;
        Point midPoint = ptsX[mid];

        // Split points by X
        Point[] leftX = Arrays.copyOfRange(ptsX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(ptsX, mid, n);

        // Split points by Y according to midPoint
        List<Point> leftY = new ArrayList<>();
        List<Point> rightY = new ArrayList<>();
        for (Point p : ptsY) {
            if (p.x <= midPoint.x) {
                leftY.add(p);
            } else {
                rightY.add(p);
            }
        }

        // Recurse on left and right halves
        double dLeft = closestPairRec(leftX, leftY.toArray(new Point[0]));
        double dRight = closestPairRec(rightX, rightY.toArray(new Point[0]));

        // Minimal distance so far
        double d = Math.min(dLeft, dRight);

        // Build strip of points within distance d from midline
        List<Point> strip = new ArrayList<>();
        for (Point p : ptsY) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        // Check strip neighbors (7–8 rule)
        double dStrip = stripClosest(strip, d);

        return Math.min(d, dStrip);
    }

    // Brute force O(n^2) for small arrays
    private static double bruteForce(Point[] points) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                minDist = Math.min(minDist, distance(points[i], points[j]));
            }
        }
        return minDist;
    }

    // Check strip (only next 7 neighbors)
    private static double stripClosest(List<Point> strip, double d) {
        double minDist = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minDist; j++) {
                minDist = Math.min(minDist, distance(strip.get(i), strip.get(j)));
            }
        }
        return minDist;
    }

    // Euclidean distance
    private static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Test
    public static void main(String[] args) {
        Point[] points = {
                new Point(2.5, 3.1),
                new Point(12.0, 30.0),
                new Point(40.0, 50.0),
                new Point(5.0, 1.0),
                new Point(12.0, 10.0),
                new Point(3.0, 4.0)
        };

        double result = closestPair(points);
        System.out.println("Closest distance = " + result);
    }

}
