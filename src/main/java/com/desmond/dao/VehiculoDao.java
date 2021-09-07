package main.java.com.desmond.dao;

import main.java.com.desmond.entity.Usuario;
import main.java.com.desmond.entity.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utiliza la entidad Vehiculo para la ejecucion de consultas sobre la base de datos de la tabla correspondiente
 * a dicha entidad.
 */

public class VehiculoDao {

    Connection conn;

    public VehiculoDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Consulta en la base de datos todos los Vehiculos y los almacena en una lista
     * @return Lista de todos los Vehiculos
     * @throws SQLException
     */

    public List<Vehiculo> getAllVehicles() throws SQLException {
        ArrayList<Vehiculo> todos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            String placa = result.getString(1);
            String marca = result.getString(2);
            String modelo = result.getString(3);
            int capacidad = result.getInt(4);
            int anio = result.getInt(5);
            String color = result.getString(6);
            int kilometros = result.getInt(7);
            todos.add(new Vehiculo(placa, marca, modelo, capacidad, anio, color, kilometros));
        }
        return todos;
    }

    /**
     * Consulta los Vehiculos dentro de la base de datos por su placa, retornando toda su informacion
     * @param plaque placa del vehiculo
     * @return Vehiculo vehiculo insertado en base de datos
     * @throws SQLException
     */

    public Vehiculo getByPlaque(String plaque) throws SQLException {
        String sql = "SELECT * FROM vehiculo WHERE veh_placa = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, plaque);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String placa = result.getString(1);
            String marca = result.getString(2);
            String modelo = result.getString(3);
            int capacidad = result.getInt(4);
            int anio = result.getInt(5);
            String color = result.getString(6);
            int kilometros = result.getInt(7);
            return new Vehiculo(placa, marca, modelo, capacidad, anio, color, kilometros);
        } else {
            return null;
        }
    }

    /**
     * Crea un vehiculo en la base de datos.
     * @param placa placa del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param anio año del vehiculo
     * @param capacidad capacidad del vehiculo
     * @param color color del vehiculo
     * @param kilometros kilometraje del vehiculo
     * @return Vehiculo vehiculo insertado en base de datos
     * @throws SQLException
     */

    public Vehiculo createVehicle(String placa, String marca, String modelo, int anio, int capacidad, String color, int kilometros) throws SQLException {
        String sql = "INSERT INTO vehiculo(veh_placa, veh_marca, veh_modelo, veh_anio, veh_capacidad, veh_color, veh_kilometros) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, placa);
        statement.setString(2, marca);
        statement.setString(3, modelo);
        statement.setInt(4, anio);
        statement.setInt(5, capacidad);
        statement.setString(6, color);
        statement.setInt(7, kilometros);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            return new Vehiculo(placa, marca, modelo, anio, capacidad, color, kilometros);
        } else  {
            return null;
        }
    }

    /**
     * Funcion encargada de actualizar los datos de un "vehiculo" existente en la base de datos.
     * @param placa placa del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param anio año del vehiculo
     * @param capacidad capacidad del vehiculo
     * @param color color del vehiculo
     * @param kilometros kilometraje del vehiculo
     * @throws SQLException
     */

    public void updateVehicle(String placa, String marca, String modelo, int anio, int capacidad, String color, int kilometros) throws SQLException {
        String sql = "UPDATE vehiculo SET veh_marca = ?, veh_modelo = ?, veh_anio = ?, veh_capacidad = ?, veh_color = ?, veh_kilometros = ? WHERE veh_placa = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, marca);
        statement.setString(2, modelo);
        statement.setInt(3, anio);
        statement.setInt(4, capacidad);
        statement.setString(5, color);
        statement.setInt(6, kilometros);
        statement.setString(7, placa);
        statement.executeUpdate();
    }
}
