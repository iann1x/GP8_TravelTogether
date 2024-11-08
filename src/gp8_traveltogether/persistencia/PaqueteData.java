package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Alojamiento;
import gp8_traveltogether.entidades.Paquete;
import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.EstadisticaCiudad;
import gp8_traveltogether.entidades.Pasaje;
import gp8_traveltogether.entidades.Pension;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PaqueteData{
    private Connection con;
    private TuristaData turistaData = new TuristaData();
    private PasajeData pasajeData = new PasajeData();
    private CiudadData ciudadData = new CiudadData();
    private AlojamientoData alojaData = new AlojamientoData();
    private PensionData pensionData = new PensionData();

    public PaqueteData() {
        con = Conexion.getConexion();
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
                if(rs !=null && rs.next()){
                    int codigoPaquete = rs.getInt(1);
                    paquete.setCodigoPaquete(codigoPaquete);
                    JOptionPane.showMessageDialog(null, "El paquete se guardó con éxito.");

                for (Turista turista : paquete.getTuristas()) {
                    turista.setCodigoPaquete(codigoPaquete);
                    turistaData.agregarTurista(turista);
                }
                }
            ps.close();
            rs.close();
            psTurista.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el paquete."+ex.getMessage());
        }
    }

    public Paquete buscarPaquete(int codigoPaquete) {
        String query = "SELECT origen, destino, fechaInicio, fechaFin, temporada, codAlojam, codPasaje, codAdicional, traslado, montoFinal, estado FROM paquete WHERE codigoPaquete=?";
        Paquete paquete = null;
        String queryTuristas = "SELECT dni, nombre, edad, estado FROM turista WHERE codigoPaquete=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigoPaquete);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                paquete = new Paquete();
                paquete.setCodigoPaquete(codigoPaquete);
                
                Ciudad origen = ciudadData.buscarCiudad(rs.getInt("origen"));
                paquete.setOrigen(origen);
                
                Ciudad destino = ciudadData.buscarCiudad(rs.getInt("destino"));
                paquete.setDestino(destino);
                
                paquete.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                paquete.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                paquete.setTemporada(rs.getString("temporada"));
                
                Alojamiento aloja = alojaData.buscarAlojamiento(rs.getInt("codAlojam"));
                paquete.setEstadia(aloja);
                
                Pasaje pasaje = pasajeData.buscarPasaje(rs.getInt("codPasaje"));
                paquete.setBoleto(pasaje);
                
                Pension pension = pensionData.buscarPensionPorCod(rs.getInt("codAdicional"));
                paquete.setPension(pension);
                
                paquete.setTraslado(rs.getBoolean("traslado"));
                
                paquete.setMontoFinal(rs.getDouble("montoFinal"));
                paquete.setEstado(rs.getBoolean("estado"));
                
                PreparedStatement psTuristas = con.prepareStatement(queryTuristas);
                psTuristas.setInt(1, codigoPaquete);
                ResultSet rsTuristas = psTuristas.executeQuery();
                ArrayList<Turista> turistas = new ArrayList<>();
                
                while (rsTuristas.next()) {
                    Turista turista = new Turista();
                    turista.setDni(rsTuristas.getInt("dni"));
                    turista.setNombre(rsTuristas.getString("nombre"));
                    turista.setEdad(rsTuristas.getInt("edad"));
                    turista.setEstado(rsTuristas.getBoolean("estado"));
                    turistas.add(turista);
                }
                paquete.setTuristas(turistas);    
            
            } else{
                JOptionPane.showMessageDialog(null, "No existe un paquete con ese código.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete. Error: "+ex.getMessage());
        }
        return paquete;
    }

    public void modificarPaquete(Paquete paquete) {
        String query = "UPDATE paquete SET origen=?, destino=?, fechaInicio=?, fechaFin=?, temporada=?, codAlojam=?, codPasaje=?, codAdicional=?, traslado=?, montoFinal=?, estado=? WHERE codigoPaquete=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,paquete.getOrigen().getCodCiudad());
            ps.setInt(2, paquete.getDestino().getCodCiudad());
            ps.setDate(3, Date.valueOf(paquete.getFechaInicio()));
            ps.setDate(4, Date.valueOf(paquete.getFechaFin()));
            ps.setString(5, paquete.getTemporada());
            ps.setInt(6, paquete.getEstadia().getCodAlojam());
            ps.setInt(7, paquete.getBoleto().getCodPasaje());
            ps.setInt (8, paquete.getPension().getCodAdicional());
            ps.setBoolean(9, paquete.isTraslado());
            ps.setDouble(10, paquete.getMontoFinal());
            ps.setBoolean(11, true);
            ps.setInt(12, paquete.getCodigoPaquete());
            
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El paquete se modificó con éxito.");
            } else{
                JOptionPane.showMessageDialog(null, "No se encontró el paquete a modificar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete."+ ex.getMessage());
        }
    }
    
    public boolean existePaquete(int codigoPaquete){
        String query = "SELECT COUNT(*) FROM paquete WHERE codigoPaquete=?";
        boolean existePaquete = false;
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigoPaquete);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                int contador = rs.getInt(1);
                if (contador >0){
                    existePaquete = true;
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Paquete.");
        }
        return existePaquete;
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
    
    public ArrayList<Turista> mostrarTuristasPorPaquete(int codigoPaquete){
        String query = "SELECT * FROM turista WHERE codigoPaquete=?";
        ArrayList <Turista> turistasPaquete = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigoPaquete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Turista turista = new Turista();
                turista.setDni(rs.getInt("dni"));
                turista.setNombre(rs.getString("nombre"));
                turista.setEdad(rs.getInt("edad"));
                turista.setCodigoPaquete(codigoPaquete);
                
                turistasPaquete.add(turista);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Turista.");
        }
        return turistasPaquete;
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
