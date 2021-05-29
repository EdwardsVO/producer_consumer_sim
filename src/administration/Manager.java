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


    
    public Manager() {
        
    }
    
    public void run() {
        while(true) {
            if(Almacen.daysLeft == 0) {
              while(Almacen.panasBuilt > 0) {
                  Almacen.panasDistributed += 1;
                  Almacen.panasBuilt -= 1;
                  System.out.println("Se ha distribuido un pana, ahora quedan: " + Almacen.panasBuilt);
                  System.out.println("Panas Distribuidos: " + Almacen.panasDistributed);
                  this.reset = true;
              }
                  
            } else { try {
                Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
}
             if(this.reset == true) {
                 System.out.println("reseting");
                 Almacen.daysLeft = 20;
                 Almacen.daysPassed = 0;
                 this.reset = false;
             }
        }
    }
}
