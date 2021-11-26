package Clases;

public class DniValidator {
    private String dni;
    private final int DNI_LENGTH=9;

    public DniValidator(String inputDni) {
        this.dni = inputDni;
    }

    /**
     * valida que el DNI cumpla las características de un DNI real
     */
    public boolean validar() {
        String letraMayuscula;
        boolean valido=false;
        if (dni.length() == DNI_LENGTH && Character.isLetter(this.dni.charAt(8))) {
            letraMayuscula = (this.dni.substring(8)).toUpperCase();
            if (soloNumeros() && letraDNI().equals(letraMayuscula)) {
                valido = true;
            }
        }
        return valido;
    }

    /**
     * Valida que los 8 primeros caracteres del DNI sean numeros
     */
    private boolean soloNumeros() {
        boolean valido=true;
        int i, j;
        String numero;
        String miDNI = "";
        String[] unoNueve = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (i = 0; i < this.dni.length() - 1; i++) {
            numero = this.dni.substring(i, i + 1);
            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) { miDNI += unoNueve[j];}
            }
        }
        if (miDNI.length() != 8) {
            valido= false;
        }
        return valido;
    }

    /**
     * Comprueba que la letra del dni sea una valida y se corresponda con la cadena de numeros del dni
     */
    private String letraDNI() {
        int miDNI = Integer.parseInt(this.dni.substring(0, 8));
        String miLetra;
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int resto = miDNI % 23;
        miLetra = asignacionLetra[resto];
        return miLetra;
    }
}
