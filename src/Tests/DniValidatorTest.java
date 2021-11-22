package Tests;

import Clases.DniValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DniValidatorTest {

    DniValidator validator;

    @Test
    void testDniValido() {
        validator= new DniValidator("45698213L");
        assertTrue(validator.validar());
    }
    @Test
    void testLetraInvalida()
    {
        validator= new DniValidator("45698213A");
        assertFalse(validator.validar());
    }
    @Test
    void testNumCorto()
    {
        validator= new DniValidator("2L");
        assertFalse(validator.validar());
    }
    @Test
    void testNotNum()
    {
        validator= new DniValidator("ASDQWERFL");
        assertFalse(validator.validar());
    }
}