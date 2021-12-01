package Clases;

/**
 * Esta clase se encarga de validar el formato de un dni.
 * La clase es una ya existente que he adaptado a el proyecto limpiando un poco las partes del codigo mas "Chapuzeras"
 * y haciendo que cumpla ciertos criterios
 */
public class DniValidator {
    private String dni;
    private final int DNI_LENGTH=9;

    /**
     * Constructor de la clase, recibe un dni que sera el que se valide
     * @param inputDni dni a validar
     */
    public DniValidator(String inputDni) {
        this.dni = inputDni;
    }

    /**
     * Valida que el DNI cumpla las características de un DNI real
     * @return boolean segun la validez del dni de la instancia de esta clase
     */
    public boolean validate() {
        String mayus;
        boolean valido=false;
        if (dni.length() == DNI_LENGTH && Character.isLetter(this.dni.charAt(8))) {
            mayus = (this.dni.substring(8)).toUpperCase();
            if (justNumbers() && dniCharacter().equals(mayus)) {
                valido = true;
            }
        }
        return valido;
    }

    /**
     * Valida que los 8 primeros caracteres del DNI sean numeros
     */
    private boolean justNumbers() {
        boolean valid=true;
        int i, j;
        String number;
        StringBuilder myDni = new StringBuilder();
        String[] oneToNine = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (i = 0; i < this.dni.length() - 1; i++) {
            number = this.dni.substring(i, i + 1);
            for (j = 0; j < oneToNine.length; j++) {
                if (number.equals(oneToNine[j])) { myDni.append(oneToNine[j]);}
            }
        }
        if (myDni.length() != 8) {
            valid= false;
        }
        return valid;
    }

    /**
     * Comprueba que la letra del dni sea una valida y se corresponda con la cadena de numeros del dni
     */
    private String dniCharacter() {
        int myDni = Integer.parseInt(this.dni.substring(0, 8));
        String character;
        String[] validChars = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int resto = myDni % 23;
        character = validChars[resto];
        return character;
    }
}
