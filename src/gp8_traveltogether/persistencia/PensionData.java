package gp8_traveltogether.persistencia;
import gp8_traveltogether.entidades.Pension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PensionData{
   private Connection con;

    public PensionData(Connection con) {
        this.con = con;
    }

    public void crearPension(Pension pension) {
        String query = "INSERT INTO pension (nombre, porcentaje) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pension.getNombre());
            ps.setDouble(2, pension.getPorcentaje());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pension.setCodAdicional(rs.getInt(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pensión.");
        }
    }

    public ArrayList<Pension> mostrarAdicionales() {
        String query = "SELECT codAdicional, nombre, porcentaje FROM pension";
        ArrayList<Pension> pensiones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pension pension = new Pension();
                pension.setCodAdicional(rs.getInt("codAdicional"));
                pension.setNombre(rs.getString("nombre"));
                pension.setPorcentaje(rs.getDouble("porcentaje"));
                pensiones.add(pension);
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pensión.");
        }
        return pensiones;
    }

    public void modificarPension(Pension pension) {
        String query = "UPDATE pension SET nombre=?, porcentaje=? WHERE codAdicional=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pension.getNombre());
            ps.setDouble(2, pension.getPorcentaje());
            ps.setInt(3, pension.getCodAdicional());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "La pensión se modificó con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pensión.");
        }
    }

    public void eliminarPension(int id) {
        String query = "DELETE FROM pension WHERE codAdicional=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "La pensión se eliminó con éxito.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pensión.");
        }
    }
}

    
        
   
    

    
