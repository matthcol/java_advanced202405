package geo.data;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Shape {
    private String name;

    public abstract void translate(double deltaX, double deltaY);
}
