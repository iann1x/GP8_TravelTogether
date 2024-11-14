/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gp8_traveltogether.vistas;

import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.persistencia.CiudadData;
import javax.swing.JOptionPane;

/**
 *
 * @author xiana
 */
public class vistaCiudad extends javax.swing.JInternalFrame {
    private CiudadData ciudadData = new CiudadData();
    private Ciudad ciudadActual = null;

    
    
    public vistaCiudad() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbNuevo = new javax.swing.JButton();
        jbEliminar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jtNombre = new javax.swing.JTextField();
        jrEstado = new javax.swing.JRadioButton();
        jtCodigoCiudad = new javax.swing.JTextField();
        jtBuscar = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbNuevo.setText("Nuevo");
        jbNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(jbNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, -1));

        jbEliminar.setText("Eliminar");
        jbEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jbEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, -1, -1));

        jbGuardar.setText("Guardar");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jbGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, -1, -1));

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jbSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, -1));
        getContentPane().add(jtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 90, -1));

        jrEstado.setText("Disponible");
        getContentPane().add(jrEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));
        getContentPane().add(jtCodigoCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 170, -1));

        jtBuscar.setText("Buscar");
        jtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ciudad600.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        // TODO add your handling code here:
        String nombre = jtNombre.getText();
            
        if (nombre.isEmpty()){
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }
            
        Boolean estado =jrEstado.isSelected();
            
        if (ciudadActual == null){
            ciudadActual = new Ciudad (nombre, estado);
            ciudadData.agregarCiudad(ciudadActual);
        } else {
            ciudadActual.setNombre(nombre);
            ciudadActual.setEstado(estado);
            ciudadData.modificarCiudad(ciudadActual);
        }   
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtBuscarActionPerformed
        // TODO add your handling code here:
        try{
            Integer cod = Integer.parseInt(jtCodigoCiudad.getText());
            ciudadActual = ciudadData.buscarCiudad(cod);
            
            if (ciudadActual != null){
                jtNombre.setText(ciudadActual.getNombre());
                jrEstado.setSelected(ciudadActual.isEstado());
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ingrese un número válido");
        }
    }//GEN-LAST:event_jtBuscarActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuevoActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        ciudadActual = null;
    }//GEN-LAST:event_jbNuevoActionPerformed

    private void jbEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminarActionPerformed
        // TODO add your handling code here:
        if (ciudadActual != null){
            ciudadData.bajaLogicaCiudad(ciudadActual.getCodCiudad());
            ciudadActual = null;
            limpiarCampos();
        }
    }//GEN-LAST:event_jbEliminarActionPerformed
    
    private void limpiarCampos(){
        jtCodigoCiudad.setText("");
        jtNombre.setText("");
        jrEstado.setSelected(false);    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    private javax.swing.JButton jbSalir;
    private javax.swing.JRadioButton jrEstado;
    private javax.swing.JButton jtBuscar;
    private javax.swing.JTextField jtCodigoCiudad;
    private javax.swing.JTextField jtNombre;
    // End of variables declaration//GEN-END:variables
}
