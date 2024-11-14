
package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.EstadisticaCiudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                JOptionPane.showMessageDialog(null, "La ciudad se cargó con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }     
    }
    
    public Ciudad buscarCiudad(int cod){
        String query = "SELECT nombre, estado FROM ciudad WHERE codCiudad =?";
        Ciudad ciudad = null;
        
         try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt (1, cod);
            ResultSet rs = ps.executeQuery();
             
            if (rs.next()){
                ciudad = new Ciudad ();
                ciudad.setCodCiudad(cod);
                ciudad.setNombre(rs.getString("nombre"));
                ciudad.setEstado(true);
            } else {
                JOptionPane.showMessageDialog(null, "No exite una ciudad con ese código.");
            }
            ps.close();
            rs.close();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
         }            
    return ciudad;
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
    
    public void bajaLogicaCiudad(int cod) {
        String consultaOrigen = "SELECT COUNT(*) AS tienePasajes FROM pasaje WHERE origen=?";
        String consultaDestino = "SELECT COUNT(*) AS tienePasajes FROM pasaje WHERE destino=?";
        String consultaAloj = "SELECT COUNT(*) AS tieneAlojamientos FROM alojamiento WHERE codCiudad=?";
        String queryBaja = "UPDATE ciudad SET estado=0 WHERE codCiudad =?";
        
        try {
            PreparedStatement psOrigen = con.prepareStatement(consultaOrigen);
            psOrigen.setInt(1, cod);
            ResultSet rsOrigen = psOrigen.executeQuery();
            
            if (rsOrigen.next()){
                int pasajes = rsOrigen.getInt("tienePasajes");
                if(pasajes >0){
                    JOptionPane.showMessageDialog(null, "La ciudad tiene pasajes asociados. Eliminelos antes de darla de baja.");
                    return;
                }
            }
            
            PreparedStatement psDestino = con.prepareStatement(consultaDestino);
            psDestino.setInt(1, cod);
            ResultSet rsDestino = psDestino.executeQuery();
            
            if (rsDestino.next()){
                int pasajes = rsDestino.getInt("tienePasajes");
                if(pasajes >0){
                    JOptionPane.showMessageDialog(null, "La ciudad tiene pasajes asociados. Eliminelos antes de darla de baja.");
                    return;
                }
            }
            
           PreparedStatement psAlojam = con.prepareStatement(consultaAloj);
            psAlojam.setInt(1, cod);
            ResultSet rsAlojam = psAlojam.executeQuery();
            
            if (rsAlojam.next()){
                int alojamientos = rsAlojam.getInt("tieneAlojamientos");
                if(alojamientos >0){
                    JOptionPane.showMessageDialog(null, "La ciudad tiene alojamientos asociados. Eliminelos antes de darla de baja.");
                    return;
                }
            }
      
            PreparedStatement psBaja = con.prepareStatement(queryBaja);
            psBaja.setInt(1, cod);
            int exito = psBaja.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "La ciudad se dio de baja con éxito.");
            }
            psOrigen.close();
            psDestino.close();
            rsOrigen.close();
            rsDestino.close();
            psAlojam.close();
            rsAlojam.close();
            psBaja.close();
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
    
public List<EstadisticaCiudad> mostrarDestinosMasElegidosPorTemporada(String temporada) {
     //System.out.println("Pruebaaaaaaaaaaa");
     String query = "SELECT ciudad.codCiudad, ciudad.nombre, COUNT(*) AS frecuencia " +
                   "FROM ciudad " +
                   "JOIN paquete ON ciudad.nombre = paquete.destino " +
                   "WHERE paquete.temporada = ? AND paquete.estado = 1 " +
                   "GROUP BY ciudad.codCiudad, ciudad.nombre " +
                   "ORDER BY frecuencia DESC";
    List<EstadisticaCiudad> estadisticas = new ArrayList<>();
    try {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, temporada);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            EstadisticaCiudad estadistica = new EstadisticaCiudad();
            estadistica.setCodCiudad(rs.getInt("codCiudad"));
            estadistica.setNombre(rs.getString("nombre"));
            estadistica.setFrecuencia(rs.getInt("frecuencia"));
            estadisticas.add(estadistica);
            //System.out.println("Pruebaaaaaaaaaaa" + rs.getString("nombre"));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
    }
    return estadisticas;
}

}
