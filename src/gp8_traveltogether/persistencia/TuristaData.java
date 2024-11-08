package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.Turista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TuristaData{
     private Connection con;

    public TuristaData() {
        this.con = Conexion.getConexion();
    }

    public void agregarTurista(Turista turista) {
        String query = "INSERT INTO turista (dni, nombre, edad, codigoPaquete, estado) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, turista.getDni());
            ps.setString(2, turista.getNombre());
            ps.setInt(3, turista.getEdad());
            ps.setInt(4, turista.getCodigoPaquete());
            ps.setBoolean(5,true);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El turista se agregó con éxito.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
        }
    }
    
    public ArrayList <Turista> mostrarTuristas() {
        String query = "SELECT dni, nombre, edad, codigoPaquete, estado FROM turista";
        ArrayList<Turista> turistas = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Turista turista = new Turista();
                turista.setDni(rs.getInt("dni"));
                turista.setNombre(rs.getString("nombre"));
                turista.setEdad(rs.getInt("edad"));
                turistas.add(turista);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
        }
        return turistas;
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
    
    public boolean existeTuristaEnPaquete(int dni, int codigoPaquete) {
        String query = "SELECT (*) FROM turista WHERE dni=? AND codigoPaquete=?";
        
         try {
             PreparedStatement ps = con.prepareStatement(query);
             ps.setInt(1, dni);
             ps.setInt(2, codigoPaquete);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                 return rs.getInt(1)>0;
             }  
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
         }
        return false; 
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
