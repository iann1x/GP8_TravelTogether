package gp8_traveltogether.persistencia;

import gp8_traveltogether.entidades.Alojamiento;
import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.Turista;
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

public class AlojamientoData{
    private Connection con;
    private CiudadData cd = new CiudadData();

    public AlojamientoData() {
        this.con = Conexion.getConexion();
    }

    public void agregarAlojam(Alojamiento alojamiento) {
        String query = "INSERT INTO alojamiento (nombre, codCiudad, tipoAlojam, capacidad, precioNoche, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alojamiento.getNombre());
            ps.setInt(2, alojamiento.getCiudad().getCodCiudad());
            ps.setString(3, alojamiento.getTipoAlojam());
            ps.setInt(4, alojamiento.getCapacidad());
            ps.setDouble(5, alojamiento.getPrecioNoche());
            ps.setBoolean(6, alojamiento.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alojamiento.setCodAlojam(rs.getInt(1));
                //JOptionPane.showMessageDialog(null, "El alojamiento se agregó con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
    }

    public void modificarAlojam(Alojamiento alojamiento) {
        String query = "UPDATE alojamiento SET nombre=?, codCiudad=?, tipoAlojam=?, capacidad=?, precioNoche=?,  estado=? WHERE codAlojam=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, alojamiento.getNombre());
            ps.setInt(2, alojamiento.getCiudad().getCodCiudad());
            ps.setString(3, alojamiento.getTipoAlojam());
            ps.setInt(4, alojamiento.getCapacidad());
            ps.setDouble(5, alojamiento.getPrecioNoche());
            ps.setBoolean(6, alojamiento.isEstado());
            ps.setInt(7, alojamiento.getCodAlojam());

            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El alojamiento se modificó con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
    }
    
    public Alojamiento buscarAlojamiento(int cod){
        String query = "SELECT nombre, codCiudad, tipoAlojam, capacidad, precioNoche, estado FROM alojamiento WHERE codAlojam=?";
        Alojamiento alojamiento = null;
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                alojamiento = new Alojamiento();
                alojamiento.setCodAlojam(cod);
                alojamiento.setNombre(rs.getString("nombre"));
                Ciudad ciudad = cd.buscarCiudad(rs.getInt("codCiudad"));
                alojamiento.setCiudad(ciudad);
                alojamiento.setTipoAlojam(rs.getString("tipoAlojam"));
                alojamiento.setCapacidad(rs.getInt("capacidad"));
                alojamiento.setPrecioNoche(rs.getDouble("precioNoche"));
                alojamiento.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un alojamiento con ese código.");
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
        return alojamiento;
    }

    public void bajaLogicaAlojam(int id) {
        String query = "UPDATE alojamiento SET estado=0 WHERE codAlojam=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El alojamiento se dio de baja con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
    }

    public void altaLogicaAlojam(int id) {
        String query = "UPDATE alojamiento SET estado=1 WHERE codAlojam=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El alojamiento se dio de alta con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
    }

    public ArrayList<Alojamiento> mostrarAlojPorCiudad (int codCiudad) {
        String query = "SELECT * FROM alojamiento WHERE codCiudad=?";
        ArrayList <Alojamiento> alojaPorCiudad = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alojamiento alojamiento = new Alojamiento();
                alojamiento.setCodAlojam(rs.getInt("codAlojam"));
                alojamiento.setNombre(rs.getString("nombre"));
                
                Ciudad ciudad = new Ciudad();
                ciudad.setCodCiudad(rs.getInt("codCiudad"));
                alojamiento.setCiudad(ciudad);
       
                alojamiento.setTipoAlojam(rs.getString("tipoAlojam"));
                alojamiento.setCapacidad(rs.getInt("capacidad"));
                alojamiento.setPrecioNoche(rs.getDouble("precioNoche"));
                
                alojaPorCiudad.add(alojamiento);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
        return alojaPorCiudad;
    }
}//cierre clase AlojamientoData