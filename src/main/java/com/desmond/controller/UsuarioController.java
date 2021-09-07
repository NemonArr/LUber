package main.java.com.desmond.controller;

import main.java.com.desmond.dao.ConductorDao;
import main.java.com.desmond.dao.UsuarioDao;
import main.java.com.desmond.dao.VehiculoDao;
import main.java.com.desmond.entity.Conductor;
import main.java.com.desmond.entity.Usuario;
import main.java.com.desmond.entity.Vehiculo;
import main.java.com.desmond.utils.ViewUtils;
import main.java.com.desmond.view.UsuarioView;
import main.java.com.desmond.view.Window;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Controla la interfaz grafica UsuarioView usando las clases "UsuarioDao" y "ConductorDao"
 */

public class UsuarioController {

    private UsuarioView view;
    private UsuarioDao usuarioDao;
    private ConductorDao conductorDao;
    private VehiculoDao vehiculoDao;

    public UsuarioController(UsuarioView view, UsuarioDao usuarioDao, ConductorDao conductorDao, VehiculoDao vehiculoDao) {
        this.view = view;
        this.usuarioDao = usuarioDao;
        this.conductorDao = conductorDao;
        this.vehiculoDao = vehiculoDao;

        try {
            view.getComboBox1().addItem("N/A");
            List<Vehiculo> vehicles =  vehiculoDao.getAllVehicles();
            for(Vehiculo v : vehicles) {
                view.getComboBox1().addItem(v.getPlaca());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ViewUtils.showApplicationErrorMessage(view);
        }

        view.getButton1().addActionListener(searchUserActionListener());
        view.getButton1().addActionListener(buttonToModifyActionListener());
        view.getButton2().addActionListener(buttonNewActionListener());
        view.getButton3().addActionListener(buttonToStartActionListener());
        view.getButton5().addActionListener(updateUserActionListener());
        view.getButton6().addActionListener(newUserActionListener());
        view.getButton7().addActionListener(showAllUsersActionListener());
        view.getButton8().addActionListener(showAllDriversActionListener());
    }

    /**
     * Crea un Action Listener que muestra todos los Usuarios en una ventana emergente
     * @return Action Listener
     */
    private ActionListener showAllUsersActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    List<Usuario> all = usuarioDao.getAllUsers();
                    String oneByOne = "";
                    for(Usuario i: all) {
                        String one = "Usuario: "+ i.getNombre()+ " " + i.getApellido() +
                                "; documento: " + i.getCedula() + "; correo: "+ i.getCorreo()+ ".\n";
                        oneByOne = oneByOne + one;
                    }
                    JOptionPane.showMessageDialog(view,
                            oneByOne,
                            "Usuarios",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    ViewUtils.showApplicationErrorMessage(view);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que muestra todos los conductores en una ventana emergente
     * @return Action Listener
     */

    private ActionListener showAllDriversActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    List<Conductor> all = conductorDao.getAllDrivers();
                    String oneByOne = "";
                    for(Conductor i: all) {
                        String one = "Documento: " + i.getCedula() + "; Placa del vehiculo: "+ i.getPlaca() + ".\n";
                        oneByOne = oneByOne + one;
                    }
                    JOptionPane.showMessageDialog(view,
                            oneByOne,
                            "Conductor",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    ViewUtils.showApplicationErrorMessage(view);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que extrae de un campo text en la interfaz grafica la cedula de un Usuarios y retorna
     * toda la informacion del usuario si este existe.
     * @return Action Listener
     */

    private ActionListener searchUserActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int cedula = Integer.parseInt(view.getTextField1().getText());
                Usuario uno = null;
                try {
                    uno = usuarioDao.getByCedula(cedula);
                    if(uno == null) {
                        JOptionPane.showMessageDialog(view,
                                "Usuario NO encontrado",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        view.getTextField2().setText(uno.getNombre());
                        view.getTextField3().setText(uno.getApellido());
                        view.getTextField4().setText(uno.getCorreo());
                        if(conductorDao.getDByCedula(cedula) != null) {
                            Conductor un = conductorDao.getDByCedula(cedula);
                            view.getCheckBox1().setSelected(true);
                            view.getComboBox1().setSelectedItem(un.getPlaca());
                        } else {
                            view.getCheckBox1().setSelected(false);
                            view.getComboBox1().setSelectedItem("N/A");
                        }
                    }
                } catch(SQLException e) {
                    ViewUtils.showApplicationErrorMessage(view);

                }
            }
        };
    }

    /**
     * Crea un Action Listener que extrae de diferentes campos de texto de la intefaz para crear un nuevo Usuario
     * @return Action Listener
     */

    private ActionListener newUserActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int cedula = Integer.parseInt(view.getTextField1().getText());
                    String nombre = view.getTextField2().getText();
                    String apellido = view.getTextField3().getText();
                    String email = view.getTextField4().getText();
                    usuarioDao.createUser(cedula,nombre, apellido, email);
                    if(view.getCheckBox1().isSelected()) {
                        String placa = null;
                        if(view.getComboBox1().getSelectedItem() != "N/A") {
                            placa = (String)view.getComboBox1().getSelectedItem();
                        }
                        conductorDao.createDriver(cedula, placa);
                    }
                    JOptionPane.showMessageDialog(view,
                            "Usuario Creado",
                            "Exito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch(SQLException e) {
                    ViewUtils.showApplicationErrorMessage(view);
                }
            }
        };
    }

    /**
     * Crea un Action Listener que actualiza los datos de un Usuario segun esto se modifiquen en la interfaz
     * grafica UsuarioView
     * @return Action Listener
     */

    private ActionListener updateUserActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int cedula = Integer.parseInt(view.getTextField1().getText());
                    String nombre = view.getTextField2().getText();
                    String apellido = view.getTextField3().getText();
                    String email = view.getTextField4().getText();
                    Usuario changed = new Usuario(cedula, nombre, apellido, email);
                    usuarioDao.updateUser(changed);
                    boolean isDriver = conductorDao.getDByCedula(cedula) != null;
                    boolean isChecked = view.getCheckBox1().isSelected();
                    if(isChecked && isDriver) {
                        String placa = null;
                        if(view.getComboBox1().getSelectedItem() != "N/A") {
                            placa = (String) view.getComboBox1().getSelectedItem();
                        }
                        conductorDao.updateDriver(cedula, placa);
                    } else if(isChecked && !isDriver) {
                        String placa = null;
                        if(view.getComboBox1().getSelectedItem() != "N/A") {
                            placa = (String) view.getComboBox1().getSelectedItem();
                        }
                        conductorDao.createDriver(cedula, placa);
                    } else if(!isChecked && isDriver) {
                        conductorDao.deleteDriver(cedula);
                    }
                    ViewUtils.showApplicationChangeSusses(view);
                } catch (SQLException e) {
                    ViewUtils.showApplicationErrorMessage(view);
                    System.out.println(e.getMessage());
                }
            }
        };
    }

    /**
     * Crea un Action Listener que cambia la interfaz UsuarioView para adaptarla a la creacion de un nuevo
     * Usuario/Conducto
     * @return Action Listener
     */

    private ActionListener buttonNewActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTextField2().setText("");
                view.getTextField3().setText("");
                view.getTextField4().setText("");
                view.getTextField2().setEditable(true);
                view.getTextField3().setEditable(true);
                view.getTextField4().setEditable(true);
                view.getCheckBox1().setEnabled(true);
                view.getComboBox1().setEnabled(true);

                view.getButton1().setVisible(false);
                view.getButton2().setVisible(false);
                view.getButton3().setVisible(true);
                view.getButton5().setVisible(false);
                view.getButton6().setVisible(true);
                view.getButton7().setVisible(false);
                view.getButton8().setVisible(false);
            }
        };
    }

    /**
     *  Crea un Action Listener que cambia la interfaz UsuarioView para adaptarla a la modificacion de un
     *  Usuario/Conducto
     * @return Action Listener
     */

    private ActionListener buttonToModifyActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTextField2().setEditable(true);
                view.getTextField3().setEditable(true);
                view.getTextField4().setEditable(true);
                view.getCheckBox1().setEnabled(true);
                view.getComboBox1().setEnabled(true);
                view.getButton3().setVisible(true);

                view.getButton1().setVisible(false);
                view.getButton2().setVisible(false);
                view.getButton3().setVisible(true);
                view.getButton5().setVisible(true);
                view.getButton6().setVisible(false);
                view.getButton7().setVisible(false);
                view.getButton8().setVisible(false);
            }
        };
    }

    /**
     * Crea un Action Listener que regresa la interfaz a su estado inicial.
     * @return Action Listener
     */

    private ActionListener buttonToStartActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTextField2().setEditable(false);
                view.getTextField3().setEditable(false);
                view.getTextField4().setEditable(false);
                view.getCheckBox1().setEnabled(false);
                view.getComboBox1().setEnabled(false);

                view.getButton1().setVisible(true);
                view.getButton2().setVisible(true);
                view.getButton3().setVisible(false);
                view.getButton5().setVisible(false);
                view.getButton6().setVisible(false);
                view.getButton7().setVisible(true);
                view.getButton8().setVisible(true);
            }
        };
    }

}
