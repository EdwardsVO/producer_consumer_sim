/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_sim;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class ButtonsProd extends Thread{
    Semaphore mutex; 
    Semaphore semButtonProd;
    Semaphore semButtonCons;
    String name;
    
    public ButtonsProd(Semaphore semButtonProd, Semaphore semButtonCons, Semaphore mutex, String name){
        this.mutex = mutex;
        this.semButtonProd = semButtonProd;
        this.semButtonCons = semButtonCons;
        this.name = name;
    }
    
    public void run() {
        while(true) {
            try {
                
                    this.semButtonProd.acquire();
                    this.mutex.acquire();
                    Almacen.contButtons++;
                    System.out.println("Se ha fabricado 1 boton, ahora hay " + Almacen.contButtons + " botones en el almacen.");
                    this.mutex.release();
                    this.semButtonProd.release();                         
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
