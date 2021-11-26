package FileManager;

import Clases.Persona;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Esta clase imlementa las funcionalidades necesarias para la lectura y escriturade objetos de la clase persona
 * directamente de un fichero binario con RandomAccessFile
 */
public class PersonaManager extends IndexManager{
    private static final int REG_SIZE=214;
    public PersonaManager(File file) {
        super(file);
    }
    public PersonaManager(String fileName){
        super(fileName);
    }
    /**
     * Metodo para escribir todos los atributos de un objeto Persona en el fichero<br/>
     * Precondiciones: client debe ser un objeto valido de la clase Persona<br/>
     * Poscondiciones: se deben haber registrado los atributos de la persona en el fichero<br/>
     * @param client
     */
    public void writePerson(Persona client, long position)
    {
        try {
            this.randomAccess.seek(position *REG_SIZE);
            this.writeString(client.getNombre());
            this.writeString(client.getApellidos());
            this.writeString(client.getDni());
            this.writeString(client.getDireccion());
            this.writeString(client.getNumTelefono());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo para facilitar la lectura de cadenas del fichero. Comienza a leer en la posción indicada y lee el primer
     * string del registro
     * Precondiciones: el fichero debe contener al menos un registro
     * Poscondiciones: el método devuelve el primer atributo del registro
     * @param position
     */
    public String readFirst(long position)
    {
        try {
            randomAccess.seek(position * REG_SIZE);
        } catch (IOException e) {
            System.err.println("No se han encontrado mas registros");
        }
        return readString();
    }
    /**
     * Lee completamente un objeto de la clase Persona
     * Precondiciones: el fichero debe contener al menos un registro de la clase Persona
     * Poscondiciones: ninguna
     * @param position
     */
    public Persona readPerson(long position)
    {
        Persona x=new Persona(this.readFirst(position), this.readString(), this.readString(), this.readString(), this.readString());
        return x;
    }
    /**
     * Pasa todos los datos del fichero binario a un fichero de texto con la codificación indicada
     * Precondiciones: el fichero debe contener al menos un elemento de la clase Person
     * Poscondicones: se debe haber creado el fichero de texto y registrado los cambios en este
     * @param charset
     */
    public void export(Charset charset)
    {
        int i=0;
        Persona aux;
        TextFileManager textFileManager=new TextFileManager(charset);
        while(!readPerson(i).getDni().equals(""))
        {
            aux=readPerson(i);
            if(aux.validar()) {
                textFileManager.writeClient(aux);
            }
            i++;
        }
    }
}
