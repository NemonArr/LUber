package main.java.com.desmond.dao;

import main.java.com.desmond.entity.Viaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Utiliza la entidad Viaje para la ejecucion de consultas sobre la base de datos de la tabla correspondiente a dicha
 * entidad
 */

public class ViajeDao {

    Connection conn;

    public ViajeDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Consulta en la base de datos todos los Viaje y los almacena en una lista
     * @return Lista de todos los Viajes
     * @throws SQLException
     */

    public List<Viaje> getAllTrips() throws SQLException {
        ArrayList<Viaje> todos = new ArrayList<>();
        String sql = "SELECT * FROM viaje";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            int conductor = result.getInt(3);
            int usuario = result.getInt(2);
            LocalDateTime fecha = result.getTimestamp(4).toLocalDateTime();
            todos.add(new Viaje(id, conductor, usuario, fecha));
        }
        return todos;
    }

    /**
     * Consulta los Viajes dentro de la base de datos por la cedula del pasajero y/o conductor, retornando toda la
     * informacion del viaje
     * @param conductorCC cedula del conductor
     * @param usuarioCC cedula ded usuario
     * @return Lista de Viajes
     * @throws SQLException
     */
    public List<Viaje> getTrip(int conductorCC, int usuarioCC) throws SQLException {
        if (conductorCC <= 0 && usuarioCC <=0) {
            return null;
        }
        ArrayList<Viaje> todos = new ArrayList<>();
        String sql = "SELECT * from viaje WHERE ";
        ArrayList<String> clauses = new ArrayList<>();
        if (conductorCC > 0 ) {
            clauses.add("con_cc = " + conductorCC);
        }
        if (usuarioCC > 0) {
            clauses.add("usu_cc = " + usuarioCC);
        }
        sql += String.join(" AND ", clauses);
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            int conductor = result.getInt(3);
            int usuario = result.getInt(2);
            LocalDateTime fecha = result.getTimestamp(4).toLocalDateTime();
            todos.add(new Viaje(id, conductor, usuario, fecha));
        }
        return todos;
    }

    /**
     * Crea un viaje en la base de datos.
     * @param conductorCC cedula del conductor
     * @param usuarioCC cedula del usuario
     * @return Viaje
     * @throws SQLException
     */

    public Viaje createTrip(int conductorCC, int usuarioCC) throws SQLException {
        String sql = "INSERT INTO viaje(con_cc, usu_cc, fecha) VALUES(?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        LocalDateTime fecha = LocalDateTime.now();
        statement.setInt(1, conductorCC);
        statement.setInt(2, usuarioCC);
        statement.setObject(3, fecha );
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            sql = "SELECT MAX(via_id) FROM viaje;";
            statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            int id = 0;
            if (result.next()) {
                id = result.getInt(1);
            }
            return new Viaje(id, conductorCC, usuarioCC, fecha);
        } else {
            return null;
        }
    }

}
