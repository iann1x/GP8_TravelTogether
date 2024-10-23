package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Paquete;
import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.Turista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class PaqueteData{
    private Connection con;
    private TuristaData turistaData;
    private PasajeData pasajeData;
    private CiudadData ciudadData;
    private AlojamientoData alojaData;

    public PaqueteData(Connection con, TuristaData turistaData, PasajeData viajeData, CiudadData ciudadData, AlojamientoData alojaData){
        this.con = con;
        this.turistaData = turistaData;
        this.pasajeData = pasajeData;
        this.ciudadData = ciudadData;
        this.alojaData = alojaData;
    }
    public double calcularPresupuesto(Paquete paquete){
        double total = 0;
        for (Iterator<Turista> it = paquete.getTuristas().iterator(); it.hasNext();) {
            Turista turista = it.next(); 
            total += paquete.getPrecio();
        }
        return total;
    }
    public void guardarPaquete(Paquete paquete){
        String query = "INSERT INTO paquete (codigoPaquete, nombre, precio) VALUES (?, ?, ?)";

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, paquete.getCodigoPaquete());
            ps.setString(2, paquete.getNombre());
            ps.setDouble(3, paquete.getPrecio());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El paquete se guardó con éxito.");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo guardar el paquete.");
        }
    }
    public Paquete buscarPaquete(int codigoPaquete){
        String query = "SELECT * FROM paquete WHERE codigoPaquete=?";
        Paquete paquete = null;

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigoPaquete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                paquete = new Paquete();
                paquete.setCodigoPaquete(rs.getInt("codigoPaquete"));
                paquete.setNombre(rs.getString("nombre"));
                paquete.setPrecio(rs.getDouble("precio"));
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
        return paquete;
    }
    public void modificarPaquete(int codigoPaquete, Paquete paquete){
        String query = "UPDATE paquete SET nombre=?, precio=? WHERE codigoPaquete=?";

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, paquete.getNombre());
            ps.setDouble(2, paquete.getPrecio());
            ps.setInt(3, codigoPaquete);
            int exito = ps.executeUpdate();
            if (exito == 1){
                JOptionPane.showMessageDialog(null, "El paquete se modificó con éxito.");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
    }
    public void bajaLogicaPaquete(int codigoPaquete){
        String query = "UPDATE paquete SET estado=0 WHERE codigoPaquete=?";

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigoPaquete);
            int exito = ps.executeUpdate();
            if (exito == 1){
                JOptionPane.showMessageDialog(null, "El paquete se dio de baja con éxito.");
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
    }
    public void agregarTurista(int codigoPaquete, Turista turista){        
    }
    public List<Paquete> mostrarPaquetes(){
        String query = "SELECT * FROM paquete WHERE estado=1"; 
        List<Paquete> paquetes = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Paquete paquete = new Paquete();
                paquete.setCodigoPaquete(rs.getInt("codigoPaquete"));
                paquete.setNombre(rs.getString("nombre"));
                paquete.setPrecio(rs.getDouble("precio"));
                paquetes.add(paquete);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
        return paquetes;
    }
    public List<Ciudad> mostrarCiudadPreferidaPorMes(int mes){        
        return new ArrayList<>(); 
    }
    public List<Ciudad> mostrarCiudadPreferidaPorTemp(String temp){       
        return new ArrayList<>(); 
    }
}
