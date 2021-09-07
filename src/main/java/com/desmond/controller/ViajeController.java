package main.java.com.desmond.controller;

import main.java.com.desmond.dao.ConductorDao;
import main.java.com.desmond.dao.UsuarioDao;
import main.java.com.desmond.dao.ViajeDao;
import main.java.com.desmond.entity.Conductor;
import main.java.com.desmond.entity.Usuario;
import main.java.com.desmond.entity.Vehiculo;
import main.java.com.desmond.entity.Viaje;
import main.java.com.desmond.utils.ViewUtils;
import main.java.com.desmond.view.ViajeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Controla la interfaz grafica ViajeView usando las clases UsuarioDao, ConductorDao y ViajeDao
 */

public class ViajeController {

    private UsuarioDao usuarioDao;
    private ConductorDao conductorDao;
    private ViajeView viajeView;
    private ViajeDao viajeDao;

    public ViajeController(UsuarioDao usuarioDao, ConductorDao conductorDao, ViajeView viajeView, ViajeDao viajeDao) {
        this.usuarioDao = usuarioDao;
        this.conductorDao = conductorDao;
        this.viajeView = viajeView;
        this.viajeDao = viajeDao;

        viajeView.getButton1().addActionListener(newTripListener());
        viajeView.getButton2().addActionListener(showAllTripsByChosenActionListener());
        viajeView.getButton3().addActionListener(showAllTripsActionListener());
    }

    /**
     * Crea un Action Listener que muestra todos los Viajes en una ventana emergente
     * @return Action Listener
     */

    private ActionListener showAllTripsActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    List<Viaje> all = viajeDao.getAllTrips();
                    String oneByOne = "";
                    for(Viaje i: all) {
                        int conductorCc = i.getConductorCC();
                        int usuarioCc = i.getUsuarioCC();
                        Usuario conductor = usuarioDao.getByCedula(conductorCc);
                        Usuario usuario = usuarioDao.getByCedula(usuarioCc);
                        String nombreConductor = conductor.getNombre() + " " + conductor.getApellido();
                        String nombreUsuario = usuario.getNombre() + " " + usuario.getApellido();
                        Conductor placaCon = conductorDao.getDByCedula(conductorCc);
                        String placa = placaCon.getPlaca();
                        String one = "Viaje: "+ i.getId() + " - " + i.getDate() + "Vehiculo : " + placa + ". Conductor: "
                                + nombreConductor + " - "+ conductorCc + ". Usuario " + nombreUsuario +
                                " - " + usuarioCc + ".\n";
                        oneByOne = oneByOne + one;
                    }
                    JOptionPane.showMessageDialog(viajeView,
                            oneByOne,
                            "VIAJES",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    ViewUtils.showApplicationErrorMessage(viajeView);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que extrae de un campo text en la interfaz grafica y retorna
     * la informacion de los viajes correspondientes a la informacion brindada
     * @return Action Listener
     */

    private ActionListener showAllTripsByChosenActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int con;
                    int usu;
                    try {
                        con = Integer.parseInt(viajeView.getTextField2().getText());
                    } catch (NumberFormatException e) {
                        con = 0;
                    }
                    try {
                        usu = Integer.parseInt(viajeView.getTextField3().getText());
                    } catch (NumberFormatException e) {
                        usu = 0;
                    }
                    List<Viaje> all = viajeDao.getTrip(con, usu);
                    String oneByOne = "";
                    for(Viaje i: all) {
                        int conductorCc = i.getConductorCC();
                        int usuarioCc = i.getUsuarioCC();
                        Usuario conductor = usuarioDao.getByCedula(conductorCc);
                        Usuario usuario = usuarioDao.getByCedula(usuarioCc);
                        String nombreConductor = conductor.getNombre() + " " + conductor.getApellido();
                        String nombreUsuario = usuario.getNombre() + " " + usuario.getApellido();
                        Conductor placaCon = conductorDao.getDByCedula(conductorCc);
                        String placa = placaCon.getPlaca();
                        String one = "Viaje: "+ i.getId() + " - " + i.getDate() + "Vehiculo : " + placa + ". Conductor: "
                                + nombreConductor + " - "+ conductorCc + ". Usuario " + nombreUsuario +
                                " - " + usuarioCc + ".\n";
                        oneByOne = oneByOne + one;
                    }
                    JOptionPane.showMessageDialog(viajeView,
                            oneByOne,
                            "VEHICULO",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    ViewUtils.showApplicationErrorMessage(viajeView);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que extrae de diferentes campos de texto de la intefaz para crear un nuevo Viaje
     * @return Action Listener
     */

    private ActionListener newTripListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int conductorCC = Integer.parseInt(viajeView.getTextField2().getText());
                    int usuarioCC = Integer.parseInt(viajeView.getTextField3().getText());
                    viajeDao.createTrip(conductorCC, usuarioCC);
                    JOptionPane.showMessageDialog(viajeView,
                            "Viaje Creado",
                            "Exito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch(SQLException e) {
                    ViewUtils.showApplicationErrorMessage(viajeView);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que cambia la interfaz ViajeView para adaptarla a la creacion de un nuevo Viaje
     * @return Action Listener
     */

    private ActionListener buttonNewTripActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viajeView.getTextField2().setText("");
                viajeView.getTextField3().setText("");

            }
        };
    }
}


