package Clases;

import java.util.Objects;

public class Persona {
    private static final int LONGITUD_TELEFONO=9;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String numTelefono;

    public Persona(String nombre, String apellidos, String dni, String direccion, String numTelefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.numTelefono = numTelefono;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNumTelefono() {
        return numTelefono;
    }
    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    /**
     * Metodo para validar que el numero de telefono tenga una longitud dada
     * @return
     */
    public boolean validarTlfn()
    {
        return numTelefono.length()==LONGITUD_TELEFONO;
    }

    /**
     * metodo para comprobar la validez del dni en funcion de los criterios que determina la cclase DniValidator
     * @return
     */
    public boolean validar(){ return new DniValidator(dni).validar();}

    @Override
    public String toString() {
        return nombre+","+ apellidos+","+ dni+","+direccion+","+numTelefono;
    }

    /**
     * Determina si el objeto persona es igual o no al que se le pasa por parametro
     * El criterio de igualdad es el dni del objeto persona
     * @param o (Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }
}
