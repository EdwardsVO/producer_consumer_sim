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
    javax.swing.JTextPane tandas;
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

                this.panasDistributed.setText(String.valueOf(this.distributed));
                this.panasBuilt.setText(String.valueOf(Almacen.panasBuilt));

                Almacen.daysLeft = 20;
                //Almacen.daysPassed = 0;
                Almacen.tandas ++;
                this.tandas.setText(String.valueOf(Almacen.tandas));
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
    
    public void showDistributed(javax.swing.JTextPane panasDistributed, javax.swing.JTextPane panasBuilt, javax.swing.JTextPane tandas){
        this.panasDistributed = panasDistributed;
        this.panasBuilt = panasBuilt;
        this.tandas = tandas;
    }
    
    
}
