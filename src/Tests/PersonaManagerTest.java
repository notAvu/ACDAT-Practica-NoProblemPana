package Tests;

import Clases.Persona;
import FileManager.PersonaManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class PersonaManagerTest {
    final PersonaManager CLIENTE = new PersonaManager(new File("Clientes"));
    final Persona p1 = new Persona("Saturnino", "Marquez", "12345678A", "lejos", "617822501");
    final Persona p2 = new Persona("Anselmo", "Calderon", "12345675B", "to lejo", "360822251");
    final Persona p3 = new Persona("Servando", "Cortazar", "12345675B", "A toma por culo", "360212251");

    private void escribirClientes() {
        CLIENTE.writePerson(p1, 0);
        CLIENTE.writePerson(p2, 1);
        CLIENTE.writePerson(p3, 2);
    }

    @Test
    void writeReadPersonTest() {
        escribirClientes();
        assertEquals(CLIENTE.readPerson(1), p2);
        assertEquals(CLIENTE.readPerson(0), p1);
        assertEquals(CLIENTE.readPerson(2), p3);
    }

    @Test
    void exportTest() {
        escribirClientes();
        CLIENTE.export(StandardCharsets.UTF_16);
    }
}