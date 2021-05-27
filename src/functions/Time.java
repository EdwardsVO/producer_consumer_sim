/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import administration.Almacen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edward Vergel
 */
public class Time extends Thread {
    
    private boolean start;
    public static volatile boolean passed = false;
    
    public void run() {
        while (this.start) {
            while (Almacen.hoursPassed != 24) {
                try {
                    this.passed = false;
                    Thread.sleep(Almacen.dayEquiv / 24);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
                }
                Almacen.hoursPassed++;
            }
            Almacen.daysPassed++;
            Almacen.hoursPassed = 0;
            System.out.println("Han transcurrido " + Almacen.daysPassed + " dias");
            this.passed = true;
            
        }
        
    }
    
    public void kill(){
        this.start = false;
    }
    public void init(){
        this.start = true;
    }
}
