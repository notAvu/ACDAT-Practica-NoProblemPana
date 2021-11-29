package Clases;

import java.util.Objects;

public class Persona {

    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String numTelefono;
    private final int NAME_SIZE = 26;

    public Persona(String nombre, String apellidos, String dni, String direccion, String numTelefono) {
        setNombre(nombre);
        setApellidos(apellidos);
        this.dni = dni;
        this.direccion = direccion;
        this.numTelefono = numTelefono;
    }
    public String getNombre() {
        return nombre;
    }
    /**
     * Asigna al atrubuto nombre el parametro recibido, pero si es de longitud mayor a el numero de caracteres
     * especificado en la variable NAME_SIZE se recorta para que solo ocupe ese tamaño
     * @param nombre
     */
    public void setNombre(String nombre) {
        if(nombre.length()<= NAME_SIZE) this.nombre = nombre;
        else this.nombre=nombre.substring(0, NAME_SIZE);
    }
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Asigna al atrubuto apellidos el parametro recibido, pero si es de longitud mayor a el numero de caracteres
     * especificado en la variable NAME_SIZE se recorta para que solo ocupe ese tamaño
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        if(apellidos.length()<= NAME_SIZE) this.apellidos = apellidos;
        else this.apellidos=apellidos.substring(0, NAME_SIZE);
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
