package Clases;

public class TlfnValidator {
    private static final int NUMBER_SIZE=9;
    private String telephoneNumber;

    public TlfnValidator(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Determina que el string introducido sea de longitud menor a 9
     * @return
     */
    public boolean validate()
    {
        return this.telephoneNumber.length()==9;
    }
}
