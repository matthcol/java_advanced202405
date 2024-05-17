package tu.geo.data;

import geo.data.Point;
import geo.data.Polygon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTest {

    static Polygon pentagon;
    static Polygon triangle345;

    @BeforeAll
    static void initData(){
        // example from: https://en.wikipedia.org/wiki/Shoelace_formula
        pentagon = Polygon.of("P",
                Point.of("P1", 1, 6),
                Point.of("P2", 3, 1),
                Point.of("P3", 7, 2),
                Point.of("P4", 4, 4),
                Point.of("P5", 8, 5)
        );
        triangle345 = Polygon.of("T",
                Point.of("A", 2.25, 4.5),
                Point.of("B", 2.25, 7.5),
                Point.of("C", 6.25, 7.5)
        );
    }

    static Stream<Arguments> surfaceFixture(){
        return Stream.of(
                Arguments.of(Named.of("pentagon Wikipedia", pentagon), 16.5),
                Arguments.of(Named.of("triangle 345",triangle345), 6.0)
        );
    }

    @ParameterizedTest
    @MethodSource("surfaceFixture")
    void testSurface(Polygon polygon, double expectedSurface) {
        double actualSurface = polygon.surface();
        assertEquals(expectedSurface, actualSurface);
    }

    static Stream<Arguments> perimeterFixture(){
        return Stream.of(
                Arguments.of(Named.of("pentagon Wikipedia", pentagon), 24.30799514569929),
                Arguments.of(Named.of("triangle 345",triangle345), 12.0)
        );
    }

    @ParameterizedTest
    @MethodSource("perimeterFixture")
    void testPerimeter(Polygon polygon, double expectedPerimeter) {
        double actualPerimeter = polygon.perimeter();
        assertEquals(expectedPerimeter, actualPerimeter, 1E-15);
    }
}