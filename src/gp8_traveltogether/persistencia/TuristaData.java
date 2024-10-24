package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Turista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TuristaData{
     private Connection con;

    public TuristaData(Connection con) {
        this.con = con;
    }

    public void agregarTurista(Turista turista) {
        String query = "INSERT INTO turista (dni, nombre, edad, codigoPaquete, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, turista.getDni());
            ps.setString(2, turista.getNombre());
            ps.setInt(3, turista.getEdad());
            ps.setInt(4, turista.getCodigoPaquete());
            ps.setBoolean(5, turista.isEstado());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El turista se agregó con éxito.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
        }
    }

    public void modificarTurista(Turista turista) {
        String query = "UPDATE turista SET nombre=?, edad=?, codigoPaquete=?, estado=? WHERE dni=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, turista.getNombre());
            ps.setInt(2, turista.getEdad());
            ps.setInt(3, turista.getCodigoPaquete());
            ps.setBoolean(4, turista.isEstado());
            ps.setInt(5, turista.getDni());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El turista se modificó con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
        }
    }

    public void bajaLogicaTurista(int dni) {
        String query = "UPDATE turista SET estado=0 WHERE dni=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, dni);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El turista se dio de baja con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
        }
    }

    public void altaLogicaTurista(int dni) {
        String query = "UPDATE turista SET estado=1 WHERE dni=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, dni);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El turista se dio de alta con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
        }
    }
}
