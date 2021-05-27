/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import producers.ButtonsProd;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import administration.Almacen;
import administration.Employee;
import functions.Time;
import functions.dataFunctions;
import java.awt.Component;

/**
 *
 * @author sebastian
 */
public class Interfaz extends javax.swing.JFrame {
    
    boolean onSim = false;

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
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.days.setText(String.valueOf(almacen.daysPassed));
        this.hours.setText(String.valueOf(almacen.hoursPassed));
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
        jLabel4 = new javax.swing.JLabel();
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
        hireProdButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        initSimulation = new javax.swing.JButton();
        stopSimulation = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        hours = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        days = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("MATTEL PANA'S PRODUCTION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("PRODUCTORES");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

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

        hireProdButton.setText("Contratar");
        hireProdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hireProdButtonActionPerformed(evt);
            }
        });
        jPanel1.add(hireProdButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, -1));

        jButton2.setText("Despedir");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        initSimulation.setText("INIT SIMULATION");
        initSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initSimulationActionPerformed(evt);
            }
        });
        jPanel1.add(initSimulation, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, 50));

        stopSimulation.setText("STOP SIM");
        stopSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSimulationActionPerformed(evt);
            }
        });
        jPanel1.add(stopSimulation, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, 50));

        console.setEditable(false);
        jScrollPane5.setViewportView(console);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 300, 150));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("DIAS");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, -1, -1));

        hours.setEditable(false);
        jScrollPane6.setViewportView(hours);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 70, -1));

        days.setEditable(false);
        jScrollPane7.setViewportView(days);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 70, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("HORAS");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hireProdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hireProdButtonActionPerformed
        try {
            if(buttonsProdEmp.size() < 4){ //LIMITE DE PRODUCTORES 
            ButtonsProd buttonProd = emp.hireProdEmployee(semButtonProd, semButtonCons, mutex, String.valueOf(buttonsProdEmp.size()));
            buttonsProdEmp.add(buttonProd);
                for (int i = 0; i < buttonsProdEmp.size(); i++) {
                    buttonsProdEmp.get(i).setButtonsPerDay(4 * buttonsProdEmp.size());
                }
            System.out.println("Se ha agregado con exito");
            }else {
                JOptionPane.showMessageDialog(null, "Capacidad de productores alcanzada");
            }
            
        } catch (Error e) {
            System.out.println(e);
        
       }

    }//GEN-LAST:event_hireProdButtonActionPerformed

    private void initSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initSimulationActionPerformed
            this.onSim = true;
            df.csvReader();
            time.start(); //INICIALIZA EL TIEMPO
            time.init();
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).start(); // SE INICIALIZAN TODOS LOS PRODUCTORES
                buttonsProdEmp.get(i).init();
            
        }
    }//GEN-LAST:event_initSimulationActionPerformed

    private void stopSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopSimulationActionPerformed
        System.out.println(this.onSim);
        if(this.onSim == true){
            time.kill();
            for (int i = 0; i < buttonsProdEmp.size(); i++) {
                buttonsProdEmp.get(i).kill(); // SE DETIENEN TODOS LOS PRODUCTORES
            }
            this.onSim = false;
        }else {
            JOptionPane.showMessageDialog(null, "Inicialice simulacion", "ERROR", 0);
        }
    }//GEN-LAST:event_stopSimulationActionPerformed

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
    private javax.swing.JTextPane console;
    private javax.swing.JTextPane days;
    private javax.swing.JButton hireProdButton;
    private javax.swing.JTextPane hours;
    private javax.swing.JButton initSimulation;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JButton stopSimulation;
    // End of variables declaration//GEN-END:variables
}
