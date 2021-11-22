package Clases;

import java.util.Objects;

public class Persona {
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
    public boolean validarTlfn()
    {
        return numTelefono.length()==9;
    }
    public boolean validar(){ return new DniValidator(dni).validar();}
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
    @Override
    public String toString() {
        return nombre+","+ apellidos+","+ dni+","+direccion+","+numTelefono;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }
}
