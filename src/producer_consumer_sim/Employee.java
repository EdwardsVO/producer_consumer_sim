/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_sim;


 

import java.util.concurrent.Semaphore;


/**
 *
 * @author sebastian
 */
public class Employee extends Thread {
    
    private Semaphore mutex;
    private Semaphore semButtonProd;
    private Semaphore semButtonCons;
    private Semaphore buttProducer;
    private Semaphore hireProducer;
    private int ButtonsPerDay = 4;
    private int cantProduc = 0;
    private int prodButtonqtt = 0;
    
    public void run() {
        
    }
    
    public ButtonsProd hireProdEmployee() {
        
        ButtonsProd bp = new ButtonsProd(hireProducer, buttProducer,  semButtonProd,  semButtonCons, mutex);
        
        return bp;    
    }
    
}
