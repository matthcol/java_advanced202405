package geo.data;

import org.junit.jupiter.api.Test;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class PointDemo {

    @Test
    void demoPoint(){
        var pA = Point.of("A", 1.0, 2.5);
        System.out.println(pA);
        pA.translate(3.5, -0.5);
        System.out.println(pA);
    }

    @Test
    void demoWeightedPoint(){
        var wp = WeightedPoint.of("W", 1.2, 5.75, 10.0);
        var pA = Point.of("A", 1.0, 2.5);
        System.out.println(wp);
        double d1 = pA.distance(wp); // Point arg accepts WeightedPoint: LSP principle
        double d2 = wp.distance(pA);
        System.out.println(MessageFormat.format("distance: {0} / {1}", d1, d2));
    }

    @Test
    void demoPointCollection(){
        List<Point> points = new ArrayList<>();
        Collections.addAll(points,
                Point.of("A", 1.5, 3.5),
                Point.of("B", 2.5, 5.5),
                Point.of("C", 3.5, 7.5),
                Point.of("d", 0.0, 0.0),
                Point.of("E", 0.0, 0.0)
        );
        Set<WeightedPoint> weightedPoints = Set.of(
                WeightedPoint.of("W", 10.5, 3.5, 1.0),
                WeightedPoint.of("X", 3.5, 5.5 ,2.0),
                WeightedPoint.of("Y", 30.5, 7.5, -1.0)
        );
        points.addAll(weightedPoints);
        System.out.println(points);
        // weightedPoints.addAll(points); // compilation error
        for (Point pt: points){
            System.out.println(MessageFormat.format("point name={0}, x={1}, y={2}",
                    pt.getName(),
                    pt.getX(),
                    pt.getY()
            ));
            // Java 17 instanceof (pattern matching)
            if (pt instanceof WeightedPoint wp){
                System.out.println("\t- weight=" + wp.getWeight());
            }
            // Java <17
            if (pt instanceof WeightedPoint){
                WeightedPoint wp = (WeightedPoint) pt;
                System.out.println("\t- weight=" + wp.getWeight());
            }
        }

        // var maxPoint = Collections.max(points); // compilation error : Point is not Comparable
        var maxPoint = Collections.max(points, Comparator.comparing(Point::getX));
        System.out.println("Max point (max x): " + maxPoint);
        maxPoint = Collections.max(points, Comparator.comparing(Shape::getName));
        System.out.println("Max point (max name cs): " + maxPoint);
        maxPoint = Collections.max(points, Comparator.comparing(Shape::getName, String::compareToIgnoreCase));
        System.out.println("Max point (max name ci): " + maxPoint);
        maxPoint = Collections.max(points, Comparator.comparing(Object::hashCode));
        System.out.println("Max point (max hash): " + maxPoint);
        maxPoint = Collections.max(points, (Point p1, Point p2) -> (int) (p1.getY() - p2.getY()));
        System.out.println("Max point (max y): " + maxPoint);

        points.sort(Comparator.comparing(Point::getX)
                .thenComparing(Point::getY, Comparator.reverseOrder())
                .thenComparing(Shape::getName, String::compareToIgnoreCase)
        );
        points.forEach(System.out::println);
    }
}
