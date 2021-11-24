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
     * Devuelve la posicion del ultimo registro disponible en el fichero
     * Precondiciones: el fichero debe contener unicamente registros del tipo de la clase
     */
    public long lastIndex()
    {
        double a=file.length()/REG_SIZE;
        return (long) a-1;
    }

    public IndexManager(File file) {
        this.file = file;
        try {
            this.randomAccess = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public IndexManager(String fileName) {
        this.file= new File(fileName);
        try {
            this.randomAccess = new RandomAccessFile(new File(fileName), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para facilitar la escritura de cadenas en el fichero. Escribe un string en formato utf-8
     * poscondiciones: Se debe haber registrado el string en el contenido del fichero
     * @param string
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
     * Poscondiciones: se debe haber registrado el numero en el contenido del fichero
     * @param num
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
     * Precondiciones: el fichero debe no estar vacio
     */
    public String readString() {
        String value = "";
        try {
            value = randomAccess.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Metodo para leer un string del fichero binario
     * Precondiciones: el fichero debe no estar vacio
     * @param position
     */
    public long readLong(long position) {
        long num = 0;
        if(position>=lastIndex()) position=lastIndex();
        try {
            randomAccess.seek(position*REG_SIZE);
            num = randomAccess.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * Cambia el numero de la posicion de un registro a -1 para denotar que ese registro no existey asi no se reconozca
     * en las busquedas
     * @param position
     */
    public void borrarRegistro(long position)
    {
        try {
            randomAccess.seek(position*REG_SIZE);
            randomAccess.writeLong(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite obtener la posicion asociada a un DNI en el fichero
     * Precondiciones: el DNI debe ser valido
     * @param dni
     */
    public long getPosition(String dni)
    {
        String id;
        long position=-1;
        boolean found=false;
        for(long i=0 ; i<file.length()/REG_SIZE && !found; i++)
        {
            i=readLong(REG_SIZE*i);
            id=readString();
            if(id.equals(dni))
            {
                position=i;
                found=true;
            }
        }
        return position;
    }
}
