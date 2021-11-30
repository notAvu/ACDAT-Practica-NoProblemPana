package Main;

import Clases.DniValidator;
import Clases.Persona;
import Clases.TlfnValidator;
import FileManager.IndexManager;
import FileManager.PersonaManager;
import Vista.Menu;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    PersonaManager clients= new PersonaManager("Clientes");
    IndexManager indice= new IndexManager("Indice_clientes");
    IndexManager config= new IndexManager("config");

    String ans;
    Scanner scan= new Scanner(System.in);
    boolean exit=false;
    while(!exit) {
        Menu.printMenu();
        ans=scan.next();
        switch (ans) {
            case "1" -> addPersona(clients, scan, indice);
            case "2" -> {
                String dni=askDni(scan);
                if(indice.getPosition(dni)==-1||!clients.readPerson(indice.getPosition(dni)).validar()) Menu.dniNotFound();
                else System.out.println(clients.readPerson(indice.getPosition(dni)).toString());
            }
            case "3" -> {
                Menu.printInfo(Menu.DNI);
                String dni=askDni(scan);
                if(indice.getPosition(dni)==-1) Menu.dniNotFound();
                else removePersona(clients.readPerson(indice.getPosition(dni)), clients, indice);
            }
            case "4" -> {
                String selected="";
                while(!selected.equals("1")&&!selected.equals("2")&&!selected.equals("3")&&!selected.equals("4")&&!selected.equals("5")&&!selected.equals("6")){
                Menu.askConfig();
                selected=scan.next();
                config.writeString(selected);
                }
            }
            case "5" -> clients.export(getFileCharset(config.readString()));
            case "0"-> exit=true;
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
     * @param charsetChoice
     */
    private static Charset getFileCharset(String charsetChoice) {
        Charset encoding;
        switch (charsetChoice) {
            case "1"->encoding=StandardCharsets.US_ASCII;
            case "2"->encoding=StandardCharsets.ISO_8859_1;
            case "3"->encoding=StandardCharsets.UTF_16;
            case "4"->encoding=StandardCharsets.UTF_16LE;
            case "5"->encoding=StandardCharsets.UTF_16BE;
            default ->encoding=StandardCharsets.UTF_8;
        }
        return encoding;
    }

    /**
     * Añade un registro con el dni indice
     * precondiciones: el dni debe ser valido
     * poscondiciones: el fihcero debe contener el registro con los datos introducidos
     * @param manager
     * @param dni
     * @param position
     */
    private static void addToIndex(IndexManager manager,String dni, long position)
    {
        manager.writeNumber(position);
        manager.writeString(dni);
    }

    /**
     * Guarda los datos de una persona cuyos datos seran introducidos por teclado
     * precondiciones: se deben haber creado los ficheros de clientManager e indexManager
     * poscondiciones: el fihcero debe contener el registro con los datos introducidos
     * @param clientManager
     * @param scan
     * @param indexManager
     */
    private static void addPersona(PersonaManager clientManager, Scanner scan, IndexManager indexManager) {
        String nombre, apellido, dni, telefono, direccion;
        do {
            nombre = askNombre(scan);
            apellido = askApellido(scan);
            dni = askDni(scan);
            telefono = askTelefono(scan);
            direccion = askDireccion(scan);
        }while (!validateFields(nombre, apellido, dni, telefono, direccion));
        Persona persona = new Persona(nombre, apellido, dni, direccion, telefono);
        long nextPosition = getNextPosition(indexManager);
        clientManager.writePerson(persona, nextPosition);
        addToIndex(indexManager, dni, nextPosition);
    }

    /**
     * Metodo para validar el input del usuario asegurando que ninguno de los campos este vacio y que el telefono y el
     * dni sean validos segun los criterios de sus respectivas clases validadoras
     * @param nombre
     * @param apellido
     * @param dni
     * @param telefono
     * @param direccion
     */
    private static boolean validateFields(String nombre, String apellido, String dni, String telefono, String direccion)
    {
        boolean empty=nombre.equals("") || apellido.equals("") || dni.equals("") || telefono.equals("") || direccion.equals("");
        if(empty) Menu.emptyFields();
        boolean tlfnValid= new TlfnValidator(telefono).validate();
        boolean validDni= new DniValidator(dni).validar();
        return (!empty)&&validDni&&tlfnValid;
    }
     /**
     * Establece a una persona como eliminada de los ficheros del programa
     * Se cambia el dni del registro por uno no valido para determinar que es un registro eliminado
     * @param objetivo
     * @param clientManager
     * @param indexManager
     */
    private static void removePersona(Persona objetivo,PersonaManager clientManager, IndexManager indexManager)
    {
        long targetPosition=indexManager.getPosition(objetivo.getDni());
        if(targetPosition>-1) {
            Persona persona = new Persona(objetivo.getNombre(), objetivo.getApellidos(), "11111111U", objetivo.getDireccion(), objetivo.getNumTelefono());
            clientManager.writePerson(persona, targetPosition);
            addToIndex(indexManager, objetivo.getDni(), targetPosition);
        }
    }

    /**
     * Devuelve la siguiente posicion disponible para escritura del fichero
     * @param indexManager
     */
    private static long getNextPosition(IndexManager indexManager) {
        long nextPosition=0;
        long numRegistros= indexManager.regCount();
        if(numRegistros>0) nextPosition = numRegistros;
        return nextPosition;
    }

    private static String askDireccion(Scanner scan) {
        Menu.printInfo(Menu.DIRECCION);
        String direccion = scan.next();
        direccion +=scan.nextLine();
        return direccion;
    }


    private static String askApellido(Scanner scan) {
        Menu.printInfo(Menu.APELLIDO);
        return scan.next();
    }

    private static String askNombre(Scanner scan) {
        Menu.printInfo(Menu.NOMBRE);
        return scan.next();
    }

    private static String askTelefono(Scanner scan) {
        Menu.printInfo(Menu.TELEFONO);
        String telefono = scan.next();
        if(!new TlfnValidator(telefono).validate()) Menu.invalidTelephone();
        return telefono;
    }

    private static String askDni(Scanner scan) {
        Menu.printInfo(Menu.DNI);
        String dni = scan.next();
        if(!new DniValidator(dni).validar()) Menu.printInvalidDni();
        return dni;
    }

}
