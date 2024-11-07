/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gp8_traveltogether.persistencia;

/**
 *
 * @author Administrator
 */

import gp8_traveltogether.entidades.EstadisticaCiudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EstadisticaCiudadData {
    private Connection con;

    public EstadisticaCiudadData() {
        con = Conexion.getConexion();
    }

    public List<EstadisticaCiudad> obtenerEstadisticasPorPaquete(int codigoPaquete) throws SQLException {
    List<EstadisticaCiudad> estadisticas = new ArrayList<>();
    String query = "SELECT ciudad.codCiudad, ciudad.nombre AS nombreCiudad, COUNT(turista.codigoPaquete) AS frecuencia " +
                   "FROM turista " +
                   "JOIN ciudad ON turista.codCiudad = ciudad.codCiudad " + 
                   "WHERE turista.codigoPaquete = ? " +
                   "GROUP BY ciudad.codCiudad " +
                   "ORDER BY frecuencia DESC"; 

    try (PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, codigoPaquete);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EstadisticaCiudad estadistica = new EstadisticaCiudad();
            estadistica.setCodCiudad(rs.getInt("codCiudad"));
            estadistica.setNombre(rs.getString("nombreCiudad"));
            estadistica.setFrecuencia(rs.getInt("frecuencia"));
            estadisticas.add(estadistica);
        }
    }
    return estadisticas;
}
}
