package validation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

@Data
@AllArgsConstructor
public class PairFieldConstraints {
    private Field field;
    private List<Annotation> constraints;
}
