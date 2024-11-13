
package gp8_traveltogether.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL="jdbc:mariadb://localhost/";
    private static final String DB="gp8_traveltogether";
    private static final String USUARIO="root";
    private static final String PSSWORD="";
    private static Connection conexion;
    
    public static Connection getConexion(){
        if (conexion==null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(URL+DB,USUARIO,PSSWORD);
                //JOptionPane.showMessageDialog(null, "Base conectada");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "No se encontró el driver o no se pudo establecer la conexion");
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
            }
        }
        return conexion;
    }

    public static Connection obtenerConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
