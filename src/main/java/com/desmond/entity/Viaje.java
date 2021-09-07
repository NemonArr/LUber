package main.java.com.desmond.entity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Clase que modela la entidad Viaje de la base de datos
 */

public class Viaje {

    private int id;
    private int conductorCC;
    private int usuarioCC;
    private LocalDateTime date;

    public Viaje(int id, int conductorCC, int usuarioCC, LocalDateTime date) {
        this.id = id;
        this.conductorCC = conductorCC;
        this.usuarioCC = usuarioCC;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getConductorCC() {
        return conductorCC;
    }

    public void setConductorCC(int conductorCC) {
        this.conductorCC = conductorCC;
    }

    public int getUsuarioCC() {
        return usuarioCC;
    }

    public void setUsuarioCC(int usuarioCC) {
        this.usuarioCC = usuarioCC;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
