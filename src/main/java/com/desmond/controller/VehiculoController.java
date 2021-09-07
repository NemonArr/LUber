package main.java.com.desmond.controller;

import main.java.com.desmond.dao.VehiculoDao;
import main.java.com.desmond.entity.Conductor;
import main.java.com.desmond.entity.Usuario;
import main.java.com.desmond.entity.Vehiculo;
import main.java.com.desmond.utils.ViewUtils;
import main.java.com.desmond.view.UsuarioView;
import main.java.com.desmond.view.VehiculoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Controla la interfaz grafica VehiculoView usando la clase VehiculoDao
 */

public class VehiculoController {

    private VehiculoView vehiculoView;
    private VehiculoDao vehiculoDao;

    public VehiculoController(VehiculoView vehiculoView, VehiculoDao vehiculoDao) throws SQLException {
        this.vehiculoView = vehiculoView;
        this.vehiculoDao = vehiculoDao;

        vehiculoView.getButton1().addActionListener(searchVehicleActionListener());
        vehiculoView.getButton1().addActionListener(buttonModifyActionListener());
        vehiculoView.getButton2().addActionListener(updateVehicleActionListener());
        vehiculoView.getButton3().addActionListener(newVehicleActionListener());
        vehiculoView.getButton4().addActionListener(showAllCarsActionListener());
        vehiculoView.getButton5().addActionListener(buttonNewActionListener());
        vehiculoView.getButton6().addActionListener(buttonCancelActionListener());
    }

    /**
     * Crea un Action Listener que muestra todos los Vehiculos en una ventana emergente
     * @return Action Listener
     */

    private ActionListener showAllCarsActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    List<Vehiculo> all = vehiculoDao.getAllVehicles();
                    String oneByOne = "";
                    for(Vehiculo i: all) {
                        String one = "Vehiculo con placas: "+ i.getPlaca() + "; Marca: " + i.getMarca() + "; Modelo: "
                                + i.getModelo() + "; Año: "+ i.getAnio()+ "; Capacidad: " + i.getCapacidad() +
                                "; Color: " + i.getColor() + "; Kilometraje: " + i.getKilometraje() + ".\n";
                        oneByOne = oneByOne + one;
                    }
                    JOptionPane.showMessageDialog(vehiculoView,
                            oneByOne,
                            "VEHICULO",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    ViewUtils.showApplicationErrorMessage(vehiculoView);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que extrae de un campo text en la interfaz grafica la placa de un Vehiculo y retorna
     * toda la informacion del vehiculo si este existe.
     * @return Action Listener
     */

    private ActionListener searchVehicleActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String placa = vehiculoView.getTextField1().getText();
                Vehiculo uno = null;
                try {
                    uno = vehiculoDao.getByPlaque(placa);
                    if(uno == null) {
                        JOptionPane.showMessageDialog(vehiculoView,
                                "Auto NO encontrado",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        vehiculoView.getTextField2().setText(uno.getMarca());
                        vehiculoView.getTextField3().setText(uno.getModelo());
                        vehiculoView.getTextField4().setText(new Integer(uno.getAnio()).toString());
                        vehiculoView.getTextField5().setText(new Integer(uno.getCapacidad()).toString());
                        vehiculoView.getTextField6().setText(uno.getColor());
                        vehiculoView.getTextField7().setText(new Integer(uno.getKilometraje()).toString());
                        vehiculoView.getButton2().setVisible(true);
                    }
                } catch(SQLException e) {
                    ViewUtils.showApplicationErrorMessage(vehiculoView);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que extrae de diferentes campos de texto de la intefaz para crear un nuevo Vehiculo
     * @return Action Listener
     */

    private ActionListener newVehicleActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String placa = vehiculoView.getTextField1().getText();
                    String marca = vehiculoView.getTextField2().getText();
                    String modelo = vehiculoView.getTextField3().getText();
                    int anio = Integer.parseInt(vehiculoView.getTextField4().getText());
                    int capacidad = Integer.parseInt(vehiculoView.getTextField5().getText());
                    String color = vehiculoView.getTextField6().getText();
                    int kilometros = Integer.parseInt(vehiculoView.getTextField7().getText());
                    vehiculoDao.createVehicle(placa, marca, modelo, anio, capacidad, color, kilometros);
                    JOptionPane.showMessageDialog(vehiculoView,
                            "Vehiculo Creado",
                            "Exito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch(SQLException e) {
                    ViewUtils.showApplicationErrorMessage(vehiculoView);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que actualiza los datos de un Vehiculo segun esto se modifiquen en la interfaz
     * grafica VehiculoView
     * @return Action Listener
     */

    private ActionListener updateVehicleActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String placa = vehiculoView.getTextField1().getText();
                    String marca = vehiculoView.getTextField2().getText();
                    String modelo = vehiculoView.getTextField3().getText();
                    int anio = Integer.parseInt(vehiculoView.getTextField4().getText());
                    int capacidad = Integer.parseInt(vehiculoView.getTextField5().getText());
                    String color = vehiculoView.getTextField6().getText();
                    int kilometros = Integer.parseInt(vehiculoView.getTextField7().getText());
                    vehiculoDao.updateVehicle(placa, marca, modelo, anio, capacidad, color, kilometros);
                    ViewUtils.showApplicationChangeSusses(vehiculoView);
                } catch (SQLException e) {
                    ViewUtils.showApplicationErrorMessage(vehiculoView);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que cambia la interfaz VehiculoView para adaptarla a la creacion de un nuevo Vehiculo
     * @return Action Listener
     */

    private ActionListener buttonNewActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehiculoView.getTextField2().setEditable(true);
                vehiculoView.getTextField3().setEditable(true);
                vehiculoView.getTextField4().setEditable(true);
                vehiculoView.getTextField5().setEditable(true);
                vehiculoView.getTextField6().setEditable(true);
                vehiculoView.getTextField7().setEditable(true);
                vehiculoView.getTextField1().setText("");
                vehiculoView.getTextField2().setText("");
                vehiculoView.getTextField3().setText("");
                vehiculoView.getTextField4().setText("");
                vehiculoView.getTextField5().setText("");
                vehiculoView.getTextField6().setText("");
                vehiculoView.getTextField7().setText("");

                vehiculoView.getButton1().setVisible(false);
                vehiculoView.getButton2().setVisible(false);
                vehiculoView.getButton3().setVisible(true);
                vehiculoView.getButton4().setVisible(false);
                vehiculoView.getButton5().setVisible(false);
                vehiculoView.getButton6().setVisible(true);
            }
        };
    }

    /**
     * Crea un Action Listener que regresa la interfaz a su estado inicial.
     * @return Action Listener
     */

    private ActionListener buttonCancelActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehiculoView.getTextField1().setText("");
                vehiculoView.getTextField2().setText("");
                vehiculoView.getTextField3().setText("");
                vehiculoView.getTextField4().setText("");
                vehiculoView.getTextField5().setText("");
                vehiculoView.getTextField6().setText("");
                vehiculoView.getTextField7().setText("");
                vehiculoView.getTextField2().setEditable(false);
                vehiculoView.getTextField3().setEditable(false);
                vehiculoView.getTextField4().setEditable(false);
                vehiculoView.getTextField5().setEditable(false);
                vehiculoView.getTextField6().setEditable(false);
                vehiculoView.getTextField7().setEditable(false);

                vehiculoView.getButton1().setVisible(true);
                vehiculoView.getButton2().setVisible(false);
                vehiculoView.getButton3().setVisible(false);
                vehiculoView.getButton4().setVisible(true);
                vehiculoView.getButton5().setVisible(true);
                vehiculoView.getButton6().setVisible(false);
            }
        };
    }

    /**
     * Crea un Action Listener que cambia la interfaz ViajeView para adaptarla a la modificacion de un Vehiculo
     * @return Action Listener
     */

    private ActionListener buttonModifyActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehiculoView.getTextField2().setEditable(true);
                vehiculoView.getTextField3().setEditable(true);
                vehiculoView.getTextField4().setEditable(true);
                vehiculoView.getTextField5().setEditable(true);
                vehiculoView.getTextField6().setEditable(true);
                vehiculoView.getTextField7().setEditable(true);

                vehiculoView.getButton1().setVisible(false);
                vehiculoView.getButton2().setVisible(true);
                vehiculoView.getButton4().setVisible(true);
                vehiculoView.getButton5().setVisible(false);
                vehiculoView.getButton6().setVisible(true);
            }
        };
    }
}
