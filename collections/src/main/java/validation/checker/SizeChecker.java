package validation.checker;

import validation.Size;
import validation.ValidatorException;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Objects;

public class SizeChecker implements Checker{
    @Override
    public boolean isValid(Annotation annotation, Object value) {
        boolean result = false;
        if (annotation instanceof Size specificAnnotation) {
            if (Objects.isNull(value)) result = true;
            else {
                int minValue = specificAnnotation.min();
                int maxValue = specificAnnotation.max();
                int size = switch (value) {
                    case String text -> text.length();
                    case Collection<?> coll -> coll.size();
                    default -> throw new ValidatorException(MessageFormat.format(
                            "Unable to retrieve size for this type (type={0})",
                            value.getClass().getName()
                    ));
                };
                result = (minValue <= size) && (size <= maxValue);
            }
        } else Checker.throwWrongChecker(annotation.annotationType(), getClass());
        return result;
    }

    // singleton pattern
    private static SizeChecker instance = null;
    private SizeChecker(){}

    public static SizeChecker getInstance(){
        if (Objects.isNull(instance)) instance = new SizeChecker();
        return instance;
    }
}
