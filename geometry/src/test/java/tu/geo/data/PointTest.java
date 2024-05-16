package tu.geo.data;

import geo.data.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @ParameterizedTest
    @CsvSource({
            "2.0, 5.5 ,5.0 ,1.5 ,5.0, 0.0",
            "2.0E300, 5.5E300 ,5.0E300 ,1.5E300 ,5.0E300, 0.0",
            "2.0E-300, 5.5E-300 ,5.0E-300 ,1.5E-300 ,5.0E-300, 1E-310",
    })
    void testDistance(double x1, double y1, double x2, double y2, double expectedDistance, double margin) {
        var p1 = Point.of("P1", x1, y1);
        var p2 = Point.of("P2", x2, y2);
        double d = p1.distance(p2);
        assertEquals(expectedDistance, d, margin);
    }
}