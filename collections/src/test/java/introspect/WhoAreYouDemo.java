package introspect;

import data.Person;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class WhoAreYouDemo {

    @Test
    void demoWhoAreYou() {
        var date = LocalDate.of(1963,12,18);
        var person = new Person(1, "Brad Pitt", date);
        var list = List.of("Bullet Train", "Benjamin Button", "Once Upon A Time in Hollywood");
        var path = Path.of("/persons.tsv");
        Function<Person, LocalDate> f1 = Person::getBirthdate;
        Predicate<String> predicate = text -> text.length() > 10;
        Stream.of(date, person, list, path, f1, predicate)
                .forEach(o -> {
                    System.out.print(o + " -> ");
                    ScanClass.scan(o.getClass());
                });
    }

    @Test
    void DemoInspectTypes(){
        Stream.of(Person.class, List.class, ArrayList.class, LocalDate.class, Month.class, int.class)
                .forEach(ScanClass::scan);
    }

}
