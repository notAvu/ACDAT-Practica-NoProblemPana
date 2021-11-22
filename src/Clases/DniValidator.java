package Clases;

public class DniValidator {
    private String dni;
    private final int DNI_LENGTH=9;

    /**
     * Constructor de la clase del validador de DNI, en el caso de que el string sea menor
     * @param inputDni
     */
    public DniValidator(String inputDni) {
//        if(inputDni.length()<DNI_LENGTH){
//            for(int i=0; i<(DNI_LENGTH*2-inputDni.length()); i++){
//                inputDni+="U";
//            }
//        }
        this.dni = inputDni;
    }
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
    private boolean soloNumeros() {
        boolean valido=true;
        int i, j = 0;
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
    private String letraDNI() {
        int miDNI = Integer.parseInt(this.dni.substring(0, 8));
        int resto = 0;
        String miLetra = "";
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        resto = miDNI % 23;
        miLetra = asignacionLetra[resto];
        return miLetra;
    }
}
