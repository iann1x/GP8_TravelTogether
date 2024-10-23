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
import javax.swing.JOptionPane;

public class AlojamientoData {
    private Connection con;

    public AlojamientoData(Connection con) {
        this.con = con;
    }
    public void agregarAlojam(Alojamiento alojamiento) {
        String query = "INSERT INTO alojamiento (nombre, direccion, precioPorNoche, codCiudad) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alojamiento.getNombre());
            ps.setString(2, alojamiento.getDireccion());
            ps.setDouble(3, alojamiento.getPrecioPorNoche());
            ps.setInt(4, alojamiento.getCodCiudad());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alojamiento.setCodAdicional(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El alojamiento se agregó con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
    }
    public void modificarAlojam(Alojamiento alojamiento) {
        String query = "UPDATE alojamiento SET nombre=?, direccion=?, precioPorNoche=? WHERE codAdicional=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, alojamiento.getNombre());
            ps.setString(2, alojamiento.getDireccion());
            ps.setDouble(3, alojamiento.getPrecioPorNoche());
            ps.setInt(4, alojamiento.getCodAdicional());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "El alojamiento se modificó con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Alojamiento.");
        }
    }
    public void bajaLogicaAlojam(int id) {
        String query = "UPDATE alojamiento SET estado=0 WHERE codAdicional=?";

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
        String query = "UPDATE alojamiento SET estado=1 WHERE codAdicional=?";

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
    public List<Ciudad> mostrarCiudades(int codCiudad) {
        String query = "SELECT * FROM ciudad WHERE codCiudad=?";
        List<Ciudad> ciudades = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codCiudad);
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
    public double calcularPrecio(Double precioNoche, List<Turista> turistas) {
        return precioNoche * turistas.size();
    }
}


