package generic;

import data.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class DemoGeneric {

    @Test
    void demoGenericCollection(){
        // J11
        var cities = new ArrayList<String>();
        // J7-J8
        List<String> cities2 = new ArrayList<>();
        // J5-J6
        List<String> cities3 = new ArrayList<String>();
        // J1.2 -> J1.4
        List cities4 = new ArrayList();
        // J5+: E=any type
        List<?> objects = new ArrayList<>();

        cities.add("Montauban"); // OK
        // cities.add(LocalDate.of(1,1,1)); // compilation error

        cities4.add("Montauban");
        cities4.add(LocalDate.of(1,1,1));

        for (String city: cities) {
            System.out.println(city);
        }

        // compilation error
        // for (Person city: cities) {
        //     System.out.println(city);
        // }

    }

    @Test
    void demoGenericPair(){
        // Pair<String, LocalDate> i.e Pair<T, U> with T=String and U=LocalDate
        //<T,U> Pair.of with T=String and U=LocalDate (by inference)
        Pair<String, LocalDate> pair = Pair.of("Révolution Fr", LocalDate.of(1789,7,14));
        System.out.println(pair);
        String name = pair.getFirst();
        LocalDate date = pair.getSecond();

        Pair<String, Integer> version = Pair.of("Java", 21);
        System.out.println(version);

        var pair2 = Pair.of("Java", 5);

        var pair3 = Pair.<String, Integer>of("Java",8);

        var pair4 = pair.map(String::toUpperCase, LocalDate::getYear);
        System.out.println(pair4);
    }
}
