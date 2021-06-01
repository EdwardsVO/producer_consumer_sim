/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class Boss extends Thread{
    
    private Semaphore mutexAdmin;
    private javax.swing.JTextPane console8;
    
    
    public Boss(Semaphore mutexAdmin) {
        this.mutexAdmin = mutexAdmin;
    }
    
    public void run() {
        while(true) {
            try {
                this.mutexAdmin.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Almacen.daysLeft > 0) {
                try {
                    Thread.sleep(Almacen.dayEquiv/3);
//                     Se esta restando 4 porque choca con Time.
                     Almacen.daysLeft-= 1;
                     this.mutexAdmin.release();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                
            this.mutexAdmin.release();
        }
        }
    }

  

}
