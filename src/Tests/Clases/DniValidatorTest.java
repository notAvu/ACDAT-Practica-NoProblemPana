package Tests.Clases;

import Clases.DniValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DniValidatorTest {

    DniValidator validator;

    @Test
    void testDniValido() {
        validator= new DniValidator("45698213L");
        assertTrue(validator.validate());
    }
    @Test
    void testLetraInvalida()
    {
        validator= new DniValidator("45698213A");
        assertFalse(validator.validate());
    }
    @Test
    void testNumCorto()
    {
        validator= new DniValidator("2L");
        assertFalse(validator.validate());
    }
    @Test
    void testNotNum()
    {
        validator= new DniValidator("ASDQWERFL");
        assertFalse(validator.validate());
    }
}