
package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Ciudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CiudadData {
    private Connection con = null;

    public CiudadData() {
        con = Conexion.getConexion();      
    }
    
    public void agregarCiudad(Ciudad ciudad){
        String query = "INSERT INTO ciudad (nombre, estado) VALUES (?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ciudad.getNombre());
            ps.setBoolean(2, ciudad.isEstado());
            ps.executeQuery();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                ciudad.setCodCiudad(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El destino se cargó con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }     
    }
    
    
    
    
    
    
    
}
