package Vista;

/**
 * Esta clase cumple la funcion de vista en el MVC guardando todos los mensaje que se muestran por pantalla al usuario
 */
public class Menu {
    public static final String MAIN_MENU= """
            Gestor de Ficheros:
            1.Registrar Cliente
            2.Consultar Cliente
            3.Borrar Cliente
            4.Cambiar configuracion
            5.Exportar
            0.Salir
            """;
    public static final String NOMBRE= "Nombre del cliente:";
    public static final String APELLIDO="Apellido del cliente";
    public static final String DNI="DNI del cliente";
    public static final String DIRECCION="Direccion del cliente:";
    public static final String TELEFONO="Numero de telefono del cliente";

    public static void printMenu()
    {
        System.out.println(MAIN_MENU);
    }
    public static void printInfo(String info){
        System.out.println(info);
    }
    public static void printInvalidDni()
    {
        System.out.println("El DNI no es valido");
    }
    public static void dniNotFound()
    {
        System.out.println("El dni introducido no ha sido encontrado en el fichero");
    }
    public static void emptyFields()
    {
        System.out.println("No puede haber campos en blanco");
    }
    public static void invalidTelephone()
    {
        System.out.println("El valor introducido no es un numero de telefono valido");
    }
    public static void askConfig()
    {
        System.out.println("""
                Seleccione el charset a utilizar:
                1.US_ASCII
                2.ISO_8859_1
                3.UTF_16
                4.UTF_16LE
                5.UTF_16BE
                6.UTF_8
                """);
    }
}
