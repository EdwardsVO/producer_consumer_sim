/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;


 

import producers.ButtonsProd;
import java.util.concurrent.Semaphore;


/**
 *
 * @author sebastian
 */
public class Employee extends Thread {
    
    public ButtonsProd hireProdEmployee(Semaphore semButtonProd, Semaphore semButtonCons, Semaphore mutex, String name) {
        
        ButtonsProd bp = new ButtonsProd(semButtonProd,  semButtonCons, mutex, name);
        
        return bp;    
    }
    
}
