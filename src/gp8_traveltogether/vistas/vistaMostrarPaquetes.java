
package gp8_traveltogether.vistas;

import gp8_traveltogether.entidades.Paquete;
import gp8_traveltogether.entidades.Turista;
import gp8_traveltogether.persistencia.PaqueteData;
import gp8_traveltogether.persistencia.TuristaData;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class vistaMostrarPaquetes extends javax.swing.JInternalFrame {
    
    private ArrayList <Paquete> paquetes;
    private ArrayList <Turista> turistas;
    
    private PaqueteData paqueteData;
    private TuristaData turistaData;
    private DefaultTableModel tablaMostrarPaquetes = new DefaultTableModel();

    public vistaMostrarPaquetes() {
        initComponents();
        paqueteData = new PaqueteData();
        turistaData = new TuristaData();
        armarTabla();
 
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jrVigentes = new javax.swing.JRadioButton();
        jrVencidos = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePaquetes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Paquetes");

        jrVigentes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jrVigentes.setText("Paquetes vigentes");
        jrVigentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrVigentesActionPerformed(evt);
            }
        });

        jrVencidos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jrVencidos.setText("Paquetes vencidos");
        jrVencidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrVencidosActionPerformed(evt);
            }
        });

        tablePaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablePaquetes);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jrVigentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 493, Short.MAX_VALUE)
                .addComponent(jrVencidos)
                .addGap(243, 243, 243))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(516, 516, 516)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(522, 522, 522)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrVigentes)
                    .addComponent(jrVencidos))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrVigentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrVigentesActionPerformed
        // TODO add your handling code here:
        borrarFilasTabla();
        jrVencidos.setSelected(false);
        cargarPaquetesVigentes();
    }//GEN-LAST:event_jrVigentesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jrVencidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrVencidosActionPerformed
        // TODO add your handling code here:
        borrarFilasTabla();
        jrVigentes.setSelected(false);
        cargarPaquetesVencidos();
    }//GEN-LAST:event_jrVencidosActionPerformed
    
    public void cargarPaquetesVigentes(){
        paquetes = paqueteData.mostrarPaquetes(true);
        DateTimeFormatter formatoNuestro = DateTimeFormatter.ofPattern("dd/MM/yy");
        
        for (Paquete paquete: paquetes){
            boolean traslado = paquete.isTraslado();
            String tieneTraslado = traslado ? "Si": "No";
            
            String nombresTuris=" ";
            
            turistas = turistaData.mostrarTuristasPorPaquete(paquete.getCodigoPaquete());
            for (Turista turi: turistas){
                nombresTuris += turi.getNombre()+ ", ";
            }
        
            
            tablaMostrarPaquetes.addRow(new Object []{
                paquete.getCodigoPaquete(),
                paquete.getBoleto().toString(),
                paquete.getFechaInicio().format(formatoNuestro),
                paquete.getFechaFin().format(formatoNuestro),
                paquete.getTemporada(),
                paquete.getEstadia().getTipoAlojam()+" "+paquete.getEstadia().getNombre(),
                paquete.getBoleto().getTipoViaje(),
                paquete.getPension().getNombre(),
                tieneTraslado,
                nombresTuris,
                paquete.getMontoFinal(),
            });
        }
    }
    
    public void cargarPaquetesVencidos(){
        paquetes = paqueteData.mostrarPaquetes(false);
        DateTimeFormatter formatoNuestro = DateTimeFormatter.ofPattern("dd/MM/yy");
        
        for (Paquete paquete: paquetes){
            boolean traslado = paquete.isTraslado();
            String tieneTraslado = traslado ? "Si": "No";
            
            String nombresTuris=" ";
            
            turistas = turistaData.mostrarTuristasPorPaquete(paquete.getCodigoPaquete());
            for (Turista turi: turistas){
                nombresTuris += turi.getNombre()+ ", ";
            }
        
            
            tablaMostrarPaquetes.addRow(new Object []{
                paquete.getCodigoPaquete(),
                paquete.getBoleto().toString(),
                paquete.getFechaInicio().format(formatoNuestro),
                paquete.getFechaFin().format(formatoNuestro),
                paquete.getTemporada(),
                paquete.getEstadia().getTipoAlojam()+" "+paquete.getEstadia().getNombre(),
                paquete.getBoleto().getTipoViaje(),
                paquete.getPension().getNombre(),
                tieneTraslado,
                nombresTuris,
                paquete.getMontoFinal(),
            });
        }
        
    }
             
    public void armarTabla(){
        tablaMostrarPaquetes.addColumn("Código");
        tablaMostrarPaquetes.addColumn("Origen y destino");
        tablaMostrarPaquetes.addColumn("Ida");
        tablaMostrarPaquetes.addColumn("Vuelta");
        tablaMostrarPaquetes.addColumn("Temporada");
        tablaMostrarPaquetes.addColumn("Alojamiento");
        tablaMostrarPaquetes.addColumn("Viaje");
        tablaMostrarPaquetes.addColumn("Pension");
        tablaMostrarPaquetes.addColumn("Traslado");
        tablaMostrarPaquetes.addColumn("Turistas");
        tablaMostrarPaquetes.addColumn("Monto final");
        
        tablePaquetes.setModel(tablaMostrarPaquetes);
        
    }
    
    private void borrarFilasTabla() {
    int indice = tablaMostrarPaquetes.getRowCount() - 1;
    for (int i = indice; i >= 0; i--) {
        tablaMostrarPaquetes.removeRow(i);
    }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrVencidos;
    private javax.swing.JRadioButton jrVigentes;
    private javax.swing.JTable tablePaquetes;
    // End of variables declaration//GEN-END:variables
}
