package validation.checker;


import validation.NotNull;
import java.lang.annotation.Annotation;
import java.util.Objects;

public class NotNullChecker implements Checker{
    @Override
    public boolean isValid(Annotation annotation, Object value) {
        boolean result = false;
        if (annotation instanceof NotNull) {
            result = Objects.nonNull(value);
        } else Checker.throwWrongChecker(annotation.annotationType(), getClass());
        return result;
    }

    // singleton pattern
    private static NotNullChecker instance = null;
    private NotNullChecker(){}

    public static NotNullChecker getInstance(){
        if (Objects.isNull(instance)) instance = new NotNullChecker();
        return instance;
    }
}
