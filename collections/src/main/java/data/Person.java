package data;

import lombok.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
// i.e. @Getter @Setter @ToString @EqualsHashCode @RequiredArgsConstructor
public class Person {
    @NonNull
    private int id;
    @NonNull
    private String name;
    private LocalDate birthdate;

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
