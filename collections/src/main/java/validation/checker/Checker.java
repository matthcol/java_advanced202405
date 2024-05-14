package validation.checker;

import validation.ValidatorException;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;

public interface Checker {
    boolean isValid(Annotation annotation, Object value);

    static void throwWrongChecker(Class<? extends Annotation> annotationType,  Class<? extends Checker> checkerType){
        throw new ValidatorException(MessageFormat.format(
                "Wrong checker for this constraint (constraint={0}, checker={1}",
                annotationType.getName(),
                checkerType.getName()
        ));
    }
}
