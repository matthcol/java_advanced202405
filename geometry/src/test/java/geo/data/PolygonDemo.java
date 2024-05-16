package geo.data;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

class PolygonDemo {

    @Test
    void demoPolygon(){
        var ptA = Point.of("A", 1.5, 3.5);
        var ptB = Point.of("B", 2.5, 5.5);
        var ptC = Point.of("C", 3.5, 7.5);
        var points = List.of(
                ptA,
                ptB,
                ptC,
                Point.of("d", 0.0, 0.0),
                Point.of("E", 0.0, 0.0)
        );
        var poly1 = Polygon.of("P1", points);
        System.out.println(poly1);
        var weightedPoints = Set.of(
                WeightedPoint.of("W", 10.5, 3.5, 1.0),
                WeightedPoint.of("X", 3.5, 5.5 ,2.0),
                WeightedPoint.of("Y", 30.5, 7.5, -1.0)
        );
        var poly2 = Polygon.of("P2", weightedPoints);
        System.out.println(poly2);
        var poly3 = Polygon.of("P3", ptA, ptB, ptC);
        System.out.println(poly3);
    }
}
