package main.java.com.desmond.dao;

import main.java.com.desmond.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utiliza la entidad Usuario para la ejecucion de consultas sobre la base de datos de la tabla correspondiente a dicha
 * entidad
 */

public class UsuarioDao {

    private Connection conn;

    public UsuarioDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Consulta en la base de datos todos los Usuarios y los almacena en una lista
     * @return Lista de todos los Usuarios
     * @throws SQLException
     */

    public List<Usuario> getAllUsers() throws SQLException {
        ArrayList<Usuario> todos = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            int cc = result.getInt(1);
            String nombre = result.getString(2);
            String apellido = result.getString(3);
            String mail = result.getString(4);
            todos.add(new Usuario(cc, nombre, apellido, mail));
        }
        return todos;
    }

    /**
     * Consulta los Usuarios dentro de la base de datos por su cedula, retornando toda su informacion
     * @param cc cedula del usuario
     * @return Usuario
     * @throws SQLException
     */

    public Usuario getByCedula(int cc) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE usu_cc = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cc);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int cedula = result.getInt(1);
            String nombre = result.getString(2);
            String apellido = result.getString(3);
            String email = result.getString(4);
            return new Usuario(cedula, nombre, apellido, email);
        } else {
            return null;
        }
    }

    /**
     * Crea un usuario en la base de datos.
     * @param cedula cedula del Usuario
     * @param nombre nombre del Usuario
     * @param apellido apellido del Usuario
     * @param email email del Usuario
     * @return Usuario insertado en base de datos
     * @throws SQLException
     */

    public Usuario createUser(int cedula, String nombre, String apellido, String email) throws SQLException {
        String sql = "INSERT INTO usuario(usu_cc, usu_nombre , usu_apellido, usu_email) VALUES(?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cedula);
        statement.setString(2, nombre);
        statement.setString(3, apellido);
        statement.setString(4, email);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            return new Usuario(cedula, nombre, apellido, email);
        } else {
            return null;
        }
    }

    /**
     * Actualiza los datos de un Usuario existente
     * @param old Usuario
     * @throws SQLException
     */
    public void updateUser(Usuario old) throws SQLException {
        String sql = "UPDATE usuario SET usu_nombre = ?, usu_apellido = ?, usu_email = ? WHERE usu_cc = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, old.getNombre());
        statement.setString(2, old.getApellido());
        statement.setString(3, old.getCorreo());
        statement.setInt(4, old.getCedula());
        statement.executeUpdate();
    }

}
