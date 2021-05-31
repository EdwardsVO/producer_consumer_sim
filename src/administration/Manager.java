/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import functions.Time;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import producers.BodyProd;

/**
 *
 * @author sebastian
 */
public class Manager extends Thread {
    
    
    javax.swing.JTextPane panasDistributed;
    javax.swing.JTextPane panasBuilt;
    private boolean reset = false;
    private boolean exit;
    private Semaphore mutexAdmin;
    private int distributed;


    
    public Manager(Semaphore mutexAdmin) {
        this.mutexAdmin = mutexAdmin;
    }
    
    public void run() {
        while(true) {
            try {
                this.mutexAdmin.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Almacen.daysLeft == 0) {
                this.distributed += Almacen.panasBuilt;
                Almacen.panasBuilt = 0;
                //System.out.println("Se han distribuido " + this.distributed + " panas, ahora quedan " + Almacen.panasBuilt + " en el almacen.");

                // Esto crashea.
                this.panasDistributed.setText(String.valueOf(this.distributed));

                Almacen.daysLeft = 20;
                Almacen.daysPassed = 0;
                this.mutexAdmin.release();
            } else {
                this.mutexAdmin.release();
                try {
                    Thread.sleep(Almacen.dayEquiv/3);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void showDistributed(javax.swing.JTextPane panasDistributed){
        this.panasDistributed = panasDistributed;
    }
    
    
}
