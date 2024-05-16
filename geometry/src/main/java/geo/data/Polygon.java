package geo.data;

import lombok.*;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @ToString(callSuper = true)
public class Polygon extends Shape implements Mesurable2D {

    private List<Point> vertices = new ArrayList<>();

    public static Polygon of(String name, Collection<? extends Point> vertices){
        var polygon = new Polygon();
        polygon.setName(name);
        polygon.vertices.addAll(vertices);
        return polygon;
    }

    public static Polygon of(String name, Point... vertices) {
        return of(name, Arrays.asList(vertices));
    }

    @Override
    public double surface() {
        return 0;
    }

    @Override
    public double perimeter() {
        double res = 0.0;
        Point prec = vertices.getLast();
        for (Point next: vertices){
            res += prec.distance(next);
            prec = next;
        }
        return res;
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        vertices.forEach(v -> v.translate(deltaX, deltaY));
    }
    @Override
    public String toString() {
        return MessageFormat.format("Polygon(name={0}, vertices count={1} names={2})",
                getName(),
                vertices.size(),
                vertices.stream()
                        .map(Shape::getName)
                        .collect(Collectors.joining("-"))
                );
    }
}
