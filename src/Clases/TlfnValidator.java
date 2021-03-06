package Clases;

/**
 * Clase auxiliar con los metodos necesarios para comprobar la validez de un string indicado como numero de telefono
 */
public class TlfnValidator {
    private static final int NUMBER_SIZE=9;
    private String telephoneNumber;

    /**
     * Constructor. La clase validara el telefono introducido por parametro
     * @param telephoneNumber telefono a validar
     */
    public TlfnValidator(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Determina que el string introducido sea de longitud menor a 9
     * @return boolean numeroValido
     */
    public boolean validate()
    {
        return (this.telephoneNumber.length()==NUMBER_SIZE)&&noLetters();
    }

    /**
     * Este metodo asegura que todos los caracteres del numero de telefono sean numeros
     */
    private boolean noLetters() {
        boolean justNumbers=true;
        int i, j;
        String auxNumber;
        StringBuilder tlfn = new StringBuilder();
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (i = 0; i < this.telephoneNumber.length(); i++) {
            auxNumber = this.telephoneNumber.substring(i, i + 1);
            for (j = 0; j < numbers.length; j++) {
                if (auxNumber.equals(numbers[j])) tlfn.append(numbers[j]);
            }
        }
        if (tlfn.length() != NUMBER_SIZE) justNumbers = false;
        return justNumbers;
    }
}
