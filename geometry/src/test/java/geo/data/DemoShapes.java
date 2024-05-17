package geo.data;

import org.junit.jupiter.api.Test;
import utils.StreamUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class DemoShapes {

    @Test
    void demoShapes(){
        var shapes = List.of(
                Polygon.of("P",
                        Point.of("P1", 1, 6),
                        Point.of("P2", 3, 1),
                        Point.of("P3", 7, 2),
                        Point.of("P4", 4, 4),
                        Point.of("P5", 8, 5)
                ),
                Segment.of(),
                Point.of("Z", 0, 0),
                Polygon.of("T",
                        Point.of("A", 2.25, 4.5),
                        Point.of("B", 2.25, 7.5),
                        Point.of("C", 6.25, 7.5)
                ),
                Circle.of(
                        "C1",
                        Point.of("o", 1, 2),
                        10
                ),
                WeightedPoint.of("W", 1, 2, 3)
        );
        shapes.forEach(System.out::println);

        // x max of all points from shapes
        // 1. filter: only Point
        // 2. getX
        // 3. max
        var maxX = shapes.parallelStream()
                .filter(shape -> shape instanceof Point)
                .map(shape -> (Point) shape)
                .mapToDouble(Point::getX)
                .max();
        System.out.println("max x:" + maxX);

        // total surface of all Mesurable2D shapes
        var totalSurface = shapes.parallelStream()
                .filter(shape -> shape instanceof Mesurable2D)
                .map(shape -> (Mesurable2D) shape)
                .peek(System.out::println) // DEBUG
                .mapToDouble(Mesurable2D::surface)
                .sum();
        System.out.println("total surface: " + totalSurface);

        totalSurface = StreamUtils.filterByType(shapes.parallelStream(), Mesurable2D.class)
                .mapToDouble(Mesurable2D::surface)
                .sum();
        System.out.println("total surface: " + totalSurface);

        // shapes instanceof LocalDate: CompilationError
        var count = StreamUtils.filterByType(shapes.parallelStream(), LocalDate.class)
                .count();
        System.out.println("Count shapes as LocalDate: " + count);
    }

    @Test
    void demoParallelStream() throws IOException {
        var origin = Point.of("O", 0,0);
        var circles = IntStream.range(1, 1_000_000)
                .mapToObj(radius -> Circle.of("C" + radius, origin, radius))
                .toList();
        var longText = circles.parallelStream()
                .map(Shape::getName)
                .collect(Collectors.joining("\n"));
        try (var outfile = Files.newBufferedWriter(Path.of("longtext.text"))){
            outfile.write(longText);
        } // close auto
    }

    @Test
    void demoCollectorCharacteristics(){
        Stream.of(
                Collectors.joining(","),
                Collectors.toList(),
                Collectors.summingDouble(Point::getX),
                Collectors.toConcurrentMap(Shape::getName, UnaryOperator.identity())
        )
                .map(Collector::characteristics)
                .forEach(System.out::println);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
