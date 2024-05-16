package geo.data;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Point extends Shape {
    private double x;
    private double y;

    protected Point(String name, double x, double y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    public static Point of(String name, double x, double y){
        return new Point(name,x, y);
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public double distance(Point other){
        return Math.hypot(x - other.x, y - other.y);
    }
}
