
package gp8_traveltogether.vistas;

import gp8_traveltogether.entidades.Alojamiento;
import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.persistencia.AlojamientoData;
import gp8_traveltogether.persistencia.CiudadData;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class vistaAlojamiento extends javax.swing.JInternalFrame {
    private ArrayList <Ciudad> ciudades;
    private CiudadData ciudadData = new CiudadData();
    
    private AlojamientoData alojData = new AlojamientoData();
    private Alojamiento alojActual = null;
  

    
    public vistaAlojamiento() {
        initComponents();
        
        ciudades = ciudadData.mostrarCiudades();
        cargarCiudades();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jtCodigo = new javax.swing.JTextField();
        jtNombre = new javax.swing.JTextField();
        jtCapacidad = new javax.swing.JTextField();
        jtPrecio = new javax.swing.JTextField();
        jrEstado = new javax.swing.JRadioButton();
        jbBuscar = new javax.swing.JButton();
        jcCiudades = new javax.swing.JComboBox<>();
        jcTipo = new javax.swing.JComboBox<>();
        bg = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, -1));

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, -1, -1));

        jbGuardar.setText("Guardar");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jbGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, -1, -1));

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jbSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, -1, -1));
        getContentPane().add(jtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 80, -1));
        getContentPane().add(jtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 160, -1));
        getContentPane().add(jtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 160, -1));
        getContentPane().add(jtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 160, -1));

        jrEstado.setText("Disponible");
        getContentPane().add(jrEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        jbBuscar.setText("Buscar");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jbBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        getContentPane().add(jcCiudades, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 160, -1));

        jcTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Hotel", "Hostel", "Departamento" }));
        getContentPane().add(jcTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 160, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/alojamiento600.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        // TODO add your handling code here:
        try{
            String nombre = jtNombre.getText();
            Ciudad citySeleccionada = (Ciudad) jcCiudades.getSelectedItem();
            String tipo = (String) jcTipo.getSelectedItem();
            Integer capacidad = Integer.parseInt(jtCapacidad.getText());
            Double precio = Double.parseDouble(jtPrecio.getText());
            
            if(nombre.isEmpty() || (jcCiudades.getSelectedItem() == null)){
                JOptionPane.showMessageDialog(this, "No puede haber campos vacíos.");
                return;
            }
            
            Boolean estado = jrEstado.isSelected();
            
            if (alojActual == null){
                alojActual = new Alojamiento (nombre, citySeleccionada, tipo, capacidad, precio, estado);
                alojData.agregarAlojam(alojActual);
                JOptionPane.showMessageDialog(this, "Alojamiento guardado");
            } else {
                alojActual.setNombre(nombre);
                alojActual.setCiudad(citySeleccionada);
                alojActual.setTipoAlojam(tipo);
                alojActual.setCapacidad(capacidad);
                alojActual.setPrecioNoche(precio);
                alojData.modificarAlojam(alojActual);
            }    
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
        }
        
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        alojActual = null;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (alojActual != null){
            alojData.bajaLogicaAlojam(alojActual.getCodAlojam());
            alojActual = null;
            limpiarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "Restan menos de 30 días para el viaje. El paquete no se puede cancelar.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        // TODO add your handling code here:
        try{
            if (jtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un código.");
                return;
            }
            
            Integer codAlojam = Integer.parseInt(jtCodigo.getText());
            alojActual = alojData.buscarAlojamiento(codAlojam);
            
            if (alojActual != null){
                jtNombre.setText(alojActual.getNombre());
                
                Ciudad ciudadSeleccionada = alojActual.getCiudad();
                    for (int i = 0; i < jcCiudades.getItemCount(); i++) {
                        Ciudad ciudad = (Ciudad) jcCiudades.getItemAt(i);
                        if (ciudad.getCodCiudad() == ciudadSeleccionada.getCodCiudad()) {
                            jcCiudades.setSelectedIndex(i);
                            break;
                        }
                    }

                jcTipo.setSelectedItem(alojActual.getTipoAlojam());
                jtCapacidad.setText(String.valueOf(alojActual.getCapacidad()));
                jtPrecio.setText(String.valueOf(alojActual.getPrecioNoche()));
                jrEstado.setSelected(alojActual.isEstado());   
            } else{
                JOptionPane.showMessageDialog(this, "No existe un alojamiento con ese código.");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Número no válido.");    
        }
    }//GEN-LAST:event_jbBuscarActionPerformed
    
    private void cargarCiudades(){
        jcCiudades.removeAllItems();
        jcCiudades.addItem(new Ciudad(0, "Seleccione", false));
        for (Ciudad c: ciudades){
            jcCiudades.addItem(c);
        }  
    }
//    private void setCiudadSeleccionadaPorNombre(JComboBox <Ciudad> jcCiudades, Ciudad ciudadBuscada){
//            jcCiudades.setSelectedItem(ciudadBuscada);
//    }
    
    private void limpiarCampos(){
        jtCodigo.setText("");
        jtNombre.setText("");
        jcCiudades.setSelectedIndex(0);
        jcTipo.setSelectedItem("Seleccione");
        jtCapacidad.setText("");
        jtPrecio.setText("");
        jrEstado.setSelected(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Ciudad> jcCiudades;
    private javax.swing.JComboBox<String> jcTipo;
    private javax.swing.JRadioButton jrEstado;
    private javax.swing.JTextField jtCapacidad;
    private javax.swing.JTextField jtCodigo;
    private javax.swing.JTextField jtNombre;
    private javax.swing.JTextField jtPrecio;
    // End of variables declaration//GEN-END:variables
}
