/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import producers.ButtonsProd;

/**
 *
 * @author sebastian
 */
public class Assembler extends Thread {
    
    Semaphore semCons;
    Semaphore semButtonsProd;
    Semaphore mutex;
    String name;
    
    public Assembler(Semaphore semCons,Semaphore semButtonsProd, Semaphore mutex, String name) {
        this.semCons = semCons;
        this.semButtonsProd = semButtonsProd;
        this.mutex = mutex;
        this.name = name;
    }
    
    public void run() {
        while(true) {
            try {
                this.semCons.acquire();
                this.mutex.acquire();
                Almacen.contButtons--;
                System.out.println("Se ha consumido un boton, ahora quedan " + Almacen.contButtons + " en el almacen.");
                this.mutex.release();
                this.semButtonsProd.release();
            } catch(InterruptedException ex) {
                Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
