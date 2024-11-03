
package gp8_traveltogether.vistas;

import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.entidades.Pasaje;
import gp8_traveltogether.persistencia.CiudadData;
import gp8_traveltogether.persistencia.PasajeData;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class vistaPasaje extends javax.swing.JInternalFrame {

    private ArrayList <Ciudad> ciudades = new ArrayList <>();
    private CiudadData ciudadData = new CiudadData();
    
    private PasajeData pData = new PasajeData();
    private Pasaje pasajeActual = null;
    
    
    public vistaPasaje() {
        initComponents();
        
        ciudades = ciudadData.mostrarCiudades();
        cargarOrigen();
        cargarDestino();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jbNuevo = new javax.swing.JButton();
        jbEliminar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jtCodigo = new javax.swing.JTextField();
        jtPrecio = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jcOrigen = new javax.swing.JComboBox<>();
        jcDestino = new javax.swing.JComboBox<>();
        jcTipo = new javax.swing.JComboBox<>();
        jrEstado = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Pasaje");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Codigo:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Origen:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Destino:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Precio:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Tipo de viaje:");

        jbNuevo.setText("Nuevo");
        jbNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuevoActionPerformed(evt);
            }
        });

        jbEliminar.setText("Eliminar");
        jbEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminarActionPerformed(evt);
            }
        });

        jbGuardar.setText("Guardar");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbBuscar.setText("Buscar");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jcTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Avion", "Colectivo" }));

        jrEstado.setText("Disponible");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Estado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jbNuevo)
                .addGap(32, 32, 32)
                .addComponent(jbEliminar)
                .addGap(36, 36, 36)
                .addComponent(jbGuardar)
                .addGap(28, 28, 28)
                .addComponent(jbSalir)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbBuscar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jcTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcDestino, javax.swing.GroupLayout.Alignment.LEADING, 0, 149, Short.MAX_VALUE)
                                .addComponent(jcOrigen, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtPrecio, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jrEstado))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrEstado)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNuevo)
                    .addComponent(jbEliminar)
                    .addComponent(jbGuardar)
                    .addComponent(jbSalir))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        // TODO add your handling code here:
        try{
            Ciudad origen = (Ciudad) jcOrigen.getSelectedItem();
            Ciudad destino = (Ciudad) jcDestino.getSelectedItem();
            Double precio = Double.parseDouble(jtPrecio.getText());
            String tipo = (String) jcTipo.getSelectedItem();
            
            if((jcOrigen.getSelectedItem() == null)|| (jcDestino.getSelectedItem() == null) || (jcTipo.getSelectedItem() == null)){
                JOptionPane.showMessageDialog(null, "No puede haber campos vacíos.");
                return;
            }
            
            if (origen.getCodCiudad() == destino.getCodCiudad()){
                    JOptionPane.showMessageDialog(this,"Las ciudades de origen y destino no pueden ser iguales.");
                    return;
            }
            
            Boolean estado = jrEstado.isSelected();
            
            if(pasajeActual ==null){
                pasajeActual = new Pasaje (origen, destino, precio, tipo, estado);
                pData.agregarPasaje(pasajeActual);
            } else {
                pasajeActual.setOrigen(origen);
                pasajeActual.setDestino(destino);
                pasajeActual.setPrecioPasaje(precio);
                pasajeActual.setTipoViaje(tipo);
                pasajeActual.setEstado(true);
                pData.modificarPasaje(pasajeActual);
            }
            
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(this,"Ingrese un precio válido."); 
        }
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuevoActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        pasajeActual = null;
    }//GEN-LAST:event_jbNuevoActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        // TODO add your handling code here:
        try{
            if (jtCodigo.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Error. Ingrese un numero válido.");
                return;
            }
            
            Integer cod = Integer.parseInt(jtCodigo.getText());
            pasajeActual = pData.buscarPasaje(cod);
            
            if (pasajeActual !=null){
                
                Ciudad origen = pasajeActual.getOrigen();
                    for (int i = 0; i < jcOrigen.getItemCount(); i++) {
                        Ciudad ciudad = (Ciudad) jcOrigen.getItemAt(i);
                        if (ciudad.getCodCiudad() == origen.getCodCiudad()) {
                            jcOrigen.setSelectedIndex(i);
                            break;
                        }
                    }
                      
                Ciudad destino = pasajeActual.getDestino();
                    for (int i = 0; i < jcDestino.getItemCount(); i++) {
                    Ciudad ciudad = (Ciudad) jcDestino.getItemAt(i);
                    if (ciudad.equals(destino)) {
                    jcDestino.setSelectedIndex(i);
                    break;
                    }
                }

                jtPrecio.setText(String.valueOf(pasajeActual.getPrecioPasaje()));
                jcTipo.setSelectedItem(pasajeActual.getTipoViaje());
                jrEstado.setSelected(pasajeActual.isEstado()); 
            }else {
                 JOptionPane.showMessageDialog(this,"No existe un pasaje con ese código.");
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Número no válido.");
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminarActionPerformed
        // TODO add your handling code here:
        if( pasajeActual != null){
            pData.bajaLogica(pasajeActual.getCodPasaje());
            pasajeActual = null;
            limpiarCampos();
        }
    }//GEN-LAST:event_jbEliminarActionPerformed

    private void cargarOrigen(){
        jcOrigen.removeAllItems();
        jcOrigen.addItem(new Ciudad(0, "Seleccione", false));
        for (Ciudad c: ciudades){
            jcOrigen.addItem(c);
        }
    }
    
    public void cargarDestino(){
        jcDestino.removeAllItems();
        jcDestino.addItem(new Ciudad(0, "Seleccione", false));
        for (Ciudad c: ciudades){
            jcDestino.addItem(c);
        }  
    }
    
    private void limpiarCampos(){
        jtCodigo.setText("");
        jcOrigen.setSelectedIndex(0);
        jcDestino.setSelectedIndex(0);
        jtPrecio.setText("");
        jcTipo.setSelectedItem("Seleccione");
        jrEstado.setSelected(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Ciudad> jcDestino;
    private javax.swing.JComboBox<Ciudad> jcOrigen;
    private javax.swing.JComboBox<String> jcTipo;
    private javax.swing.JRadioButton jrEstado;
    private javax.swing.JTextField jtCodigo;
    private javax.swing.JTextField jtPrecio;
    // End of variables declaration//GEN-END:variables
}
