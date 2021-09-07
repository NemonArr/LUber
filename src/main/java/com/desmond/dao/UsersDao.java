package main.java.com.desmond.dao;

import main.java.com.desmond.entity.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;

/**
 * Utiliza la entidad Users para la ejecucion de consultas sobre la base de datos de la tabla correspondiente a dicha
 * entidad para ser usados en la autentificacion de usuario
 */

public class UsersDao {

    private Connection conn;

    public UsersDao(Connection conn) {
        this.conn = conn;
    }

    public boolean login(String user, String password) throws SQLException, NoSuchAlgorithmException {
        String sql = "SELECT * FROM users";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String us = result.getString(1);
            String pass = result.getString(2);
            byte[] sha256Bytes = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
            String sha256 = Base64.getEncoder().encodeToString(sha256Bytes);
            if (us.equals(user) && pass.equals(sha256)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
