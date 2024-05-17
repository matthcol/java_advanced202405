package utils;

import java.util.stream.Stream;

public class StreamUtils {

    // Stream<Shape>: filterByType(Point) => Stream<Point>
    // Stream<Shape>: filterByType(Mesurable2D) => Stream<Mesurable2D>

    // Stream<T>: filterByType(U) => Stream<U>
    public static <T,U> Stream<U> filterByType(Stream<T> stream, Class<U> classU){
        return stream.filter(classU::isInstance)
                .map(classU::cast);
    }
}
