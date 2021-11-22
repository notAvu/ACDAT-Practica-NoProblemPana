package FileManager;

import Clases.Persona;

import java.io.*;
import java.nio.charset.Charset;

public class TextFileManager {
    File txtFile;
    BufferedWriter writer;
    BufferedReader reader;
    Charset charset;
    public TextFileManager(Charset charset)
    {
        txtFile=new File("InfoClientesTxt");
        this.charset=charset;
    }
    private void iniReader()
    {
        try {
            reader=new BufferedReader(new FileReader(txtFile, charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void iniWriter() {
        try {
            writer=new BufferedWriter(new FileWriter(txtFile, charset, true));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void writeClient(Persona client)
    {
        iniWriter();
        try {
            writer.write(client.toString());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        closeWriter();
    }
    private void closeWriter() {
        try {
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
