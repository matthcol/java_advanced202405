package tu.validation;

import data.Person;
import org.junit.jupiter.api.Test;
import validation.Validator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testIsvalid_okNoConstraint(){
        String text = "valid";
        boolean valid = Validator.isValid(text);
        assertTrue(valid, "valid object");
    }

    @Test
    void testIsValid_ok(){
        var person = new Person(1, "Brad", LocalDate.of(1963, 12, 18));
        boolean valid = Validator.isValid(person);
        assertTrue(valid, "valid object");
    }

    @Test
    void testIsValid_koNameNull(){
        var person = new Person(1, null, LocalDate.of(1963, 12, 18));
        boolean valid = Validator.isValid(person);
        assertFalse(valid, "invalid object");
    }

    @Test
    void testIsValid_koNameSize(){
        var person = new Person(1, "", LocalDate.of(1963, 12, 18));
        boolean valid = Validator.isValid(person);
        assertFalse(valid, "invalid object");
    }

    @Test
    void testIsValid_koBirthdateMin(){
        var person = new Person(1, "Brad", LocalDate.MIN);
        boolean valid = Validator.isValid(person);
        assertFalse(valid, "invalid object");
    }


}