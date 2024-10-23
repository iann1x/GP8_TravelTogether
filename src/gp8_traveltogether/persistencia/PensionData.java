package gp8_traveltogether.persistencia;
import gp8_traveltogether.entidades.Pension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PensionData {
    private Connection con;

    public PensionData(Connection con) {
        this.con = con;
    }
    public void crearPension(Pension pension) {
        String query = "INSERT INTO pension (nombre, porcentaje, estado) VALUES (?, ?, ?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, pension.getNombre());
                ps.setDouble(2, pension.getPorcentaje());
                ps.setBoolean(3, true);
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    pension.setCodAdicional(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "La pensión se cargó con éxito.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pensión.");
        }
    }

    public ArrayList<Pension> mostrarAdicionales() {
        String query = "SELECT codAdicional, nombre, porcentaje FROM pension WHERE estado=1";
        ArrayList<Pension> pensiones = new ArrayList<>();

        try {
            ResultSet rs;
            try (PreparedStatement ps = con.prepareStatement(query)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    Pension pension = new Pension();
                    pension.setCodAdicional(rs.getInt("codAdicional"));
                    pension.setNombre(rs.getString("nombre"));
                    pension.setPorcentaje(rs.getDouble("porcentaje"));
                    
                    pensiones.add(pension);
                }
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pensión.");
        }
        return pensiones;
    }

    public void modificarPension(Pension pension) {
        String query = "UPDATE pension SET nombre=?, porcentaje=? WHERE codAdicional=?";

        try {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, pension.getNombre());
                ps.setDouble(2, pension.getPorcentaje());
                ps.setInt(3, pension.getCodAdicional());
                int exito = ps.executeUpdate();
                if (exito == 1) {
                    JOptionPane.showMessageDialog(null, "La pensión se modificó con éxito.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pensión.");
        }
    }

    public void bajaLogicaPension(int id) {
        String query = "UPDATE pension SET estado=0 WHERE codAdicional=?";

        try {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);
                
                int exito = ps.executeUpdate();
                if (exito == 1) {
                    JOptionPane.showMessageDialog(null, "La pensión se dio de baja con éxito.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pensión.");
        }
    }

    public void altaLogicaPension(int id) {
        String query = "UPDATE pension SET estado=1 WHERE codAdicional=?";

        try {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);
                
                int exito = ps.executeUpdate();
                if (exito == 1) {
                    JOptionPane.showMessageDialog(null, "La pensión se dio de alta con éxito.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla Pensión.");
        }
    }
}

    
        
   
    

    
