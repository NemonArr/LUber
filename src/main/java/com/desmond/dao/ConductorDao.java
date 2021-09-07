package main.java.com.desmond.dao;

import main.java.com.desmond.entity.Conductor;
import main.java.com.desmond.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utiliza la entidad Conductor para la ejecucion de consultas sobre la base de datos de la tabla correspondiente
 * a dicha entidad
 */

public class ConductorDao {

    private Connection conn;

    public ConductorDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Consulta en la base de datos todos los Conductores y los almacena en una lista
     * @return Lista de todos los Conductores
     * @throws SQLException
     */

    public List<Conductor> getAllDrivers() throws SQLException {
        ArrayList<Conductor> todos = new ArrayList<>();
        String sql = "SELECT * FROM conductor";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            int cc = result.getInt(1);
            String placa = result.getString(2);
            todos.add(new Conductor(cc, placa));
        }
        return todos;
    }

    /**
     * Consulta los Conductores dentro de la base de datos por su cedula, retornando toda su informacion
     * @param cc cedula del conductor
     * @return Conductor insertado en base de datos
     * @throws SQLException
     */

    public Conductor getDByCedula(int cc) throws SQLException {
        String sql = "SELECT * FROM conductor WHERE usu_cc = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cc);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int cedula = result.getInt(1);
            String placa = result.getString(2);
            return new Conductor(cedula, placa);
        } else {
            return null;
        }
    }

    /**
     * Consulta los Conductores dentro de la base de datos por su cedula, retornando toda su informacion
     * @param usuario informacion del usuario
     * @return Conductor insertado en base de datos
     * @throws SQLException
     */

    public Conductor getDByCedula(Conductor usuario) throws SQLException {
        return getDByCedula(usuario.getCedula());
    }

    /**
     * Crea un conductor en la base de datos.
     * @param cedula cedula del conductor
     * @param placa placa del vehiculo
     * @return Conductor insertado en base de datos
     * @throws SQLException
     */

    public Conductor createDriver(int cedula, String placa) throws SQLException {
        String sql = "INSERT INTO conductor(usu_cc, veh_placa) VALUES(?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cedula);
        statement.setString(2, placa);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            return new Conductor(cedula, placa);
        } else {
            return null;
        }
    }

    /**
     * Crea un conductor en la base de datos.
     * @param usuario informacion del Usuario
     * @param placa placa del vehiculo
     * @return Conductor insertado en base de datos
     * @throws SQLException
     */

    public Conductor createDriver(Conductor usuario, String placa) throws SQLException {
        return new Conductor(usuario.getCedula(), placa);
    }

    /**
     * Actualiza los datos de un Conductor existente en la base de datos.
     * @param cedula cedula del conducto
     * @param placa placa del vehiculo
     * @throws SQLException
     */

    public void updateDriver(int cedula, String placa) throws SQLException {
        String sql = "UPDATE conductor SET veh_placa = ? WHERE usu_cc = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, placa );
        statement.setInt(2, cedula);
        statement.executeUpdate();//hay uno mejor.
    }

    /**
     * Sobrecarga, Actualiza los datos de un Conductor existente en la base de datos.
     * @param old informacion del conductor
     * @param placa placa del vehiculo
     * @throws SQLException
     */

    public void updateDriver(Conductor old, String placa) throws SQLException {
        String sql = "UPDATE conductor SET placa WHERE usu_cc = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, placa);
        statement.setInt(2, old.getCedula());
        statement.executeUpdate();//hay uno mejor.
    }

    /**
     * Elimina los datos de un Conductor
     * @param conductor informacion de un conductor
     * @throws SQLException
     */

    public void deleteDriver(Conductor conductor) throws SQLException {
        String sql = " DELETE FROM conductor WHERE con_cc = ?";
        PreparedStatement statement = conn.prepareStatement (sql );
        statement.setInt(1, conductor.getCedula());
        statement.executeUpdate();
    }

    /**
     * Sobrecarga,elimina los datos de un Conductor
     * @param cedula cedula del conductor
     * @throws SQLException
     */

    public void deleteDriver(int cedula) throws SQLException {
        String sql = "DELETE FROM conductor WHERE usu_cc = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, cedula);
        statement.executeUpdate();
    }
}
