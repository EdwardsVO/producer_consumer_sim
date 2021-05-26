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
public class ButtonsProd extends Thread {

    private Semaphore mutex;
    private Semaphore semButtonProd;
    private Semaphore semButtonCons;
    private Semaphore buttProducer;
    private String name;
    private int ButtonsPerDay = 4;

    public ButtonsProd(Semaphore buttProducer, Semaphore semButtonProd, Semaphore semButtonCons, Semaphore mutex, String name) {
        this.mutex = mutex;
        this.semButtonProd = semButtonProd;
        this.semButtonCons = semButtonCons;
        this.name = name;
        this.buttProducer = buttProducer;
    }

    public void run() {
        while (true) {
            try {
                this.buttProducer.acquire();
                this.semButtonProd.acquire();
                this.mutex.acquire();
                Almacen.contButtons++;
                System.out.println(this.name + " fabricado 1 boton, ahora hay " + Almacen.contButtons + " botones en el almacen.");
                Thread.sleep(Almacen.dayEquiv/this.ButtonsPerDay);
                this.mutex.release();
                this.semButtonCons.release();

            } catch (InterruptedException ex) {
                Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void byeProdButton(){
        this.buttProducer.release();
    }
}
