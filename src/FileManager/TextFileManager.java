package FileManager;

import Clases.Persona;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Clase dedicada a la gestion del fichero de texto al que se exportaran los datos de los clientes.
 * Contiene los metodos necesarios para la escritura de objetos de la clase persona en un fichero de texto
 */
public class TextFileManager {
    private File txtFile;
    private BufferedWriter writer;
    private Charset charset;

    /**
     * constructor de la clase TxtFileManager
     * @param charset codificacion del fichero de texto que se va a crear
     */
    public TextFileManager(Charset charset) {
        txtFile=new File("InfoClientesTxt");
        if(txtFile.exists()) createNewTxtFile();
        this.charset=charset;
    }

    /**
     * Metodo auxiliar que borra el fichero de texto ya existente y crea un nuevo fichero de texto con el mismo nombre
     */
    private void createNewTxtFile() {
        try {
            txtFile.delete();
            txtFile.createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Metodo auxiliar para inicializar el atributo writer de un objeto de esta clase
     */
    private void iniWriter() {
        try {
            writer=new BufferedWriter(new FileWriter(txtFile, charset, true));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Escribe los datos de un cliente dado en el fichero de texto
     * Precondiciones: client debe no ser null
     * Poscondiciones: se debe haber registrado al cliente en el fichero de texto
     * @param client cliente a escribir en el fichero txt
     */
    public void writeClient(Persona client)
    {
        iniWriter();
        try {
            writer.write(client.toString()+"|");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        closeWriter();
    }
    /**
     * Metodo auxiliar para cerrar el flujo del writer de un objeto de esta clase
     */
    private void closeWriter() {
        try {
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
