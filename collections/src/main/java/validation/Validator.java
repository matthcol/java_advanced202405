package validation;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

public class Validator {

    public static boolean isValid(Object object){
        // get object type
        var objectClass = object.getClass();

        // get validation annotations on fields
        // filter annotation (package = validation)
        // NB: TODO introspect parent class field
        var fieldWithWonstraints = Arrays.stream(objectClass.getDeclaredFields())
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                .map(f -> new PairFieldConstraints(
                        f,
                        Arrays.stream(f.getAnnotations())
                                .filter(a -> a.annotationType().getPackageName().equals(Validator.class.getPackageName()))
                                .toList()
                ))
                .filter(pairFieldConstraints -> !pairFieldConstraints.getConstraints().isEmpty())
                .toList();
        System.out.println(fieldWithWonstraints);

        // for each validation constraint:
        // - get value field
        // - check value against constraint


        // TODO
        return false;
    }
}
