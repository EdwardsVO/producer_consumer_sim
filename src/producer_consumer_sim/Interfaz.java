/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_sim;

import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author sebastian
 */
public class Interfaz extends javax.swing.JFrame {

    Almacen almacen = new Almacen(); //VARIABLES VOLATILES
    Employee emp = new Employee(); //FUNCIONES PARA CONTRATAR Y DESPEDIR
    Time time = new Time(); //TIEMPO EN SIMULACION
    
    dataFunctions df = new dataFunctions();

    ArrayList<ButtonsProd> buttonsProdEmp = new ArrayList<ButtonsProd>(); //PRODUCTORES DE BOTONES MAXIMOS

    Semaphore mutex = new Semaphore(1);
    //PROD - CONS ----> BOTONES
    Semaphore semButtonProd = new Semaphore(60); //TOTAL DE BOTONES
    Semaphore semButtonCons = new Semaphore(0); //CANTIDAD EN CONSUMO
    
    //PROD - CONS ----> BRAZOS
    
    //ENSAMBLADORES

    public Interfaz() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jSlider2 = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jSlider4 = new javax.swing.JSlider();
        jSlider5 = new javax.swing.JSlider();
        hireProdButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        initSimulation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("MATTEL PANA'S PRODUCTION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ALMACEN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("PRODUCTORES");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("ENSAMBLADORES");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Cuerpo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Botones");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Brazos");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Piernas");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setForeground(new java.awt.Color(204, 204, 204));

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jTextPane1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 40, -1));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setForeground(new java.awt.Color(204, 204, 204));

        jTextPane2.setEditable(false);
        jTextPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(jTextPane2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 40, -1));

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setForeground(new java.awt.Color(204, 204, 204));

        jTextPane3.setEditable(false);
        jTextPane3.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setViewportView(jTextPane3);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 40, -1));

        jScrollPane4.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane4.setForeground(new java.awt.Color(204, 204, 204));

        jTextPane4.setEditable(false);
        jTextPane4.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane4.setViewportView(jTextPane4);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 40, -1));

        jSlider2.setMaximum(4);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setSnapToTicks(true);
        jSlider2.setValue(1);
        jPanel1.add(jSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 100, 20));

        jSlider3.setMaximum(4);
        jSlider3.setPaintLabels(true);
        jSlider3.setPaintTicks(true);
        jSlider3.setSnapToTicks(true);
        jSlider3.setValue(1);
        jPanel1.add(jSlider3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 100, 20));

        jSlider4.setMaximum(5);
        jSlider4.setPaintLabels(true);
        jSlider4.setPaintTicks(true);
        jSlider4.setSnapToTicks(true);
        jSlider4.setValue(1);
        jPanel1.add(jSlider4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 100, 20));

        jSlider5.setMaximum(4);
        jSlider5.setPaintLabels(true);
        jSlider5.setPaintTicks(true);
        jSlider5.setSnapToTicks(true);
        jSlider5.setValue(1);
        jPanel1.add(jSlider5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 100, 20));

        hireProdButton.setText("Contratar");
        hireProdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hireProdButtonActionPerformed(evt);
            }
        });
        jPanel1.add(hireProdButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jButton2.setText("Despedir");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        initSimulation.setText("INIT SIMULATION");
        initSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initSimulationActionPerformed(evt);
            }
        });
        jPanel1.add(initSimulation, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hireProdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hireProdButtonActionPerformed
        try {
            if(buttonsProdEmp.size() <= 4){ //LIMITE DE PRODUCTORES 
            ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutex, String.valueOf(buttonsProdEmp.size()));
            buttonsProdEmp.add(buttonProd);
            System.out.println("Se ha agregado con exito");
            }
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, "Capacidad de productores alcanzada");
        
       }

    }//GEN-LAST:event_hireProdButtonActionPerformed

    private void initSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initSimulationActionPerformed
            df.csvReader();;
            time.start(); //INICIALIZA EL TIEMPO
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).start(); // SE INICIALIZAN TODOS LOS PRODUCTORES
            
        }
    }//GEN-LAST:event_initSimulationActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hireProdButton;
    private javax.swing.JButton initSimulation;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    private javax.swing.JSlider jSlider5;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    // End of variables declaration//GEN-END:variables
}
