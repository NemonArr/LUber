package main.java.com.desmond;

import main.java.com.desmond.controller.LoginController;
import main.java.com.desmond.controller.UsuarioController;
import main.java.com.desmond.controller.VehiculoController;
import main.java.com.desmond.controller.ViajeController;
import main.java.com.desmond.dao.*;
import main.java.com.desmond.view.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/LUber";
        String username = "java";
        String password = "1234";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn == null) {
                System.out.println("No Conectado");
                return;
            }
        } catch (SQLException ex) {
            System.out.println("No Conectado: " + ex.getMessage());
            return;
        }

        // Se crean DAOs
        UsersDao useDao = new UsersDao(conn);
        UsuarioDao usuDao = new UsuarioDao(conn);
        ConductorDao conDao = new ConductorDao(conn);
        VehiculoDao vehiDao = new VehiculoDao(conn);
        ViajeDao viajeDao = new ViajeDao(conn);

        // Se crean vistas
        LoginView loginView = new LoginView();
        UsuarioView usuarioView = new UsuarioView();
        VehiculoView vehiculoView = new VehiculoView();
        ViajeView viajeView = new ViajeView();
        Window window = new Window(loginView, usuarioView, vehiculoView, viajeView);

        // Se crean los controladores
        LoginController loginController = new LoginController(loginView, window, useDao);
        UsuarioController usuarioController = new UsuarioController(usuarioView, usuDao, conDao, vehiDao);
        VehiculoController vehiculoController = new VehiculoController(vehiculoView, vehiDao);
        ViajeController viajeController = new ViajeController(usuDao, conDao, viajeView, viajeDao);

        // Inciamos
        window.setVisible(true);
    }

}
