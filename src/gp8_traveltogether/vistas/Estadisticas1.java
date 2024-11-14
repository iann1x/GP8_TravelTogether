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
import gp8_traveltogether.persistencia.CiudadData;

/**
 *
 * @author Kevin
 */
public class Estadisticas1 extends javax.swing.JInternalFrame {
    private JTable jTableEstadisticas;
    private PaqueteData paqueteData;
        private DefaultTableModel model = new DefaultTableModel();
        private CiudadData ciudad;
    
    public Estadisticas1( ) {
        initComponents();
        paqueteData = new PaqueteData(); 
        ciudad = new CiudadData();
  
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtEstadisticas = new javax.swing.JTable();
        jcbTemporadas = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jcbMeses = new javax.swing.JComboBox<>();
        bg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo de Ciudad", "Ciudad", "Frecuencia"
            }
        ));
        jScrollPane1.setViewportView(jtEstadisticas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 370, 170));

        jcbTemporadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "alta", "media", "baja" }));
        jcbTemporadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTemporadasActionPerformed(evt);
            }
        });
        getContentPane().add(jcbTemporadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 100, -1));

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, 70, 30));

        jcbMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre" }));
        jcbMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMesesActionPerformed(evt);
            }
        });
        getContentPane().add(jcbMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 110, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/estadisticas600_22.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, -1, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    
/*
    private void jcbMesesActionPerformed(java.awt.event.ActionEvent evt) {                                         
       
       
     
  
            cargarEstadisticasPorMes(mesSeleccionado);     }                                        
*/


    private void jcbTemporadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTemporadasActionPerformed
          String temporadaSeleccionada = (String) jcbTemporadas.getSelectedItem();
         //System.out.println(temporadaSeleccionada);
         cargarEstadisticasPorTemporada(temporadaSeleccionada);
            
    }//GEN-LAST:event_jcbTemporadasActionPerformed

    private void jcbMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMesesActionPerformed
    int mesSeleccionado = jcbMeses.getSelectedIndex() + 2;  
    //System.out.println("Mes seleccionado: " + mesSeleccionado);
    cargarEstadisticasPorMes(mesSeleccionado);
    }//GEN-LAST:event_jcbMesesActionPerformed



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

   
    
    private void cargarEstadisticasPorMes(int mes){
    List<Ciudad> estadisticas = paqueteData.mostrarCiudadPreferidaPorMes(mes);
    DefaultTableModel model1 = (DefaultTableModel) jtEstadisticas.getModel();
    model1.setRowCount(0); 

    for (Ciudad estadistica : estadisticas) {
        model1.addRow(new Object[]{
            estadistica.getCodCiudad(),
            estadistica.getNombre(),
            estadistica.getFrecuencia()
        });
    }
    }
    
     private void cargarEstadisticasPorTemporada(String temporada) {
          List<EstadisticaCiudad> estadisticas = ciudad.mostrarDestinosMasElegidosPorTemporada(temporada);
        DefaultTableModel model1 = (DefaultTableModel) jtEstadisticas.getModel();
        model1.setRowCount(0); 
        for (EstadisticaCiudad estadistica : estadisticas) {
            //System.out.println(estadistica.getCodCiudad());
            model1.addRow(new Object[]{
                estadistica.getCodCiudad(),
                estadistica.getNombre(),
                estadistica.getFrecuencia()
            });
        }
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbMeses;
    private javax.swing.JComboBox<String> jcbTemporadas;
    private javax.swing.JTable jtEstadisticas;
    // End of variables declaration//GEN-END:variables
}
