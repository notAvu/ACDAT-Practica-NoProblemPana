package Clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    //No testeare el metodo validar ya que simplemente hace una llamada a los metodos de la clase DniValidator que
    // ya han sido testeados en su corresopondiente clase
    Persona pValid=new Persona("Saturnino", "Marquez", "12345678A", "lejos", "617822501");
    Persona pValidCopy=new Persona("Saturnino", "Marquez", "12345678A", "lejos", "617822501");
    Persona pFail=new Persona("Saturnino", "Marquez", "12312678A", "lejos", "0");
    @Test
    void validarTlfnTestTrue() {
        assertTrue(pValid.validarTlfn());
    }
    @Test
    void validarTlfnTestFalse() {
        assertFalse(pFail.validarTlfn());
    }

    @Test
    void testEqualsTest() {
        assertTrue(pValid.equals(pValidCopy));
    }
}