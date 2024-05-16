package geo.data;

import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;
class CircleDemo {

    @Test
    void demoCircle(){
        var pt = Point.of("A", 1.0, 3.0);
        var circles = IntStream.range(1,11)
                .peek(System.out::println)
                .mapToObj(r -> Circle.of("C"+r, pt, r))
                .toList();
        circles.forEach(System.out::println);
    }
}
