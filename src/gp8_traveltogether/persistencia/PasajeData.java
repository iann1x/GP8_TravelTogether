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

    public PasajeData(Connection con){
        this.con = con;
    }
    public List<Pasaje> mostrarPasajes(){
        String query = "SELECT * FROM pasaje WHERE estado=1"; 
        List<Pasaje> pasajes = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Pasaje pasaje = new Pasaje();
                pasaje.setCodPasaje(rs.getInt("codPasaje"));
                pasaje.setNombre(rs.getString("nombre"));
                pasaje.setPrecio(rs.getDouble("precio"));                
                pasajes.add(pasaje);
            }
            rs.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pasaje.");
        }
        return pasajes;
    }
    public List<Pasaje> mostrarPasajesPorCiudad(int codCiudad){
        String query = "SELECT * FROM pasaje WHERE codCiudad=? AND estado=1"; 
        List<Pasaje> pasajes = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setCodPasaje(rs.getInt("codPasaje"));
                pasaje.setNombre(rs.getString("nombre"));
                pasaje.setPrecio(rs.getDouble("precio"));                
            }
            rs.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pasaje.");
        }
        return pasajes;
    }
    public double calcularPrecioPasaje(double precioPasaje, List<Turista> turistas){
        return precioPasaje * turistas.size();
    }
}
