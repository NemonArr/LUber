package main.java.com.desmond.entity;

/**
 * Clase que modela la entidad Usuario de la base de datos
 */
public class Usuario {

    protected int cedula;
    protected String nombre;
    protected String apellido;
    protected String correo;

    public Usuario(int cedula, String nombre, String apellido, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public Usuario() {
    }

    public int getCedula() {
        return cedula;
    }

    public void setCc(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
