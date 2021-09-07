package main.java.com.desmond.entity;

/**
 * Clase que modela la entidad Conductor de la base de datos
 */

public class Conductor extends Usuario {

    private String placa;

    public Conductor(int cedula, String nombre, String apellido, String correo, String placa) {
        super(cedula, nombre, apellido, correo);
        this.placa = placa;
    }

    public Conductor(String placa) {
        this.placa = placa;
    }

    public Conductor(int cedula, String placa) {
        this.cedula = cedula;
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}