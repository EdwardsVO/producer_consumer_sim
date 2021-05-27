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
    private Semaphore hireProducer;
    private int ButtonsPerDay = 4;
    private int cantProduc = 0;

    public ButtonsProd(Semaphore hireProducer, Semaphore buttProducer, Semaphore semButtonProd, Semaphore semButtonCons, Semaphore mutex) {
        this.mutex = mutex;
        this.semButtonProd = semButtonProd;
        this.semButtonCons = semButtonCons;
        this.buttProducer = buttProducer;
        this.hireProducer = hireProducer;
    }

    public void run() {
        try {
            this.buttProducer.acquire();
            this.hireProducer.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                this.semButtonProd.acquire();
                this.mutex.acquire();
                Almacen.contButtons++;
                System.out.println("Fabricado 1 boton, ahora hay " + Almacen.contButtons + " botones en el almacen.");
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
        try {
            this.hireProducer.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void hireProdButton() {
        try{
                    this.buttProducer.acquire();
                    this.cantProduc++;
                    System.out.println("Ahora hay: "+ this.cantProduc + " trabajando.");

        } catch(InterruptedException ex) {
            Logger.getLogger(ButtonsProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
