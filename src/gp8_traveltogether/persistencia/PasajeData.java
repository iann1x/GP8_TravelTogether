package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.Pasaje;
import gp8_traveltogether.entidades.Turista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;

public class PasajeData{
   private Connection con;
   private CiudadData cd = new CiudadData();

    public PasajeData() {
        con = Conexion.getConexion();
    }

    public void agregarPasaje (Pasaje pasaje){
        String query = "INSERT INTO pasaje(origen, destino, precioPasaje, tipoViaje, estado) VALUES (?, ?, ?, ?, ?)";
        
       try {
           PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           ps.setInt(1, pasaje.getOrigen().getCodCiudad());
           ps.setInt(2, pasaje.getDestino().getCodCiudad());
           ps.setDouble(3, pasaje.getPrecioPasaje());
           ps.setString(4, pasaje.getTipoViaje());
           ps.setBoolean(5, pasaje.isEstado());
           ps.executeUpdate();
           
           ResultSet rs = ps.getGeneratedKeys();
           if(rs.next()){
               pasaje.setCodPasaje(rs.getInt(1));
               JOptionPane.showMessageDialog(null, "El pasaje se agregó con éxito.");
           }
           ps.close();
           rs.close();   
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasaje.");
       }
    }
    
    public void modificarPasaje (Pasaje pasaje){
        String query = "UPDATE pasaje SET origen=?,destino=?, precioPasaje=?, tipoViaje=?, estado=? WHERE codPasaje=?";
        
       try {
           PreparedStatement ps = con.prepareStatement(query);
           ps.setInt(1, pasaje.getOrigen().getCodCiudad());
           ps.setInt(2, pasaje.getDestino().getCodCiudad());
           ps.setDouble(3, pasaje.getPrecioPasaje());
           ps.setString(4, pasaje.getTipoViaje());
           ps.setBoolean(5, pasaje.isEstado());
           ps.setInt(6, pasaje.getCodPasaje());
           
           int exito = ps.executeUpdate();
           if(exito ==1){
               JOptionPane.showMessageDialog(null, "El pasaje se modificó con éxito");
           }
           ps.close();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasaje.");
       }
    }
    
    public ArrayList<Pasaje> mostrarPasajes(Ciudad origen, Ciudad destino) {
        String query = "SELECT * FROM pasaje WHERE origen=? AND destino =?"; 
        ArrayList<Pasaje> pasajes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, origen.getCodCiudad());
            ps.setInt(2, destino.getCodCiudad());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setCodPasaje(rs.getInt("codPasaje"));
                pasaje.setOrigen(origen);
                pasaje.setDestino(destino);
                pasaje.setPrecioPasaje(rs.getDouble("precioPasaje"));
                pasaje.setTipoViaje(rs.getString("tipoViaje"));
                pasajes.add(pasaje);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pasaje.");
        }
        return pasajes;
    }
    
    public Pasaje buscarPasaje(int cod){
        String query = "SELECT origen, destino, precioPasaje, tipoViaje, estado FROM pasaje WHERE codPasaje=?";
        Pasaje pasaje = null;
        
       try {
           PreparedStatement ps = con.prepareStatement(query);
           ps.setInt(1, cod);
           ResultSet rs = ps.executeQuery();
           
           if(rs.next()){
               pasaje = new Pasaje();
               pasaje.setCodPasaje(cod);
               
               Ciudad origen = cd.buscarCiudad(rs.getInt("origen"));
               pasaje.setOrigen(origen);
               
               Ciudad destino = cd.buscarCiudad(rs.getInt("destino"));
               pasaje.setDestino(destino);
               
               pasaje.setPrecioPasaje(rs.getDouble("precioPasaje"));
               pasaje.setTipoViaje(rs.getString("tipoViaje"));
               pasaje.setEstado(rs.getBoolean("estado"));
            } else {
               JOptionPane.showMessageDialog(null, "No exite un pasaje con ese código.");
            }
           ps.close();
           rs.close();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pasaje.");
       }
        return pasaje;
    }
    
    public void bajaLogica (int cod){
        String query = "UPDATE pasaje SET estado=0 WHERE codPasaje=?";
        
       try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cod);
            int exito = ps.executeUpdate();
            if (exito ==1){
               JOptionPane.showMessageDialog(null, "El pasaje se dio de baja con éxito.");
            }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pasaje.");
       }
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
} //cierre clase PaqueteData
