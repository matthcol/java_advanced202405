package data;

import lombok.*;
import validation.MinDate;
import validation.NotNull;
import validation.Size;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;

@Data
// @RequiredArgsConstructor
@AllArgsConstructor
// i.e. @Getter @Setter @ToString @EqualsHashCode @RequiredArgsConstructor
public class Person {

    // @NonNull
    private int id;

    @NotNull
    @Size(min=1)
    // @NonNull
    private String name;

    @MinDate("1800-01-01")
    private LocalDate birthdate;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean hasBirthdate() {
        return Objects.nonNull(birthdate);
    }

    public int getBirthYear(){
        if (!hasBirthdate()) throw new NoSuchElementException("birthdate");
        return getBirthdate().getYear();
    }

    public boolean bornThisYear(int year){
        return hasBirthdate() && (getBirthYear() == year);
    }

    public boolean bornBetweenYears(int year1, int year2){
        return hasBirthdate() && (year1 <= getBirthYear()) && (getBirthYear() <= year2);
    }
}
