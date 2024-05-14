package validation;

import validation.checker.Checker;
import validation.checker.MinDateChecker;
import validation.checker.NotNullChecker;
import validation.checker.SizeChecker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Arrays;

public class Validator {

    public static boolean isValid(Object object){
        // get object type
        var objectClass = object.getClass();

        // get validation annotations on fields
        // filter annotation (package = validation)
        // NB: TODO introspect parent class fields
        var fieldWithWonstraintsList = Arrays.stream(objectClass.getDeclaredFields())
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                .map(f -> new PairFieldConstraints(
                        f,
                        Arrays.stream(f.getAnnotations())
                                .filter(a -> a.annotationType().getPackageName().equals(Validator.class.getPackageName()))
                                .toList()
                ))
                .filter(pairFieldConstraints -> !pairFieldConstraints.getConstraints().isEmpty())
                .toList();
        return fieldWithWonstraintsList.stream().allMatch(
                fieldWithConstraints -> {
                    Object valueField = fieldValue(object, fieldWithConstraints.getField());
                    return fieldWithConstraints.getConstraints().stream()
                            .allMatch(constraint -> constraintIsSatisfied(constraint, valueField));
                }
        );

    }

    public static Object fieldValue(Object object, Field f){
        Class<?> objectClass = object.getClass();
        String fieldName = f.getName();
        Class<?> fieldType = f.getType();
        String getterPrefix  = ((fieldType == boolean.class) || (fieldType == Boolean.class)) ?
                "is" : "get";
        String getterName = getterPrefix + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        try {
            Method getter = objectClass.getMethod(getterName);
            return  getter.invoke(object);
        } catch (NoSuchMethodException e) {
            throw new ValidatorException(MessageFormat.format("Getter is missing (class={0}, getter={1}",
                    objectClass.getName(),
                    getterName));
        } catch (InvocationTargetException| IllegalAccessException e) {
            throw new ValidatorException(MessageFormat.format("Getter invokation failed (getter={0}, reason={1})",
                    getterName,
                    e.getMessage()));
        }
    }
    public static boolean constraintIsSatisfied(Annotation constraint, Object value){
        Checker checker =  switch (constraint) {
            case MinDate md -> MinDateChecker.getInstance();
            case NotNull nn -> NotNullChecker.getInstance();
            case Size s -> SizeChecker.getInstance();
            default -> throw new ValidatorException(MessageFormat.format(
                    "No checker available for this constraint (constraint={0})",
                    constraint.annotationType().getName())
            );
        };
        return checker.isValid(constraint, value);
    }
}
