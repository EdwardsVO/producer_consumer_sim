/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_sim;

import java.util.concurrent.Semaphore;
/**
 *
 * @author Edward Vergel
 */
public class Producer_consumer_sim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Semaphore mutex = new Semaphore(1);
        Semaphore semButtonProd = new Semaphore(60);
        Semaphore semButtonCons = new Semaphore(0);
        Semaphore buttProducer = new Semaphore(4);
        
        ButtonsProd seb = new ButtonsProd(buttProducer, semButtonProd, semButtonCons, mutex, "Sebita");
        ButtonsProd ed = new ButtonsProd(buttProducer,semButtonProd, semButtonCons, mutex, "Edward");
        Time time = new Time();
        
        time.start();
        seb.start();
        ed.start();

    }
    
}
