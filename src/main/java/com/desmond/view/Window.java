package main.java.com.desmond.view;

import javax.swing.*;

/**
 *  Crea la interfaz grafica que contiene las interfaces UsuarioView, VehiculoView y ViajeView
 *  en forma de pestañas para su visualizacion.
 */

public class Window extends JFrame {

    private JTabbedPane tabs;
    private LoginView loginView;
    private UsuarioView usuarioView;
    private VehiculoView vehiculoView;
    private ViajeView viajeView;

    public Window(LoginView loginView, UsuarioView usuarioView, VehiculoView vehiculoView, ViajeView viajeView) {
        this.loginView = loginView;
        this.usuarioView = usuarioView;
        this.vehiculoView = vehiculoView;
        this.viajeView = viajeView;
        tabs = new JTabbedPane();
        tabs.add("Usuarios", usuarioView);
        tabs.add("Vehículos", vehiculoView);
        tabs.add("Viajes", viajeView);
        setSize(700, 400);
        add(loginView);
    }

    public void showTabs() {
        this.getContentPane().removeAll();
        add(tabs);
        this.revalidate();
    }

}
