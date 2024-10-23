package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Pasaje;
import gp8_traveltogether.entidades.Turista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PasajeData{
   private Connection con;

    public PasajeData(Connection con) {
        this.con = con;
    }

    public List<Pasaje> mostrarPasajes() {
        String query = "SELECT * FROM pasaje WHERE estado=1"; 
        List<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setCodPasaje(rs.getInt("codPasaje"));
                pasaje.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
                pasaje.setPrecioPasaje(rs.getDouble("precioPasaje"));
                pasaje.setTipoViaje(rs.getString("tipoViaje"));
                pasajes.add(pasaje);
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pasaje.");
        }
        return pasajes;
    }

    public List<Pasaje> mostrarPasajesPorCiudad(int codCiudad) {
        String query = "SELECT * FROM pasaje WHERE codCiudad=? AND estado=1"; 
        List<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setCodPasaje(rs.getInt("codPasaje"));
                pasaje.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
                pasaje.setPrecioPasaje(rs.getDouble("precioPasaje"));
                pasaje.setTipoViaje(rs.getString("tipoViaje"));
                pasajes.add(pasaje);
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pasaje.");
        }
        return pasajes;
    }

    public void calcularPrecioPasaje(int codPasaje, List<Turista> turistas) {
        String query = "UPDATE pasaje SET precioPasaje=? WHERE codPasaje=?";
        double precioPasaje = 0;

        try {
            PreparedStatement ps = con.prepareStatement("SELECT precioPasaje FROM pasaje WHERE codPasaje=?");
            ps.setInt(1, codPasaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                precioPasaje = rs.getDouble("precioPasaje");
            }

            double nuevoPrecio = precioPasaje * turistas.size();

            PreparedStatement psUpdate = con.prepareStatement(query);
            psUpdate.setDouble(1, nuevoPrecio);
            psUpdate.setInt(2, codPasaje);
            psUpdate.executeUpdate();

            JOptionPane.showMessageDialog(null, "El precio del pasaje se actualizó con éxito.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el precio del pasaje.");
        }
    }
}
