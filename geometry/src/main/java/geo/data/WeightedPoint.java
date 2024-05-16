package geo.data;

import lombok.*;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class WeightedPoint extends Point {

    private double weight;

    protected WeightedPoint(String name, double x, double y, double weight) {
        super(name, x, y);
        this.weight = weight;
    }

    public static WeightedPoint of(String name, double x, double y, double weight) {
        return new WeightedPoint(name, x, y, weight);
    }
}
