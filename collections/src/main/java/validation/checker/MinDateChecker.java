package validation.checker;

import validation.MinDate;
import validation.ValidatorException;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class MinDateChecker implements Checker{
    @Override
    public boolean isValid(Annotation annotation, Object value) {
        boolean result = false;
        if (annotation instanceof MinDate specificAnnotation) {
            String minDateStr = specificAnnotation.value();
            try {
                LocalDate minDate = LocalDate.parse(minDateStr);
                if (Objects.isNull(value)) result = true;
                else {
                    LocalDate valueDate = (LocalDate) value;
                    result = !minDate.isAfter(valueDate);
                }
            } catch (DateTimeParseException e) {
                throw new ValidatorException(MessageFormat.format(
                        "Min date format incorrect (value={0})",
                        minDateStr
                ));
            } catch (ClassCastException e) {
                throw new ValidatorException(MessageFormat.format(
                        "Constraint applied on wrong data type (constraint={0}, type={1})",
                        MinDate.class.getName(), value.getClass().getName()
                ));
            }
        } else Checker.throwWrongChecker(annotation.annotationType(), getClass());
        return result;
    }

    // singleton pattern
    private static MinDateChecker instance = null;
    private MinDateChecker(){}

    public static MinDateChecker getInstance(){
        if (Objects.isNull(instance)) instance = new MinDateChecker();
        return instance;
    }

}
