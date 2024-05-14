package introspect;

import java.util.Arrays;

public class ScanClass {

    public static void scan(Class<?> aClass){
        System.out.println(aClass + ":");
        System.out.println("\t- name: " + aClass.getName());
        System.out.println("\t- canonical name: " + aClass.getCanonicalName());
        System.out.println("\t- simple name: " + aClass.getSimpleName());

        // list constructor
        System.out.println("\t- public constructors:");
        Arrays.stream(aClass.getConstructors())
                .forEach(constructor -> System.out.println("\t\t* " + constructor));

        // list methods
        System.out.println("\t- public methods:");
        for (var method: aClass.getMethods()){
            System.out.println("\t\t* " + method);
        }

        // list fields
        System.out.println("\t- public fields:");
        for (var field: aClass.getFields()){
            System.out.println("\t\t* " + field);
        }

        System.out.println("\t- all fields:");
        for (var field: aClass.getDeclaredFields()){
            System.out.println("\t\t* " + field);
            for (var annotation: field.getAnnotations()) {
                System.out.println("\t\t\t~ " + annotation);
            }
        }

    }
}















