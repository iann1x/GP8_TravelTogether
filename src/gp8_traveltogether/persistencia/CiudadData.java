
package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Ciudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CiudadData {
     private Connection con;

    public CiudadData() {
        con = Conexion.getConexion();      
    }
    
    public void agregarCiudad(Ciudad ciudad) {
        String query = "INSERT INTO ciudad (nombre, estado) VALUES (?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ciudad.getNombre());
            ps.setBoolean(2, ciudad.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ciudad.setCodCiudad(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El destino se cargó con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }     
    }
    
    public ArrayList<Ciudad> mostrarCiudades() {
        String query = "SELECT codCiudad, nombre FROM ciudad WHERE estado=1";
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                ciudad.setCodCiudad(rs.getInt("codCiudad"));
                ciudad.setNombre(rs.getString("nombre"));
                ciudades.add(ciudad);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }
        return ciudades;
    }
    
    public void modificarCiudad(Ciudad ciudad) {
        String query = "UPDATE ciudad SET nombre=? WHERE codCiudad =?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, ciudad.getNombre());
            ps.setInt(2, ciudad.getCodCiudad());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "La ciudad se modificó con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }
    }
    
    public void bajaLogicaCiudad(int id) {
        String query = "UPDATE ciudad SET estado=0 WHERE codCiudad =?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "La ciudad se dio de baja con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }
    }
    
    public void altaLogicaCiudad(int id) {
        String query = "UPDATE ciudad SET estado=1 WHERE codCiudad =?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "La ciudad se dio de alta con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }
    }
    


}
