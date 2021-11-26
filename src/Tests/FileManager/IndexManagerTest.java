package Tests.FileManager;

import FileManager.IndexManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexManagerTest {
    IndexManager FIN;

    @BeforeEach
     void setUp() {
        FIN= new IndexManager("testFile");
        FIN.writeNumber(0);
        FIN.writeString("12345678R");
        FIN.writeNumber(1);
        FIN.writeString("32345678R");
        FIN.writeNumber(2);
        FIN.writeString("22345678R");
    }

    @Test
    void testLastIndex() {
        assertEquals (3, FIN.regCount());
    }

    @Test
    void testReadLong() {
        assertEquals(1, FIN.readLong(1));
    }
    @Test
    void testWriteReadRegistry()
    {
        assertEquals(1, FIN.readLong(1));
        assertEquals("32345678R", FIN.readString());
    }

    @Test
    void testGetPosition() {
        assertEquals(0, FIN.getPosition("12345678R"));
        assertEquals(1, FIN.getPosition("32345678R"));
        assertEquals(2, FIN.getPosition("22345678R"));
    }

    @Test
    void borrarRegistro() {
        FIN.borrarRegistro(2);
        assertEquals(-1, FIN.readLong(2));
    }
}