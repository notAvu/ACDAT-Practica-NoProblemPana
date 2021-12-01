package FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Esta clase es la base para gestionar ficheros con RandomAccessFile conteniendo los métodos de escritura y lectura de
 * datos básicos en un fichero binario
 *
 * Esta clase ha sido diseñada especificamente para el tratamiento de datos tipo indice (long+string)
 *
 * Intentaba implementar el borrado de registros con ficheros de movimiento. Si no está implementado es que no he
 * he conseguido pulirlo para la entrega con la practica
 */
public class IndexManager {
    private final static int REG_SIZE=19;
    private File file;
    protected RandomAccessFile randomAccess;

    /**
     * Calcula el numero de registros en base al tamaño de registro definido en la clase
     * @return long numero de registros
     */
    public long regCount() {
        return file.length()/REG_SIZE;
    }

    /**
     * Constructor
     * @param file fichero en el que se va a escribir
     */
    public IndexManager(File file) {
        this.file = file;
        createRandomAccessFile(file);
    }
    /**
     * Constructor
     * @param fileName nombre del fichero en el que se va a escribir
     */
    public IndexManager(String fileName) {
        this.file= new File(fileName);
        createRandomAccessFile(new File(fileName));
    }

    private void createRandomAccessFile(File file) {
        try {
            this.randomAccess = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para facilitar la escritura de cadenas en el fichero. Escribe un string en formato utf-8
     * @param string cadena que se quiere escribir en el fichero
     */
    public void writeString(String string) {
        try {
            randomAccess.writeUTF(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo para facilitar la escritura de enteros en el fichero
     * @param num numero que se quiere escribir en el fichero
     */
    public void writeNumber(long num) {
        try {
            randomAccess.writeLong(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para leer un string del fichero binario
     * @return string leido del fichero
     */
    public String readString() {
        String stringRead = "";
        try {
            stringRead = randomAccess.readUTF();
        } catch (IOException e) {
            System.err.println("El registro esta vacio");
        }
        return stringRead;
    }

    /**
     * Metodo para leer un long del fichero binario dada la posicion. Si intenta leer mas alla de la longitud del
     * contenido del fichero, lee el ultimo registro de este
     * @param position posicionde la que se quiere leer el numero
     * @return numero leido del fichero
     */
    public long readLong(long position) {
        long numberRead = 0;
        if(position>=regCount()) {position=regCount();}
        try {
            randomAccess.seek(position*REG_SIZE);
            numberRead = randomAccess.readLong();
        } catch (IOException e) {
            System.err.println("El registro esta vacio");
        }
        return numberRead;
    }

    /**
     * Cambia el numero de la posicion de un registro a -1 para denotar que ese registro no existe y asi no se reconozca
     * en las busquedas
     * @param position posicion del registro que se quiere borrar
     */
    public void borrarRegistro(long position) {
        try {
            randomAccess.seek(position*REG_SIZE);
            randomAccess.writeLong(-1);
        } catch (IOException e) {
            System.err.println("El registro esta vacio");
        }
    }

    /**
     * Permite obtener la posicion asociada a un DNI en el fichero indice
     * @param dni dni de la persona cuya posicion queremos obtener
     * @return posicion del dni en el fichero indice
     */
    public long getPosition(String dni) {
        String id;
        long position=-1;
        boolean found=false;
        for(long i=0 ; i<regCount() && !found; i++) {
            i=readLong(i);
            id=readString();
            if(id.equals(dni)) {
                position=i;
                found=true;
            }
        }
        return position;
    }
}
