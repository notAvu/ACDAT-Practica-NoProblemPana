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

    /**
     * Constructor de PersonaManager
     * @param file fichero en el que se quiere escribir
     */
    public PersonaManager(File file) {
        super(file);
    }
    /**
     * Constructor de PersonaManager
     * @param fileName nombre del fichero en el que se quiere escribir
     */
    public PersonaManager(String fileName){
        super(fileName);
    }
    /**
     * Escribe en el fichero los datos de un objeto persona en la posicion indicada del fichero
     * @param client persona con los datos que se quieren introducir en el fichero
     * @param position posicion del indice en que se quiere empezar a escribir los datos de la persona
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
     * @param position posicion desde la que se quiere empezar a leer
     * @return primer string leida de dicha posicion registro del fichero
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
     * @param position posicion de la persona en el fichero
     * @return objeto Persona leido de la posicion del fichero
     */
    public Persona readPerson(long position)
    {
        return new Persona(this.readFirst(position), this.readString(), this.readString(), this.readString(), this.readString());
    }
    /**
     * Pasa todos los datos del fichero binario a un fichero de texto con la codificación indicada
     * Cuando se encuentra se marca el dni del objeto persona auxiliar como vacio para salir del bucle
     * Precondiciones: el fichero debe contener al menos un elemento de la clase Person
     * Poscondicones: se debe haber creado el fichero de texto y registrado los cambios en este
     * @param charset la codificacion en la que se desea exportar el contenido del fichero
     */
    public void export(Charset charset)
    {
        int i=0;
        TextFileManager textFileManager=new TextFileManager(charset);
        Persona aux=readPerson(i);
        while(!aux.getDni().equals(""))
        {
            i++;
            if(aux.validar()) {
                textFileManager.writeClient(aux);
                aux.setDni("");
            }else{
            aux=readPerson(i);
            }
        }
    }
}
