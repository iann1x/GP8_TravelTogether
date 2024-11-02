package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Paquete;
import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.EstadisticaCiudad;
import gp8_traveltogether.entidades.Turista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private PensionData pensionData;

    public PaqueteData(Connection con, TuristaData turistaData, PasajeData viajeData, CiudadData ciudadData, AlojamientoData alojaData) {
        this.con = con;
        this.turistaData = turistaData;
        this.pasajeData = viajeData;
        this.ciudadData = ciudadData;
        this.alojaData = alojaData;
    }
    
    public PaqueteData(){
    }

    public double calcularPresupuesto(Paquete paquete) {
        double total = 0;
        for (Iterator<Turista> it = paquete.getTuristas().iterator(); it.hasNext();) {
            Turista turista = it.next(); 
            total += paquete.getMontoFinal();
        }
        return total;
    }

    public void guardarPaquete(Paquete paquete) {
        String query = "INSERT INTO paquete (origen, destino, fechaInicio, fechaFin, temporada, codAlojam, codPasaje, codAdicional, traslado, montoFinal, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryTurista = "INSERT INTO turista(dni, nombre, edad, codigoPaquete, estado) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psTurista = con.prepareStatement(queryTurista);
            
            ps.setInt(1, paquete.getOrigen().getCodCiudad());
            ps.setInt(2, paquete.getDestino().getCodCiudad());
            ps.setDate(3, Date.valueOf(paquete.getFechaInicio()));
            ps.setDate(4, Date.valueOf(paquete.getFechaFin()));
            ps.setString(5, paquete.getTemporada());
            ps.setInt (6, paquete.getEstadia().getCodAlojam());
            ps.setInt (7, paquete.getBoleto().getCodPasaje());
            ps.setInt(8, paquete.getPension().getCodAdicional());
            ps.setBoolean(9, paquete.isTraslado());
            ps.setDouble(10, paquete.getMontoFinal());
            ps.setBoolean(11, true);
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                paquete.setCodigoPaquete(rs.getInt(1));   
            }
            
            for (Turista turista : paquete.getTuristas()) {
                psTurista.setInt(1, turista.getDni());
                psTurista.setString(2, turista.getNombre()); 
                psTurista.setInt(3, turista.getEdad()); 
                psTurista.setInt(4, paquete.getCodigoPaquete()); 
                ps.setBoolean(5, true);
                psTurista.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "El paquete se guardó con éxito.");
            ps.close();
            psTurista.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el paquete.");
        }
    }

    public Paquete buscarPaquete(int codigoPaquete) {
        String query = "SELECT * FROM paquete WHERE codigoPaquete=?";
        Paquete paquete = null;
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigoPaquete);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                paquete = new Paquete();
                paquete.setCodigoPaquete(rs.getInt("codigoPaquete"));
                paquete.setMontoFinal(rs.getDouble("precio"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
        return paquete;
    }

    public void modificarPaquete(int codigoPaquete, Paquete paquete) {
        String query = "UPDATE paquete SET precio=? WHERE codigoPaquete=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, paquete.getMontoFinal());
            ps.setInt(2, codigoPaquete);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El paquete se modificó con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
    }

    public void bajaLogicaPaquete(int codigoPaquete) {
        String query = "UPDATE paquete SET estado=0 WHERE codigoPaquete=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigoPaquete);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El paquete se dio de baja con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
    }

    public void agregarTurista(int codigoPaquete, Turista turista) {
    }

    public List<Paquete> mostrarPaquetes() {
        String query = "SELECT * FROM paquete WHERE estado=1"; 
        List<Paquete> paquetes = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paquete paquete = new Paquete();
                paquete.setCodigoPaquete(rs.getInt("codigoPaquete"));
                paquete.setMontoFinal(rs.getDouble("precio"));
                paquetes.add(paquete);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
        return paquetes;
    }

    public List<Ciudad> mostrarCiudadPreferidaPorMes(int mes) {
        String query = "SELECT ciudad.codCiudad, ciudad.nombre FROM ciudad " +
                       "JOIN paquete ON ciudad.codCiudad = paquete.codCiudad " +
                       "WHERE MONTH(paquete.fecha) = ? AND paquete.estado = 1";
        List<Ciudad> ciudades = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, mes);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                ciudad.setCodCiudad(rs.getInt("codCiudad"));
                ciudad.setNombre(rs.getString("nombre"));
                ciudades.add(ciudad);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
        }
        return ciudades;
    }

   public List<EstadisticaCiudad> mostrarCiudadPreferidaPorTemp(String temporada) {
    String query = "SELECT ciudad.codCiudad, ciudad.nombre, COUNT(*) AS frecuencia " +
                   "FROM ciudad " +
                   "JOIN paquete ON ciudad.codCiudad = paquete.codCiudad " +
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
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Ciudad.");
    }
    return estadisticas;
}
}
