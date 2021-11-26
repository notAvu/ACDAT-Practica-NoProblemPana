package Main;

import Clases.DniValidator;
import Clases.Persona;
import FileManager.IndexManager;
import FileManager.PersonaManager;
import Vista.Menu;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main { public static void main(String[] args) {
    PersonaManager clients= new PersonaManager("Clientes");
    IndexManager indice= new IndexManager("Indice_clientes");
    IndexManager config= new IndexManager("config");
    String ans;
    Scanner scan= new Scanner(System.in);
    boolean exit=false;
    while(!exit)
    {
        Menu.printMenu();
        ans=scan.next();
        switch (ans) {
            case "1" -> addPersona(clients, scan, indice);
            case "2" -> {
                Menu.printInfo(Menu.DNI);
                String input=scan.next();
                String dni=askDni(scan);
                if(indice.getPosition(dni)==-1) {
                    Menu.dniNotFound();
                }else {
                    indice.getPosition(input);
                    System.out.println();
                }
            }
            case "3" -> {
                Menu.printInfo(Menu.DNI);
                String dni=askDni(scan);
                if(indice.getPosition(dni)==-1) {
                    Menu.dniNotFound();
                }
            }
            case "4" -> {
                String selected="";
                while(!selected.equals("1")&&!selected.equals("2")&&!selected.equals("3")&&!selected.equals("4")&&!selected.equals("5")&&!selected.equals("6")){
                Menu.askConfig();
                selected=scan.next();
                config.writeString(selected);
                }
            }
            case "5" -> clients.export(getFileCharset(config));
            case "0"->exit=true;
        }
    }
}

    /**
     * Metodo que recibe un numero almacenado en el fichero que corresponde a uno de estos Charset
     * El fichero guarda
     * 1-> US_ASCII
     * 2-> ISO_8859_1
     * 3-> UTF_16
     * 4-> UTF_16LE
     * 5-> UTF_16BE
     * 6-> UTF8
     * @param config
     * @return
     */
    private static Charset getFileCharset(IndexManager config) {
        Charset encoding;
        switch (config.readString())
        {
            case "1"-> encoding=StandardCharsets.US_ASCII;
            case "2"->encoding=StandardCharsets.ISO_8859_1;
            case "3"->encoding=StandardCharsets.UTF_16;
            case "4"->encoding=StandardCharsets.UTF_16LE;
            case "5"->encoding=StandardCharsets.UTF_16BE;
            default -> encoding=StandardCharsets.UTF_8;
        }
        return encoding;
    }

    private static void addToIndex(IndexManager manager,String dni, long position)
    {
        manager.writeNumber(position);
        manager.writeString(dni);
    }

    private static void addPersona(PersonaManager clientManager, Scanner scan, IndexManager indexManager) {
        String nombre="";
        String apellido="";
        String dni="";
        String telefono="";
        String direccion="";
        while (nombre.equals("") || apellido.equals("") || dni.equals("") || telefono.equals("")|| direccion.equals("")) {
            nombre = askNombre(scan);
            apellido = askApellido(scan);
            dni = askDni(scan);
            telefono = askTelefono(scan);
            direccion = askDireccion(scan);
        }
        Persona persona = new Persona(nombre, apellido, dni, direccion, telefono);
        long nextPosition=0;
        long numRegistros=indexManager.regCount();
        if(numRegistros>0){nextPosition=numRegistros;}
        clientManager.writePerson(persona, nextPosition);
        addToIndex(indexManager, dni, nextPosition);
    }

    private static String askDireccion(Scanner scan) {
        String direccion;
        Menu.printInfo(Menu.DIRECCION);
        direccion = scan.next();
        direccion +=scan.nextLine();
        return direccion;
    }

    private static String askTelefono(Scanner scan) {
        String telefono;
        Menu.printInfo(Menu.TELEFONO);
        telefono = scan.next();
        return telefono;
    }

    private static String askApellido(Scanner scan) {
        String apellido;
        Menu.printInfo(Menu.APELLIDO);
        apellido = scan.next();
        return apellido;
    }

    private static String askNombre(Scanner scan) {
        String nombre;
        Menu.printInfo(Menu.NOMBRE);
        nombre = scan.next();
        return nombre;
    }

    private static String askDni(Scanner scan) {
        String dni;
        Menu.printInfo(Menu.DNI);
        dni = scan.next();
        DniValidator validator=new DniValidator(dni);
        if(!validator.validar()) {
            Menu.printInvalidDni();
            dni="";
        }
        return dni;
    }

}
