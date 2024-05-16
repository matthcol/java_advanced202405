package geo.data;

import lombok.*;
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Circle extends Shape implements Mesurable2D {

    private Point center;

    private double radius;

    protected Circle(String name, Point center, double radius) {
        super(name);
        this.center = center;
        this.radius = radius;
    }

    public static Circle of(String name, Point center, double radius){
        return new Circle(name, center, radius);
    }

    @Override
    public double surface() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2.0 * Math.PI * radius;
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        center.translate(deltaX, deltaY);
    }
}
