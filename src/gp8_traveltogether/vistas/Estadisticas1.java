/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package gp8_traveltogether.vistas;


import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.EstadisticaCiudad;
import gp8_traveltogether.persistencia.Conexion;
import gp8_traveltogether.persistencia.EstadisticaCiudadData;
import gp8_traveltogether.persistencia.PaqueteData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin
 */
public class Estadisticas1 extends javax.swing.JInternalFrame {
    private JTable jTableEstadisticas;
    private PaqueteData paqueteData;
        private DefaultTableModel model = new DefaultTableModel();
    
    public Estadisticas1( ) {
          initComponents();
        paqueteData = new PaqueteData(); 
  
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtEstadisticas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jcbTemporadas = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jcbMeses = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 204));

        jtEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo ", "Ciudad", "Frecuencia"
            }
        ));
        jScrollPane1.setViewportView(jtEstadisticas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Destinos mas elegidos");

        jcbTemporadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primavera", "Verano", "Otoño", "Invierno" }));
        jcbTemporadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTemporadasActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("temporada:");

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Mes:");

        jcbMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre" }));
        jcbMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMesesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jcbTemporadas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jcbMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(435, 435, 435)
                .addComponent(jButton2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jcbTemporadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcbMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void jcbMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMesesActionPerformed
int mesSeleccionado = jcbMeses.getSelectedIndex() + 1;
        
        
  
            cargarEstadisticasPorMes(mesSeleccionado);     }//GEN-LAST:event_jcbMesesActionPerformed

    private void jcbTemporadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTemporadasActionPerformed
         String temporadaSeleccionada = (String) jcbTemporadas.getSelectedItem();
            cargarEstadisticasPorTemporada(temporadaSeleccionada);
            
    }//GEN-LAST:event_jcbTemporadasActionPerformed

  public void mostrarEstadisticasEnTabla(List<Ciudad> estadisticas) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Código de Ciudad");
    model.addColumn("Nombre");
    model.addColumn("Frecuencia");

    for (Ciudad ciudad : estadisticas) {
        model.addRow(new Object[]{ciudad.getCodCiudad(), ciudad.getNombre(), ciudad.getFrecuencia()});
    }

    jtEstadisticas.setModel(model);
}

   
    
    private void cargarEstadisticasPorMes(int mes) throws SQLException {
    // Define la consulta SQL
    String query = "SELECT c.codCiudad, c.nombre, COUNT(*) AS frecuencia "
                 + "FROM paquete p "
                 + "JOIN ciudad c ON p.codigoCiudad = c.codCiudad "
                 + "WHERE MONTH(p.fechaInicio) = ? "
                 + "GROUP BY c.codCiudad, c.nombre "
                 + "ORDER BY frecuencia DESC LIMIT 25";

    try (Connection con = Conexion.obtenerConexion();  // Conexión abierta
         PreparedStatement ps = con.prepareStatement(query)) {

        // Establece el mes en la consulta
        ps.setInt(1, mes);

        try (ResultSet rs = ps.executeQuery()) {
            List<Ciudad> estadisticas = new ArrayList<>();

            // Recorrer los resultados
            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                ciudad.setCodCiudad(rs.getInt("codCiudad"));
                ciudad.setNombre(rs.getString("nombre"));
                ciudad.setFrecuencia(rs.getInt("frecuencia"));
                estadisticas.add(ciudad);
            }

            // Mostrar los resultados en la tabla
            mostrarEstadisticasEnTabla(estadisticas);
        }

    }}
  private void cargarEstadisticasPorTemporada(String temporada) {
        List<EstadisticaCiudad> estadisticas = paqueteData.mostrarDestinosMasElegidosPorTemporada(temporada);
        DefaultTableModel model = (DefaultTableModel) jTableEstadisticas.getModel();
        model.setRowCount(0); 

        for (EstadisticaCiudad estadistica : estadisticas) {
            model.addRow(new Object[]{
                estadistica.getCodCiudad(),
                estadistica.getNombre(),
                estadistica.getFrecuencia()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbMeses;
    private javax.swing.JComboBox<String> jcbTemporadas;
    private javax.swing.JTable jtEstadisticas;
    // End of variables declaration//GEN-END:variables
}
