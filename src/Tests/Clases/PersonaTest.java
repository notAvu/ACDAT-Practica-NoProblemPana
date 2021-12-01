package Tests.Clases;

import Clases.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    //No testeare el metodo validar ya que simplemente hace una llamada a los metodos de validacion que
    // ya han sido testeados en su corresopondiente clase
    static Persona pValid;
    static Persona pValidCopy;
    static Persona pFail;
    @BeforeEach void beforeEach()
    {
        pValid=new Persona("Saturnino", "Marquez", "12345678A", "lejos", "617822501");
        pValidCopy=new Persona("Saturnino", "Marquez", "12345678A", "lejos", "617822501");
        pFail=new Persona("Saturnino", "Marquez", "12312678A", "lejos", "0");
    }
    @Test
    void setGetNombre() {
        pValidCopy.setNombre("Pepe");
        assertEquals(pValidCopy.getNombre(),"Pepe");
    }

    @Test
    void setGetApellidos() {
        pValidCopy.setApellidos("Gonzalez");
        assertEquals(pValidCopy.getApellidos(),"Gonzalez");
    }

    @Test
    void setGetDni() {
        pValidCopy.setDni("01452367D");
        assertEquals(pValidCopy.getDni(),"01452367D");
    }

    @Test
    void setGetDireccion() {
        pValidCopy.setDireccion("Calle melancolia 7");
        assertEquals(pValidCopy.getDireccion(),"Calle melancolia 7");
    }

    @Test
    void setgetNumTelefono() {
        pValidCopy.setNumTelefono("616258348");
        assertEquals(pValidCopy.getNumTelefono(),"616258348");
    }

    @Test
    void testToString() {
        assertEquals(pValidCopy.toString(),"Saturnino,Marquez,12345678A,lejos,617822501");
    }

    @Test
    void testEqualsTest() {
        assertEquals(pValid, pValidCopy);
    }

    @Test
    void testnotEquals(){
        assertNotEquals(pValid, pFail);
    }
}