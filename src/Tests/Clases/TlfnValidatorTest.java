package Tests.Clases;

import Clases.TlfnValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TlfnValidatorTest {

    @Test
    void validateTrue() {
        TlfnValidator tlfnValido= new TlfnValidator("606606606");
        assertTrue(tlfnValido.validate());
    }
    @Test
    void validateShortnumber() {
        TlfnValidator tlfnValido= new TlfnValidator("616");
        assertFalse(tlfnValido.validate());
    }
    @Test
    void validateLongNumber() {
        TlfnValidator tlfnValido= new TlfnValidator("606606606778");
        assertFalse(tlfnValido.validate());
    }
    @Test
    void validateNoLetters() {
        TlfnValidator tlfnValido= new TlfnValidator("A12606606");
        assertFalse(tlfnValido.validate());
    }
}