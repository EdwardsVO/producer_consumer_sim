/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_sim;

/**
 *
 * @author sebastian
 */
public class Almacen {
    
    public static volatile int contButtons = 0;
    
    public static volatile int contLegs = 0;
    public static volatile int contArms = 0;
    public static volatile int contBody = 0;
    public static volatile int hoursPassed = 0;
    public static volatile int daysPassed = 0;
    public static volatile int dayEquiv = 8000; //Miliseconds
    public static volatile int daysLeft = 0;
    
}
